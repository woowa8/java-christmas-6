package christmas.domain;

public enum Badge {
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE);

    private String badgeName;
    private int moreThan;    // 이상
    private int lessThan;    // 미만

    Badge(String badgeName, int moreThan, int lessThan) {
        this.badgeName = badgeName;
        this.moreThan = moreThan;
        this.lessThan = lessThan;
    }
}
