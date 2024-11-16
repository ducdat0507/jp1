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

    public String toSaveString() {
        return Long.toString(id);
    }

    public void parse (String line) {
        String[] props = line.split(Entity.PROPERTY_SEPARATOR);
        setId(Long.parseLong(props[0]));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entity other = (Entity) obj;
        if (id != other.id)
            return false;
        return true;
    }

    
}
