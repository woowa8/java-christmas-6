package christmas.domain;

public enum Badge {
    STAR("별"), TREE("트리"), SANTA("산타");

    private String displayName;

    Badge(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
