package Entity;

import java.time.YearMonth;

import IGeneral.Entity.Entity;

public class ProductMonthlyPerformance extends Entity {
    private long productId;
    private YearMonth period;
    private long clickCount;
    private long addToCartCount;
    private long checkoutCount;

    public ProductMonthlyPerformance() {}
    public ProductMonthlyPerformance(long productId, YearMonth period) {
        this.productId = productId;
        this.period = period;
    }
    public ProductMonthlyPerformance(long id, long productId, YearMonth period) {
        super(id);
        this.productId = productId;
        this.period = period;
    }
    public ProductMonthlyPerformance(long productId, YearMonth period, long clickCount, long addToCartCount,
            long checkoutCount) {
        this.productId = productId;
        this.period = period;
        this.clickCount = clickCount;
        this.addToCartCount = addToCartCount;
        this.checkoutCount = checkoutCount;
    }
    public ProductMonthlyPerformance(long id, long productId, YearMonth period, long clickCount, long addToCartCount,
            long checkoutCount) {
        super(id);
        this.productId = productId;
        this.period = period;
        this.clickCount = clickCount;
        this.addToCartCount = addToCartCount;
        this.checkoutCount = checkoutCount;
    }

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public YearMonth getPeriod() {
        return period;
    }
    public void setPeriod(YearMonth period) {
        this.period = period;
    }
    public long getClickCount() {
        return clickCount;
    }
    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }
    public long getAddToCartCount() {
        return addToCartCount;
    }
    public void setAddToCartCount(long addToCartCount) {
        this.addToCartCount = addToCartCount;
    }
    public long getCheckoutCount() {
        return checkoutCount;
    }
    public void setCheckoutCount(long checkoutCount) {
        this.checkoutCount = checkoutCount;
    }

    @Override
    public String toString() {
        return "ProductPerformance [id=" + id + ", productId=" + productId + ", period=" + period + ", clickCount=" + clickCount
                + ", addToCartCount=" + addToCartCount + ", checkoutCount=" + checkoutCount + "]";
    }

    @Override
    public String toSaveString() {
        return 
              id + Entity.PROPERTY_SEPARATOR
            + productId + Entity.PROPERTY_SEPARATOR
            + period + Entity.PROPERTY_SEPARATOR
            + clickCount + Entity.PROPERTY_SEPARATOR
            + addToCartCount + Entity.PROPERTY_SEPARATOR
            + checkoutCount + Entity.PROPERTY_SEPARATOR
        ;
    }

    @Override
    public void parse(String line) {
        String[] props = line.split(Entity.PROPERTY_SEPARATOR);
        setId(Long.parseLong(props[0]));
        setProductId(Long.parseLong(props[1]));
        setPeriod(YearMonth.parse(props[2]));
        setClickCount(Long.parseLong(props[3]));
        setAddToCartCount(Long.parseLong(props[4]));
        setCheckoutCount(Long.parseLong(props[5]));
    }
}
