package Controller;

import java.util.Optional;

import Entity.Staff;
import Service.StaffService;

public class StaffController {
    private StaffService cs;
    public StaffController(StaffService cs) {
        this.cs = cs;
    }
    public Optional<Staff> getById(int id){
        return cs.getById(id);
    }
}
