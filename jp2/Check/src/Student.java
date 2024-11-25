import Interface.Learner;

public class Student extends AbstractPerson implements Learner {
    private String major;

    public Student(String id, String name, int age, String major) {
        super(id, name, age);
        this.major = major;
    }

    @Override
    public void displayRole() {
        System.out.println("Student " + name + "");
    }

    @Override
    public void study() {
        System.out.println("E");
    }
}
