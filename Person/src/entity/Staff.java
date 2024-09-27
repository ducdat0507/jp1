package entity;

import java.time.LocalDate;

public class Staff extends Person {
    private String staffId;
    private double salary;

    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Staff(String staffId, String name, String address, Gender gender, LocalDate birthdate, double salary) {
        super(name, address, gender, birthdate);
        this.staffId = staffId;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff [staffId=" + staffId + ", name=" + getName() + ", address=" + getAddress()
                + ", gender=" + getGender() + ", birthdate=" + getBirthdate() + ", age=" + getAge() + ", salary=" + salary + "]";
    }
}
