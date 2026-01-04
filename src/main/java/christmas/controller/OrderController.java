package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.repository.MenuRepository;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class OrderController {
    private InputView inputView;
    private OutputView outputView;
    private OrderService orderService;
    private MenuRepository menuRepository;

    public OrderController(InputView inputView, OutputView outputView,
                           OrderService orderService, MenuRepository menuRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
        this.menuRepository = menuRepository;
    }

    // 주문 메뉴 전체 보기
    public void allOrders(){
        List<List<String>> orders = inputView.inputOrder();
        // 저장하기
        for (List<String> order : orders) {
            String menuName = order.get(0);
            int cnt = Integer.parseInt(order.get(1));

            Order order1 = new Order(menuRepository.findMenuByName(menuName), cnt);
            orderService.save(order1);
        }
        // 보여주기
        outputView.printOrders(orderService.findAllOrders());
    }

    // 할인 전 메뉴 총 금액 보기
    public void totalAmount(){
        outputView.printBeforeAmount(orderService.totalAmount());
    }
}
