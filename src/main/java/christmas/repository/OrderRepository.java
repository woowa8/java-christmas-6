package christmas.repository;

import christmas.domain.Category;
import christmas.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    // List로 order 저장
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    // 모든 order 내보내기
    public List<Order> getOrders() {
        return orders;
    }

    // 총 갯수 print
    public int totalOrdersCount() {
        int cnt = 0;
        for (Order order : orders) {
            cnt += order.getAmount();
        }
        return cnt;
    }

    // 총 주문 안에 있는 총 카테고리별 갯수 출력하기
    public int countByCategory(Category category) {
        return (int) orders.stream()
                .filter(order -> category.equals(order.getMenu().getCategory()))
                .count();
    }

    // order안에 menu 이름이 같은게 있는지 본다.
    public Order findOrderByMenuName(String menuName) {
        return orders.stream()
                .filter(order -> menuName.equals(order.getMenu().getName()))
                .findFirst().orElse(null);
    }
}
