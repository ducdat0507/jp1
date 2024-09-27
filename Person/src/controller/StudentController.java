package controller;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.*;

import entity.Staff;

public class StudentController extends ArrayList<Staff> {

    public Optional<Staff> getYoungest() {
        return stream().max(Comparator.comparing(Staff::getBirthdate));
    }

    public Stream<Staff> getStudentByName(String query) {
        Pattern regex = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        return stream().filter(x -> regex.matcher(x.getName()).find());
    }
}
