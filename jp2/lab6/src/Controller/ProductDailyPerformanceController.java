package Controller;

import Entity.ProductDailyPerformance;
import IGeneral.Entity.PersistentEntityController;
import Service.ProductDailyPerformanceService;

public class ProductDailyPerformanceController extends PersistentEntityController<ProductDailyPerformanceService, ProductDailyPerformance> {
    public ProductDailyPerformanceController() {
        super(new ProductDailyPerformanceService());
    }
}
