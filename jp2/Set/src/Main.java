import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, String> brands = new HashMap();
        brands.put("AP001", "Apple");
        brands.put("SS001", "Samsung");
        brands.put(null, null);

        System.out.print("Enter key: ");
        String key = System.console().readLine().trim();

        String categoryName = brands.entrySet().stream().filter(b -> b.getKey() == key).findFirst()
            .map(x -> x.getValue()).orElse("Brand does not exist");

        System.out.println(categoryName);
    }
}
