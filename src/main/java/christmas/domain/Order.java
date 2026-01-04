package christmas.domain;

public class Order {
    private final Menu menu;
    private int amount;

    public Order(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getAmount() {
        return amount;
    }
}
