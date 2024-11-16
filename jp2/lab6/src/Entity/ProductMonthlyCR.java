package Entity;

import java.time.YearMonth;

import IGeneral.Entity.Entity;

public class ProductMonthlyCR extends Entity {
    private long productId;
    private YearMonth period;
    private double addToCartCR;
    private double checkoutCR;

    public ProductMonthlyCR() {}
    public ProductMonthlyCR(long productId, YearMonth period) {
        this.productId = productId;
        this.period = period;
    }
    public ProductMonthlyCR(long id, long productId, YearMonth period) {
        super(id);
        this.productId = productId;
        this.period = period;
    }
    public ProductMonthlyCR(long productId, YearMonth period, double addToCartCR, double checkoutCR) {
        this.productId = productId;
        this.period = period;
        this.addToCartCR = addToCartCR;
        this.checkoutCR = checkoutCR;
    }
    public ProductMonthlyCR(long id, long productId, YearMonth period, double addToCartCR, double checkoutCR) {
        super(id);
        this.productId = productId;
        this.period = period;
        this.addToCartCR = addToCartCR;
        this.checkoutCR = checkoutCR;
    }
    public ProductMonthlyCR(ProductMonthlyPerformance data) {
        this.productId = data.getProductId();
        this.period = data.getPeriod();
        this.addToCartCR = data.getAddToCartCount() / (double)data.getClickCount();
        this.checkoutCR = data.getCheckoutCount() / (double)data.getClickCount();
    }
    public ProductMonthlyCR(long id, ProductMonthlyPerformance data) {
        this.id = id;
        this.productId = data.getProductId();
        this.period = data.getPeriod();
        this.addToCartCR = data.getAddToCartCount() / (double)data.getClickCount();
        this.checkoutCR = data.getCheckoutCount() / (double)data.getClickCount();
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
    public double getAddToCartCR() {
        return addToCartCR;
    }
    public void setAddToCartCR(double addToCartCR) {
        this.addToCartCR = addToCartCR;
    }
    public double getCheckoutCR() {
        return checkoutCR;
    }
    public void setCheckoutCR(double checkoutCR) {
        this.checkoutCR = checkoutCR;
    }

    @Override
    public String toSaveString() {
        return 
              id + Entity.PROPERTY_SEPARATOR
            + productId + Entity.PROPERTY_SEPARATOR
            + period + Entity.PROPERTY_SEPARATOR
            + addToCartCR + Entity.PROPERTY_SEPARATOR
            + checkoutCR + Entity.PROPERTY_SEPARATOR
        ;
    }

    @Override
    public void parse(String line) {
        String[] props = line.split(Entity.PROPERTY_SEPARATOR);
        setId(Long.parseLong(props[0]));
        setProductId(Long.parseLong(props[1]));
        setPeriod(YearMonth.parse(props[2]));
        setAddToCartCR(Long.parseLong(props[3]));
        setCheckoutCR(Long.parseLong(props[4]));
    }
}
