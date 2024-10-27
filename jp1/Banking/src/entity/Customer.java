package entity;

public class Customer {
    private String id;
    private String name;
    private Gender gender;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Gender getGender() {
        return gender;
    }


    @Override
    public String toString() {
        return name + "(" + id + ")";
    }

    public Customer(String id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
    
}
