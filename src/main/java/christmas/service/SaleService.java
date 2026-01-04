package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.Sale;
import christmas.repository.SaleRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleService {
    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    // 혜택 list 출력하기 : map으로 내보내서
    public Map<Sale, Integer> getAllSale(int day) {
        Map<Sale, Integer> totalSaleAmount = new HashMap<>();   // 가격을 저장한다.

        List<Sale> sales = saleRepository.findAllSales();
        Map<Sale, Integer> map = saleRepository.findTotalSaleCnt();

        for (Sale sale : sales) {
            int amount = Sale.getAmount(sale, day) * map.getOrDefault(sale, 0);    // 총 할인 가격을 만든다.
            totalSaleAmount.put(sale, amount);
        }

        return totalSaleAmount;
    }

    // 전체 세일 더하기
    public void addTotalSale(int day) {
        // 1. 1 ~ 25일 사이
        if (1 <= day && day <= 25) {
            saleRepository.addTotalSale(Sale.CHRISTMAS_D_DAY);
        }

        LocalDate date = LocalDate.of(2023, 12, day);
        DayOfWeek dayWeek = date.getDayOfWeek();

        // 2. 일요일 혹은 크리스마스
        if(dayWeek == DayOfWeek.SUNDAY || day == 25){
            saleRepository.addTotalSale(Sale.SPECIAL);
        }
    }

    // 개별 혜택 추가하기
    public void addIndividualSale(Order order, int day) {
        // TODO : 날짜 변환 주의하기
        LocalDate date = LocalDate.of(2023, 12, day);
        DayOfWeek dayWeek = date.getDayOfWeek();

        boolean isWeekend = (dayWeek == DayOfWeek.FRIDAY || dayWeek == DayOfWeek.SATURDAY);

        // day가 평일인데 & 디저트일 경우
        if (!isWeekend && Category.DESERT.equals(order.getMenu().getCategory())) {
            saleRepository.addTotalSale(Sale.WEEKDAYS);
            return;
        }

        // day가 주말인데 & 메인일 경우
        if (isWeekend && Category.MAIN.equals(order.getMenu().getCategory())) {
            saleRepository.addTotalSale(Sale.WEEKEND);
        }
    }

    // 전체 할인 금액 출력하기
    public int calculateSaleAmount(int day) {
        int totalSaleAmount = 0;

        List<Sale> sales = saleRepository.findAllSales();
        Map<Sale, Integer> map = saleRepository.findTotalSaleCnt();

        for (Sale sale : sales) {
            totalSaleAmount += Sale.getAmount(sale, day) * map.getOrDefault(sale, 0);    // 총 할인 가격을 만든다.
        }

        return totalSaleAmount;
    }

    // 증정 메뉴 보여주기 : 직접 String으로 return
    public String givePresent(int totalAmount) {
        if (totalAmount >= 1200000) {
            saleRepository.addTotalSale(Sale.TAKE);
            return "샴페인";
        }
        return "없음";
    }

    // 뱃지 보여주기
    public Badge getBadge(int day) {
        int total = calculateSaleAmount(day);

        if (5000 <= total && total < 10000) {
            return Badge.STAR;
        }

        if (10000 <= total && total < 20000) {
            return Badge.TREE;
        }

        if (20000 <= total) {
            return Badge.SANTA;
        }
    }
}
