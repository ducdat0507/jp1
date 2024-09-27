package controller;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.*;

import entity.Student;

public class StudentController extends ArrayList<Student> {

    public Optional<Student> getYoungest() {
        return stream().max(Comparator.comparing(Student::getBirthdate));
    }

    public Stream<Student> getStudentByName(String query) {
        Pattern regex = Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE);
        return stream().filter(x -> regex.matcher(x.getName()).find());
    }
}
