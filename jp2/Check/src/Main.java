import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<AbstractPerson> people = new ArrayList<>();
        people.add(new Student("S001", "A", 18, "Computer Science"));
        people.add(new Teacher("T001", "B", 27, "Computer Science"));

        for (AbstractPerson person : people) {
            System.out.println("id: " + person.getId());
            System.out.println("name: " + person.getName());
            System.out.println("age: " + person.getAge());
        }
    }
}
