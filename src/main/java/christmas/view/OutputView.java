package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Order;

import java.util.List;
import java.util.Map;

public class OutputView {
    // 0. 인트로 프린트
    public void printIntro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOutro(int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    // 1. 주문 메뉴 프린트
    public void printMenu(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders) {
            System.out.println(order.getMenu().getName() + " " + order.getAmount() + "개");
        }
        System.out.println();
    }

    // 2. 할인 전 총 주문금액 프린트
    public void printOrders(int totalAmount) {
        System.out.println("<할인 전 총주문 금액>");
        String money = String.format("%,d", totalAmount);
        System.out.println(money + "원");
        System.out.println();
    }

    // 3. 증정메뉴 프린트
    public void printPresent(String present) {
        System.out.println("<증정 메뉴>");
        System.out.println(present);
        System.out.println();
    }

    // 4. 혜택 내역 프린트
    public void printSales(Map<String, Integer> sales) {
        System.out.println("<혜택 내역>");

        if (sales.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        sales.keySet().forEach(key -> {
            System.out.println(key+ ": -" + String.format("%,d", sales.get(key)) + "원");
        });
        System.out.println();
    }

    // 5. 총 혜택 금액 프린트
    public void printTotalSaleAmount(int totalSaleAmount) {
        System.out.println("<총혜택 금액>");
        String money = String.format("%,d", totalSaleAmount);
        if("0".equals(money)) {
            System.out.println(money + "원");
            System.out.println();
            return;
        }
        System.out.println("-" + money + "원");
        System.out.println();
    }

    // 6. 할인 후 예상 결제 금액 프린트
    public void printExpectPrice(int expectPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        String money = String.format("%,d", expectPrice);
        System.out.println(money + "원");
        System.out.println();
    }

    // 7. 12월 이벤트 배지 프린트
    public void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");

        if (badge == null) {
            System.out.println("없음");
            System.out.println();
            return;
        }

        System.out.println(badge.getDisplayName());
        System.out.println();
    }
}
