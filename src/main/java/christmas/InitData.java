package christmas;

import christmas.domain.Category;
import christmas.domain.Menu;
import christmas.repository.MenuRepository;

/*
메뉴를 구성하기 위한 클래스이다.
 */
public class InitData {
    private final MenuRepository menuRepository;

    public InitData(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void createMenu() {
        // 에피타이저 생성
        Menu a1 = new Menu(Category.APPETIZER, "양송이수프", 6000);
        Menu a2 = new Menu(Category.APPETIZER, "타파스", 5500);
        Menu a3 = new Menu(Category.APPETIZER, "시저샐러드", 8000);

        menuRepository.add(a1);
        menuRepository.add(a2);
        menuRepository.add(a3);

        // 메인 생성
        Menu m1 = new Menu(Category.MAIN, "티본스테이크", 55000);
        Menu m2 = new Menu(Category.MAIN, "바비큐립", 54000);
        Menu m3 = new Menu(Category.MAIN, "해산물파스타", 35000);
        Menu m4 = new Menu(Category.MAIN, "크리스마스파스타", 25000);

        menuRepository.add(m1);
        menuRepository.add(m2);
        menuRepository.add(m3);
        menuRepository.add(m4);

        // 디저트 생성
        Menu d1 = new Menu(Category.DESERT, "초코케이크", 15000);
        Menu d2 = new Menu(Category.DESERT, "아이스크림", 5000);

        menuRepository.add(d1);
        menuRepository.add(d2);

        // 음료 생성
        Menu dr1 = new Menu(Category.DRINKS, "제로콜라", 3000);
        Menu dr2 = new Menu(Category.DRINKS, "레드와인", 60000);
        Menu dr3 = new Menu(Category.DRINKS, "샴페인", 25000);

        menuRepository.add(dr1);
        menuRepository.add(dr2);
        menuRepository.add(dr3);
    }
}
