package Entity;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private String departmentId;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String city;
    private String province;
    private String phoneNumber;

    public Employee() {
    }

    public Employee(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    public Employee(String id, String name, String departmentId, LocalDate dateOfBirth, Gender gender, String city,
            String province, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", departmentId=" + departmentId + ", dateOfBirth="
                + dateOfBirth + ", gender=" + gender + ", city=" + city + ", province=" + province + ", phoneNumber="
                + phoneNumber + "]";
    }
    
    
}
