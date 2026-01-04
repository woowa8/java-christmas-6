package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    // 음료만 있는지 검증하기
    public void isAllDrinks(List<List<String>> inputOrder) {
        int orderCount = inputOrder.size();
        int drinksCount = 0;

        for(List<String> order : inputOrder) {
            String menuName = order.get(0);
            Menu menu = menuRepository.findMenuByName(menuName);
            if(Category.DRINKS.equals(menu.getCategory())) {
                drinksCount++;
            }
        }

        if(drinksCount == orderCount) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    // 상품 저장 0번째 인덱스는 이름, 1번째 인덱스는 갯수
    public void saveOrder(List<String> order) {
        String menuName = order.get(0);
        String count = order.get(1);

        // 예외 처리 : 없는 메뉴를 주문하려고 할 경우
        if (menuRepository.findMenuByName(menuName) == null) {
            System.out.println("menuName : " + menuName);   // TODO : 디버깅
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        // 예외 처리 : 이미 존재하는 상품을 더하려고 할 경우
        if (orderRepository.findOrderByMenuName(menuName) != null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        // 예외 처리 : 현재 20개 있는 경우 더 이상 주문 불가
        if (orderRepository.totalOrdersCount() >= 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 20개 이상 주문할 수 없습니다.");
        }

        Menu menu = menuRepository.findMenuByName(menuName);
        Order newOrder = new Order(menu, Integer.parseInt(count));

        orderRepository.addOrder(newOrder);
    }

    // 총 주문 가져오기
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }

    // 총 금액 계산
    public int sumBeforeDiscount() {
        int totalAmount = 0;

        List<Order> orders = orderRepository.getOrders();
        for (Order order : orders) {
            Menu menu = order.getMenu();
            totalAmount += (menu.getPrice() * order.getAmount());
        }

        return totalAmount;
    }

    // 증정메뉴 여부 조회
    public String givePresent(int totalAmount) {
        if (totalAmount >= 120_000) {
            return "샴페인 1개";    // 일단 가라로 한다.
        }
        return "없음";
    }

    // 총 할인 적용 조회
    public Map<String, Integer> sumDiscountAmount(int day, String givePresent) {
        Map<String, Integer> sales = new HashMap<>();
        List<Order> orders = orderRepository.getOrders();

        // 날짜를 알아야 한다.
        DayOfWeek yoil = caculateYoil(day);

        // 1~25 사이이면 "크리스마스 디데이 할인" + 할인 금액 저장
        if (1 <= day && day <= 25) {
            sales.put("크리스마스 디데이 할인", 1000 + (100 * (day - 1)));
        }

        // 오늘이 금&토(주말)인데 메인 메뉴 있으면 "주말 할인" + 2023 저장
        if (yoil.equals(DayOfWeek.FRIDAY) || yoil.equals(DayOfWeek.SATURDAY)) {
            for (Order order : orders) {
                if (Category.MAIN.equals(order.getMenu().getCategory())) {
                    sales.put("주말 할인", 2023 * order.getAmount());
                }
            }
        } else {
            // 오늘이 일~목(평일)인데 디저트 메뉴 있으면 "평일 할인" + 2023 저장
            for (Order order : orders) {
                if (Category.DESERT.equals(order.getMenu().getCategory())) {
                    sales.put("평일 할인", 2023 * order.getAmount());
                }
            }
        }

        // 오늘이 일요일이나 25일 이면 "특별 할인" + 1000 저장
        if (yoil.equals(DayOfWeek.SUNDAY) || day == 25) {
            sales.put("특별 할인", 1000);
        }

        // 총 할인 금액 12만원 이상이면 "증정 이벤트" + 25000원 저장
        if (!"없음".equals(givePresent)) {
            sales.put("증정 이벤트", 25000);
        }

        return sales;
    }

    private DayOfWeek caculateYoil(int day) {
        LocalDate date = LocalDate.of(2023, 12, day);
        return date.getDayOfWeek();
    }

    // 총 혜택 금액 계산
    public int sumDiscount(Map<String, Integer> sales) {
        int totalDiscount = 0;

        // TODO : Map에서 총 합 계산하는거 알아두기
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            totalDiscount += entry.getValue();
        }

        return totalDiscount;
    }

    public int sumDiscountExpectedPresent(Map<String, Integer> sales) {
        int totalDiscount = 0;

        // TODO : Map에서 총 합 계산하는거 알아두기
        for (Map.Entry<String, Integer> entry : sales.entrySet()) {
            if(!"증정 이벤트".equals(entry.getKey())){
                totalDiscount += entry.getValue();
            }
        }

        return totalDiscount;
    }

    // 배지 프린트
    public Badge getBadge(int totalDiscount) {
        if (5000 <= totalDiscount && totalDiscount < 10000) {
            return Badge.STAR;
        }

        if (10000 <= totalDiscount && totalDiscount < 20000) {
            return Badge.TREE;
        }

        if (totalDiscount >= 20000) {
            return Badge.SANTA;
        }

        return null;
    }

}
