package Entity;

import java.time.LocalDateTime;

public class Staff {
    private int id;
    private String code;
    private String name;
    private LocalDateTime birthdate;

    public Staff() {}
    public Staff(int id, String code, String name, LocalDateTime birthdate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.birthdate = birthdate;
    }
    public int getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getBirthdate() {
        return birthdate;
    }
    
    @Override
    public String toString() {
        return "Staff [id=" + id + ", code=" + code + ", name=" + name + ", birthdate=" + birthdate + "]";
    }
    
}
