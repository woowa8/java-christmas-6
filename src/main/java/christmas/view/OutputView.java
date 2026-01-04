package christmas.view;

import christmas.domain.Order;

import java.util.List;

public class OutputView {
    // 총 주문 갯수 프린트
    public void printOrders(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders) {
            System.out.println(order.getCount() + " " + order.getCount() + "개");
        }
        System.out.println();
    }

    // 할인 전 총주문 금액
    public void printBeforeAmount(int amount) {
        System.out.println("<할인 전 총주문 금액>");
        String money = String.format("%,d", amount);
        System.out.println(money + "원");
    }

    // 증정 메뉴
    public void printPresent(String present) {
        System.out.println("<증정 메뉴>");
        System.out.println(present);
    }

    // 혜택 내역

    // 총혜택 금액

    // 할인 후 예상 결제 금액

    // 12월 이벤트 배지
}
