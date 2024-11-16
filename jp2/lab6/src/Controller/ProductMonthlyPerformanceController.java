package Controller;

import Entity.ProductMonthlyPerformance;
import IGeneral.Entity.PersistentEntityController;
import Service.ProductMonthlyPerformanceService;

public class ProductMonthlyPerformanceController extends PersistentEntityController<ProductMonthlyPerformanceService, ProductMonthlyPerformance> {
    public ProductMonthlyPerformanceController() {
        super(new ProductMonthlyPerformanceService());
    }
}
