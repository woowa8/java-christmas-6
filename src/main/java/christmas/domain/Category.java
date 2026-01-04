package christmas.domain;

public enum Category {
    APPETIZER("애피타이저"), MAIN("메인"), DESERT("디저트"), DRINKS("음료");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
