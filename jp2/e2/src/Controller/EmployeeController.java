package Controller;

import java.time.Month;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Common.Common;
import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import Service.EmployeeService;
import Template.Entity.EntityController;

public class EmployeeController extends EntityController<EmployeeService, Employee> {
    public EmployeeController() {
        super(new EmployeeService());
    }

    public Set<Employee> searchByName(String query) {
        var prec = Common.fuzzyMatch(query);
        
        return stream().filter(x -> prec.test(x.getName())).collect(Collectors.toSet());
    }

    public Map<String, Set<Employee>> groupByDepartmentName() {
        return stream().collect(Collectors.groupingBy(
            x -> x.getDepartment().getName(),
            Collectors.toSet()
        ));
    }

    public Map<String, Long> countByDepartmentName() {
        return stream().collect(Collectors.groupingBy(
            x -> x.getDepartment().getName(),
            Collectors.counting()
        ));
    }

    public long countMaleByDepartment(Department dep) {
        return stream().filter(
            x -> x.getGender() == Gender.MALE && x.getDepartment().equals(dep)
        ).count();
    }

    public Set<Employee> getBirthdayInMonth(Month month) {
        return stream().filter(
            x -> x.getDateOfBirth().getMonth() == month
        ).collect(Collectors.toSet());
    }
}
