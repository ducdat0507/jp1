package Controller;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Entity.Department;
import Entity.Employee;
import IGeneral.IGenericController;
import Service.EmployeeService;

public class EmployeeController implements IGenericController<Employee> {
    private EmployeeService service;

    public EmployeeController() {
        service = new EmployeeService();
    }

    @Override 
    public void add(Employee item) {
        service.add(item);
    }

    @Override 
    public void remove(Employee item) {
        service.remove(item);
    }

    @Override
    public Set<Employee> searchByName(String query) {
        return service.searchByName(query);
    }

    public Map<String, Set<Employee>> groupByDepartment() {
        return service.groupByDepartment();
    }

    public Map<String, Long> countByDepartment() {
        return service.countByDepartment();
    }
}
