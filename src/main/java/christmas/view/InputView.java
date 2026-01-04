package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.InputParser;

import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private final InputParser parser;

    public InputView(InputParser parser) {
        this.parser = parser;
    }

    // 식당 방문 날짜 받기
    public int inputDay() {
        return retryOnError(() -> {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            int input = Integer.parseInt(Console.readLine());

            if (1 > input || input > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }

            return input;
        });
    }

    // 주문 메뉴 받기
    public List<List<String>> inputOrder() {
        return retryOnError(() -> {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

            return parser.parse(Console.readLine());
        });
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
