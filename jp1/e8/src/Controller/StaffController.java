package Controller;

import java.util.List;
import java.util.Optional;

import Entity.Staff;
import Service.StaffService;

public class StaffController {
    private StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    public Optional<Staff> getById(int id){
        return service.getById(id);
    }
    public Optional<Staff> getByCode(String code){
        return service.getByCode(code);
    }
    public List<Staff> getByName(String keyword) {
        return service.getByName(keyword);
    }
}
