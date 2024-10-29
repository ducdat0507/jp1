import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(120);
        set.add(121);
        set.add(122);
        set.add(123);
        set.add(124);
        set.add(125);
        set.add(126);
        set.add(123456789);
        set = set.stream().filter(Main::divisionByThree).collect(Collectors.toSet());
        System.out.println(set);
    }

    private static Boolean divisionByThree(int num) {
        return Integer.remainderUnsigned(num, 3) == 0;
    }
}
