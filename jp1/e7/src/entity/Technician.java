package entity;

import java.util.regex.Pattern;

public class Technician extends Staff {
    
    protected double overtimePay;

    public Technician() {}
    public Technician(String name, double salary, double overtimePay) {
        super(name, salary);
        this.overtimePay = overtimePay;
    }

    @Override
    public double getPaid() {
        return getSalary() + overtimePay;
    }

    @Override
    public String toString() {
        return "Technician [name=" + getName() + ", salary=" + getSalary() + ", overtimePay=" + overtimePay + "]";
    }
    
}
