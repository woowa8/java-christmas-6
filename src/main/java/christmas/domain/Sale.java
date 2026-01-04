package christmas.domain;

public enum Sale {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 1_000, 100),
    WEEKDAYS("평일 할인", 2_023, 0),
    WEEKEND("주말 할인", 2_023, 0),
    SPECIAL("특별 할인",1_000,0),
    TAKE("증정 이벤트",25_000,0);

    private final String saleName;
    private final int saleAmount;
    private final int plusAmount;

    Sale(String saleName, int saleAmount, int plusAmount) {
        this.saleName = saleName;
        this.saleAmount = saleAmount;
        this.plusAmount = plusAmount;
    }

    public String getSaleName() {
        return saleName;
    }

    public static int getAmount(Sale sale, int day) {
        switch (sale) {
            case CHRISTMAS_D_DAY :
                return sale.saleAmount + sale.plusAmount * (day-1);
        }
        return sale.saleAmount;
    }
}
