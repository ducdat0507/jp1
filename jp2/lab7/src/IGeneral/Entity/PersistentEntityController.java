package IGeneral.Entity;

import java.util.stream.Stream;

import IGeneral.IGenericController;

public class PersistentEntityController<S extends PersistentEntityService<T>, T extends Entity> extends EntityController<S, T> {
    
    public PersistentEntityController(S service) {
        super(service);
    }

    public void load() {
        service.load();
    }

    public void save() {
        service.save();
    }
    
}
