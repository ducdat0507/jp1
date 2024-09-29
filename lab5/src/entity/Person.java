package entity;

import java.time.LocalDate;

public class Person {
    private String name;
    private String address;
    private Gender gender;
    private LocalDate birthdate;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public int getAge() {
        return LocalDate.now().getYear() - this.birthdate.getYear();
    }

    public Person(String name, String address, Gender gender, LocalDate birthdate) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", address=" + address + ", gender=" + gender + ", birthdate=" + birthdate + ", age=" + getAge()
                + "]";
    }
}
