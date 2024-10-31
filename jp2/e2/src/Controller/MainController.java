package Controller;

public class MainController {
    private DepartmentController departments;
    private EmployeeController employees;

    public MainController() {
        departments = new DepartmentController();
        employees = new EmployeeController();
    }

    public DepartmentController departments() {
        return departments;
    }
    public EmployeeController employees() {
        return employees;
    }
}
