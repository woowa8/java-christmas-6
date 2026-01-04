package christmas.repository;

import christmas.domain.Order;
import christmas.domain.Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleRepository {
    private final List<Sale> totalSales;
    private final Map<Sale, Integer> totalSaleCnt;

    public SaleRepository() {
        this.totalSales = new ArrayList<>();
        this.totalSaleCnt = new HashMap<>();
    }

    public void addTotalSale(Sale sale) {
        totalSales.add(sale);
        totalSaleCnt.put(sale, totalSaleCnt.getOrDefault(sale, 0) + 1);
    }

    public List<Sale> findAllSales() {    // 전체 할인 금액을 계산하기 위해 필요
        return totalSales;
    }

    public Map<Sale, Integer> findTotalSaleCnt() {
        return totalSaleCnt;
    }
}
