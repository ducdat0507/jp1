package Service;

import Entity.Customer;
import Entity.Staff;
import Global.Global;
import IGeneric.IGeneral;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaffService implements IGeneral<Staff> {
    private List<Staff> staffs;
    public StaffService(List<Staff> staffs){
        this.staffs = staffs;
    }
    @Override
    public Optional<Staff> getById(int id) {
        Optional<Staff> staff = staffs.stream()
                .filter(c->c.getId() == id)
                .findFirst();
        return staff;
    }
    @Override
    public Optional<Staff> getByCode(String code) {
        Optional<Staff> staff = staffs.stream()
                .filter(c->c.getCode().equalsIgnoreCase(code))
                .findFirst();
        return staff;
    }

    @Override
    public List<Staff> getByName(String keyword) {
        return staffs.stream()
                .filter(c->Global.ignoreCase(c.getName(), keyword))
                .collect(Collectors.toList());
    }
}
