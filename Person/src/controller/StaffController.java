package controller;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.*;

import entity.Gender;
import entity.Staff;

public class StaffController extends ArrayList<Staff> {
    
    public Optional<Staff> getFemaleWithHighestSalary() {
        return stream().filter(x -> x.getGender() == Gender.FEMALE).max(Comparator.comparing(Staff::getSalary));
    }

    public Optional<Staff> getById(String id) {
        return stream().filter(x -> x.getStaffId().equals(id)).findAny();
    }

    public Stream<Staff> getByName(String query) {
        Pattern regex = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        return stream().filter(x -> regex.matcher(x.getName()).find());
    }
}
