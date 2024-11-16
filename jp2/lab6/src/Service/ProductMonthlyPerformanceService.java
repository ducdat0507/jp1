package Service;

import java.io.File;

import Entity.ProductMonthlyPerformance;
import IGeneral.Entity.PersistentEntityService;

public class ProductMonthlyPerformanceService extends PersistentEntityService<ProductMonthlyPerformance> {

    public ProductMonthlyPerformanceService () {
        super(new File(System.getProperty("user.dir"), "jp2/lab7/data/product_monthly_perf.in.txt"));
    }

    @Override
    protected ProductMonthlyPerformance instanceT(String line) {
        ProductMonthlyPerformance item = new ProductMonthlyPerformance();
        item.parse(line);
        return item;
    }
}
