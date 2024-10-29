import Controller.DepartmentController;
import Controller.EmployeeController;
import Entity.Department;
import Entity.Employee;

public class Main {
    public static void main(String[] args) {
        DepartmentController depCon = new DepartmentController();
        EmployeeController empCon = new EmployeeController();

        depCon.add(new Department("DEP00001", "Manager"));
        depCon.add(new Department("DEP00002", "Research & Development"));
        depCon.add(new Department("DEP00003", "Developer"));
        depCon.add(new Department("DEP00004", "Quality Assurance"));
        depCon.add(new Department("DEP00005", "Marketing"));

        empCon.add(new Employee("EMP00001", "A", "DEP00001"));
        empCon.add(new Employee("EMP00002", "BA", "DEP00002"));
        empCon.add(new Employee("EMP00003", "C", "DEP00003"));
        empCon.add(new Employee("EMP00004", "D", "DEP00004"));
        empCon.add(new Employee("EMP00005", "E", "DEP00005"));
        empCon.add(new Employee("EMP00006", "BF", "DEP00002"));
        empCon.add(new Employee("EMP00007", "G", "DEP00003"));
        empCon.add(new Employee("EMP00008", "H", "DEP00004"));
        empCon.add(new Employee("EMP00008", "I", "DEP00005"));

        System.out.println(empCon.searchByName("A"));
        System.out.println();
        System.out.println(empCon.groupByDepartment());
        System.out.println();
        System.out.println(empCon.countByDepartment());
    }
}
