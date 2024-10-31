package Entity;

import java.time.LocalDate;

import Template.Entity.Entity;

public class Employee extends Entity {
    private String name;
    private Department department;
    private Gender gender;
    private LocalDate dateOfBirth;

    public Employee() {
        super();
    }
    public Employee(String name, Department department, Gender gender, LocalDate dateOfBirth) {
        super();
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    public Employee(String id, String name, Department department, Gender gender, LocalDate dateOfBirth) {
        super(id);
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", name=" + name + ", department=" + department + ", gender=" + gender + ", dateOfBirth="
                + dateOfBirth + "]";
    }
}
