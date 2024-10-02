package entity;

public abstract class Staff {

    private String name;
    private double salary;

    public Staff() {}
    public Staff(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }

    public abstract double getPaid();
    @Override
    public abstract String toString();
    
}
