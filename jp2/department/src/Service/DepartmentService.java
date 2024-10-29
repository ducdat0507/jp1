package Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Common.Common;
import Entity.Department;
import Entity.Employee;
import IGeneral.IGenericService;

public class DepartmentService implements IGenericService<Department> {
    private Set<Department> set;

    public DepartmentService() {
        set = new HashSet<>();
    }

    @Override
    public void add(Department item) {
        set.add(item);
    }

    @Override
    public void remove(Department item) {
        set.remove(item);
    }

    @Override
    public Set<Department> searchByName(String query) {
        Predicate<String> matcher = Common.fuzzyMatch(query);
        return set.stream().filter(x -> matcher.test(x.getName()))
            .collect(Collectors.toSet());
    }

}
