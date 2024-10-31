package IGeneral.Entity;

public class Entity {
    protected String id;

    public Entity() {}
    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        if (this.id != null) throw new IllegalStateException("ID is already set");
        this.id = id;
    }
}
