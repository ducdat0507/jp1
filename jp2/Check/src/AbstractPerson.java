import Interface.Identifiable;

public abstract class AbstractPerson implements Identifiable {
    protected String id;
    protected String name;
    protected int age;

    public AbstractPerson(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "AbstractPerson [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    public abstract void displayRole();
}
