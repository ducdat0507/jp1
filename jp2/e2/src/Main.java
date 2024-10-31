

import java.time.LocalDate;
import java.time.LocalDateTime;

import Controller.DepartmentController;
import Controller.EmployeeController;
import Controller.MainController;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;

public class Main {
    public static void main(String[] args) {
        MainController controller = new MainController();
        DepartmentController deps = controller.departments();
        deps.add(new Department[] {
            new Department("HR", "Human Resources"),
            new Department("IT", "Information Technology"),
        });
        EmployeeController emps = controller.employees();
        emps.add(new Employee[] {
            new Employee("A", deps.get("HR"), Gender.MALE, LocalDate.of(2004, 10, 2)),
            new Employee("B", deps.get("HR"), Gender.FEMALE, LocalDate.of(2002, 9, 23)),
            new Employee("C", deps.get("IT"), Gender.MALE, LocalDate.of(1998, 7, 14)),
            new Employee("D", deps.get("IT"), Gender.FEMALE, LocalDate.of(2000, 8, 31)),
        });
        deps.stream().forEach(System.out::println);
        emps.stream().forEach(System.out::println);
    }
}