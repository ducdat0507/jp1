package Controller;

import Entity.Pricing;
import IGeneral.Entity.EntityController;
import Service.PricingService;

public class PricingController extends EntityController<PricingService, Pricing> {
    
    public PricingController() {
        super(new PricingService());
    }
}
