package christmas.service;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.OrderRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
### OrderService
- 기능 구현
  - [ ] 총 금액 계산
  - [ ] 증정메뉴 여부 조회
  - [ ] 총 할인 적용 조회
  - [ ] 총 혜택 금액 계산
  - [ ] 배지 프린트
  - [ ] 상품 저장
- 예외 처리
    - [ ] 이미 존재하는 상품을 더하려고 할 경우
    - [ ] 없는 메뉴를 주문하려고 할 경우
 */
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 총 금액 계산
    public int sumBeforeDiscount() {
        int totalAmount = 0;

        List<Order> orders = orderRepository.getOrders();
        for (Order order : orders) {
            Menu menu = order.getMenu();
            totalAmount += menu.getPrice();
        }

        return totalAmount;
    }

    // 증정메뉴 여부 조회
    public String givePresent() {
        int totalAmount = sumBeforeDiscount();
        if (totalAmount >= 120_000) {
            // TODO : 혜택 내역에 저장하는거 있어야 한다.
            return "샴페인 1개";    // 일단 가라로 한다.
        }
        return "없음";
    }

    // 총 할인 적용 조회
    public List<List<String>> sumDiscountAmount(int day) {
        Map<String, Integer> sales = new HashMap<>();
        List<Order> orders = orderRepository.getOrders();

        // 날짜를 알아야 한다.
        DayOfWeek yoil = caculateYoil(day);

        // 1~25 사이이면 "크리스마스 프로모션" + 할인 금액 저장
        if (1 <= day && day <= 25) {
            sales.put("크리스마스 프로모션", 1000 + (100 * (day-1)));
        }
        // 오늘이 금&토(주말)인데 메인 메뉴 있으면 "주말 할인" + 2023 저장
        if (yoil.equals(DayOfWeek.FRIDAY) || yoil.equals(DayOfWeek.SATURDAY)) {
            for(Order order : orders) {
                if(Category.MAIN.equals(order.getMenu().getCategory())){
                    sales.put("주말 할인", 2023 * sales.getOrDefault("주말 할인", 1));
                }
            }
        }else {
            // 오늘이 일~목(평일)인데 디저트 메뉴 있으면 "평일 할인" + 2023 저장
            for(Order order : orders) {
                if(Category.DESERT.equals(order.getMenu().getCategory())){
                    sales.put("평일 할인", 2023 * sales.getOrDefault("평일 할인", 1));
                }
            }
        }
        // 오늘이 일요일이나 25일 이면 "특별 할인" + 1000 저장
        // 총 할인 금액 12만원 이상이면 "증정 이벤트" + 25000원 저장
        List<Order> orders = orderRepository.getOrders();
    }

    private DayOfWeek caculateYoil(int day) {
        LocalDate date = LocalDate.of(2023, 12, day);
        return date.getDayOfWeek();
    }
    // 총 혜택 금액 계산
    // 배지 프린트
    // 상품 저장
}
