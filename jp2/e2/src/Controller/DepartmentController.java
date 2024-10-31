package Controller;

import Entity.Department;
import Service.DepartmentService;
import Template.Entity.EntityController;

public class DepartmentController extends EntityController<DepartmentService, Department> {
    public DepartmentController() {
        super(new DepartmentService());
    }
}
