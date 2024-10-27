package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import entity.Staff;

public class StaffController {
    private final List<Staff> staffs;

    public StaffController () {
        staffs = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        staffs.add(staff);
    }

    public void payStaff(String name) {
        Pattern pattern = Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE);
        staffs.stream().filter(x -> pattern.matcher(x.getName()).find()).forEach(x -> {
            System.out.println("Paid " + x.getPaid() + " to " + x.toString());
        });
    }

    public void displayOne() {
        staffs.stream().findAny().ifPresent(System.out::println);
    }
}
