import java.time.LocalDate;

import controller.StudentController;

import entity.Gender;
import entity.Student;

public class Main {
    public static void main(String[] args) {
        StudentController student = new StudentController ();
        student.add(new Student("1", "Le", "A", Gender.MALE, LocalDate.of(2002, 5, 24)));
        student.add(new Student("2", "Thanh", "B", Gender.MALE, LocalDate.of(2003, 1, 15)));
        student.add(new Student("3", "Vinh", "C", Gender.MALE, LocalDate.of(2005, 10, 8)));
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        student.forEach(System.out::println);
        System.out.println("-----");
        student.getYoungest().ifPresent(System.out::println);
        System.out.println("-----");
        student.getStudentByName("h").forEach(System.out::println);

        
    }
}
