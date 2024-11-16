package Service;

import java.io.File;

import Entity.ProductDailyPerformance;
import IGeneral.Entity.PersistentEntityService;

public class ProductDailyPerformanceService extends PersistentEntityService<ProductDailyPerformance> {

    public ProductDailyPerformanceService () {
        super(new File(System.getProperty("user.dir"), "jp2/lab7/data/product_daily_perf.in.txt"));
    }

    @Override
    protected ProductDailyPerformance instanceT(String line) {
        ProductDailyPerformance item = new ProductDailyPerformance();
        item.parse(line);
        return item;
    }
}
