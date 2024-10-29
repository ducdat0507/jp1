package Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Common.Common;
import Entity.Employee;
import IGeneral.IGenericService;

public class EmployeeService implements IGenericService<Employee> {
    private Set<Employee> set;

    public EmployeeService() {
        set = new HashSet<>();
    }

    @Override
    public void add(Employee item) {
        set.add(item);
    }

    @Override
    public void remove(Employee item) {
        set.remove(item);
    }

    @Override
    public Set<Employee> searchByName(String query) {
        Predicate<String> matcher = Common.fuzzyMatch(query);
        return set.stream().filter(x -> matcher.test(x.getName()))
            .collect(Collectors.toSet());
    }

    public Map<String, Set<Employee>> groupByDepartment() {
        return set.stream().collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.toSet()));
    }

    public Map<String, Long> countByDepartment() {
        return set.stream().collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.counting()));
    }
}
