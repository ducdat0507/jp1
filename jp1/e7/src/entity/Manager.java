package entity;

public class Manager extends Staff {
    
    protected double allowance;

    public Manager() {}
    public Manager(String name, double salary, double allowance) {
        super(name, salary);
        this.allowance = allowance;
    }

    @Override
    public double getPaid() {
        return getSalary() + allowance;
    }

    @Override
    public String toString() {
        return "Manager [name=" + getName() + ", salary=" + getSalary() + ", allowance=" + allowance + "]";
    }
}
