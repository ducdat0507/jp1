package Controller;

import Entity.Ticker;
import IGeneral.Entity.EntityController;
import Service.TickerService;

public class TickerController extends EntityController<TickerService, Ticker> {
    
    public TickerController() {
        super(new TickerService());
    }
}
