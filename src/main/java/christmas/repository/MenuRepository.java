package christmas.repository;

import christmas.domain.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private List<Menu> menuList;

    public MenuRepository() {
        this.menuList = new ArrayList<>(menuList);
    }

    public void add(Menu menu) {
        menuList.add(menu);
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu findMenuByName(String menuName) {
        return menuList.stream()
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst().get();
    }
}
