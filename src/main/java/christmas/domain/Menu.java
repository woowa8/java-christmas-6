package christmas.domain;

public class Menu {
    private final String name;
    private Category category;
    private int price;

    public Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
