package christmas.service;

import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    // 주문 저장하기
    // TODO : 이미 ORDERREPO 안에 있는거면 에러 발생 시킨다. (중복 오더)
    public void save(Order order) {
        // 메뉴 20개 이상일시 에러
        if(orderRepository.getOrders().size() >= 19){
            throw new IllegalArgumentException("[ERROR] 메뉴는 20개 이상 주문할 수 없습니다.");
        }

        if(menuRepository.findMenuByName(order.getMenu().getName()) == null){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 메뉴입니다.");
        }

        // TODO : 음료 하나만 저장 불가 로직 필요
        orderRepository.add(order);
    }

    // 주문 메뉴 보여주기
    public List<Order> findAllOrders() {
        return orderRepository.getOrders();
    }

    // 주문 금액 계산하기
    public int totalAmount() {
        List<Order> orders = orderRepository.getOrders();
        int total = 0;
        for (Order order : orders) {
            total += order.getMenu().getPrice() * order.getCount();
        }
        return total;
    }
}
