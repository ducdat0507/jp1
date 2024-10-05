import Controller.CustomerController;
import Controller.StaffController;
import Entity.Customer;
import Entity.Gender;
import Entity.Staff;
import Service.CustomerService;
import Service.StaffService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<Customer>();
        CustomerService cs = new CustomerService(customers);
        CustomerController customerController = new CustomerController(cs);

        List<Staff> staffs = new ArrayList<Staff>();
        StaffService ss = new StaffService(staffs);
        StaffController staffController = new StaffController(ss);

        customers.add(new Customer(1,"VCB01", "Binh", Gender.MALE));
        customers.add(new Customer(2,"TCB002","Minh", Gender.FEMALE));

        staffs.add(new Staff(1,"VCB01", "Binh", LocalDateTime.of(2002, 1, 10, 0, 0, 0)));
        staffs.add(new Staff(2,"TCB002","Minh", LocalDateTime.of(2002, 1, 10, 0, 0, 0)));

        Optional<Customer> cus = customerController.getById(2);
        if(cus.isPresent()){
            System.out.println(cus.get());
        }else{
            System.out.println("Not Found");
        }

        Optional<Staff> staff = staffController.getById(2);
        if(staff.isPresent()){
            System.out.println(staff.get());
        }else{
            System.out.println("Not Found");
        }
    }
}