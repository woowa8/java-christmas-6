package christmas;

import christmas.repository.MenuRepository;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InitData initData = new InitData(new MenuRepository());    // 메뉴 데이터 추가
    }
}
