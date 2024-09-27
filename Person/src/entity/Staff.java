package entity;

import java.time.LocalDate;

public class Staff extends Person {
    public String staffId;

    public String getStaffId() {
        return staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Staff(String staffId, String name, String address, Gender gender, LocalDate birthdate) {
        super(name, address, gender, birthdate);
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "Staff [staffId=" + staffId + ", name=" + getName() + ", address=" + getAddress()
                + ", gender=" + getGender() + ", birthdate=" + getBirthdate() + ", age=" + getAge() + "]";
    }
}
