package christmas.repository;

import christmas.domain.Category;
import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private final List<Menu> menuList;

    public MenuRepository() {
        menuList = new ArrayList<>();
    }

    // 1. menu 추가하기
    public void addMenu(Menu menu) {
        menuList.add(menu);
    }

    // 2. 들어온 이름이 menu에 있는 건지 확인하기 (find)
    public Menu findMenuByName(String menuName) {
//        // TODO : 디버깅
//        for (Menu menu : menuList) {
//            System.out.println("menuName: " + menu.getName());
//        }

        return menuList.stream()
                .filter(menu -> menuName.equals(menu.getName()))
                .findFirst().orElse(null);
    }
}
