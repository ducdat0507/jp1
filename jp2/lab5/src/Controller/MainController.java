package Controller;

public class MainController {
    private TickerController tickers;
    private PricingController pricings;

    public MainController () {
        tickers = new TickerController();
        pricings = new PricingController();
    }
}
