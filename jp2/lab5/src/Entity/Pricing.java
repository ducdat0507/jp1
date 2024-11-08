package Entity;

import java.time.LocalDateTime;

public class Pricing extends IGeneral.Entity.Entity {
    private Ticker ticker;
    private LocalDateTime dateTime;
    private double openPrice;
    private double closePrice;
    private double currentPrice;

    public Pricing() {}
    public Pricing(Ticker ticker, LocalDateTime dateTime, double openPrice, double closePrice, double currentPrice) {
        this.ticker = ticker;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.currentPrice = currentPrice;
    }
    public Pricing(String id, Ticker ticker, LocalDateTime dateTime, double openPrice, double closePrice,
            double currentPrice) {
        super(id);
        this.ticker = ticker;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.currentPrice = currentPrice;
    }
    
    public Ticker getTicker() {
        return ticker;
    }
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public double getOpenPrice() {
        return openPrice;
    }
    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }
    public double getClosePrice() {
        return closePrice;
    }
    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Pricing [id=" + id + ", ticker=" + ticker + ", dateTime=" + dateTime + ", openPrice=" + openPrice
                + ", closePrice=" + closePrice + ", currentPrice=" + currentPrice + "]";
    }
}
