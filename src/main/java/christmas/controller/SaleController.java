package christmas.controller;

import christmas.repository.MenuRepository;
import christmas.service.OrderService;
import christmas.service.SaleService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class SaleController {
    private InputView inputView;
    private OutputView outputView;
    private SaleService saleService;
    private OrderService orderService;
    private MenuRepository menuRepository;

    public SaleController(InputView inputView, OutputView outputView,
                          SaleService saleService, OrderService orderService, MenuRepository menuRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.saleService = saleService;
        this.menuRepository = menuRepository;
    }

    // <증정 메뉴>
    //없음
    public void present() {
        int totalAmount = orderService.totalAmount();
        String present = saleService.givePresent(totalAmount);
        outputView.printPresent(present);
    }

    // <혜택 내역>
    public void saleList() {
        // 혜택 내역을 가져온다.
        //
    }

    //<총혜택 금액>
    //0원

    //<할인 후 예상 결제 금액>
    //8,500원

    //<12월 이벤트 배지>
    //없음
}
