package Controller;

import java.util.Set;

import Entity.Department;
import IGeneral.IGenericController;
import Service.DepartmentService;

public class DepartmentController implements IGenericController<Department> {
    private DepartmentService service;

    public DepartmentController() {
        service = new DepartmentService();
    }

    @Override 
    public void add(Department item) {
        service.add(item);
    }

    @Override 
    public void remove(Department item) {
        service.remove(item);
    }

    @Override
    public Set<Department> searchByName(String query) {
        return service.searchByName(query);
    }
}
