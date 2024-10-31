package Service;

import Entity.Employee;
import Template.Entity.EntityService;

public class EmployeeService extends EntityService<Employee> {
    private int nextId = 1;

    @Override
    protected String autoIncrement(Employee item) {
        return String.format("%06d", nextId++);
    }
}
