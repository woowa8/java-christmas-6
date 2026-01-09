package christmas.controller;

//- 기능 구현
//    - [ ] run으로 모든 구현 흐름 관리
//    - [ ] 모든 출력과 흐름을 한군데서 관리
//    - [ ] try-catch로 예외 관리

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.repository.MenuRepository;
import christmas.repository.OrderRepository;
import christmas.service.OrderService;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class OrderController {
    private final InputView inputView;
    private final InputParser inputParser;
    private final OutputView outputView;
    private final OrderService orderService;
    private final MenuRepository menuRepository;

    // 모든 객체 생성을 controller에서 전담
    public OrderController() {
        inputParser = new InputParser();
        inputView = new InputView(inputParser);
        outputView = new OutputView();
        menuRepository = new MenuRepository();    // TODO : 객체 두 번 생성하면 초기화 되니까 주의하기
        orderService = new OrderService(
                new OrderRepository(), menuRepository
        );
    }

    // 초기 메뉴 생성
    private void init() {
        Menu a1 = new Menu("양송이수프", Category.APPETIZER, 6_000);
        Menu a2 = new Menu("타파스", Category.APPETIZER, 5_500);
        Menu a3 = new Menu("시저샐러드", Category.APPETIZER, 8_000);
        menuRepository.addMenu(a1);
        menuRepository.addMenu(a2);
        menuRepository.addMenu(a3);

        Menu m1 = new Menu("티본스테이크", Category.MAIN, 55_000);
        Menu m2 = new Menu("바비큐립", Category.MAIN, 54_000);
        Menu m3 = new Menu("해산물파스타", Category.MAIN, 35_000);
        Menu m4 = new Menu("크리스마스파스타", Category.MAIN, 25_000);
        menuRepository.addMenu(m1);
        menuRepository.addMenu(m2);
        menuRepository.addMenu(m3);
        menuRepository.addMenu(m4);

        Menu d1 = new Menu("초코케이크", Category.DESERT, 15_000);
        Menu d2 = new Menu("아이스크림", Category.DESERT, 5_000);
        menuRepository.addMenu(d1);
        menuRepository.addMenu(d2);

        Menu dr1 = new Menu("제로콜라", Category.DRINKS, 3_000);
        Menu dr2 = new Menu("레드와인", Category.DRINKS, 60_000);
        Menu dr3 = new Menu("샴페인", Category.DRINKS, 25_000);
        menuRepository.addMenu(dr1);
        menuRepository.addMenu(dr2);
        menuRepository.addMenu(dr3);
    }

    // run으로 모든 구현 흐름 관리 & try-catch로 예외 관리
    public void run() {
        // 메뉴 생성
        init();
        // 인트로 메세지
        outputView.printIntro();
        // 날짜 받기
        int day = inputView.inputDay();
        // 주문 받기
        List<List<String>> inputOrder = inputView.inputMenu();

        // 아웃트로 메세지
        outputView.printOutro(day);

        // 주문 메뉴 출력
        try{
            orderService.isAllDrinks(inputOrder);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 총 주문 출력
        // TODO : throw는 전파시 controller에서 꼭 받아야 한다는거 기억하기
        // TODO : 여기서 처리하면 바로 끝나버려서 고민
        try{
            for(List<String> order : inputOrder) {
                orderService.saveOrder(order);
            }
            outputView.printMenu(orderService.getOrders());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 할인 전 총 주문 금액 출력
        int sumBeforeDiscount = orderService.sumBeforeDiscount();
        outputView.printOrders(sumBeforeDiscount);

        // 증정 메뉴 출력
        String present = orderService.givePresent(sumBeforeDiscount);
        outputView.printPresent(present);

        // 혜택 출력
        Map<String, Integer> sales = orderService.sumDiscountAmount(day, present);
        outputView.printSales(sales);

        // 총 혜택 금액 출력
        int totalDiscount = orderService.sumDiscount(sales);
        outputView.printTotalSaleAmount(totalDiscount);

        // 할인 후 예상 결제 금액 출력
        // TODO : 요구사항이랑 출력형태 잘 보기, 예상 결제 금액에서 샴페인 가격 빠져 있음
        int expectedAmount = sumBeforeDiscount - orderService.sumDiscountExpectedPresent(sales);
        outputView.printExpectPrice(expectedAmount);

        // 12월 이벤트 배지 출력
        outputView.printBadge(orderService.getBadge(totalDiscount));
    }
}
