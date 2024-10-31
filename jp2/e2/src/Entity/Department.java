package Entity;

import Template.Entity.Entity;

public class Department extends Entity {
    private String name;

    public Department() {
        super();
    }
    public Department(String name) {
        super();
        this.name = name;
    }
    public Department(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", name=" + name + "]";
    }
}
