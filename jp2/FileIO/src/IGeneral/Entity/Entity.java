package IGeneral.Entity;

public class Entity {
    protected static final String PROPERTY_SEPARATOR = ";";

    protected long id;

    public Entity() {}
    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        if (this.id != 0) throw new IllegalStateException("ID is already set");
        this.id = id;
    }
}
