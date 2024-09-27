package entity;

import java.time.LocalDate;

public class Student extends Person {
    public String studentId;

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Student(String studentId, String name, String address, Gender gender, LocalDate birthdate) {
        super(name, address, gender, birthdate);
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + getName() + ", address=" + getAddress()
                + ", gender=" + getGender() + ", birthdate=" + getBirthdate() + ", age=" + getAge() + "]";
    }
}
