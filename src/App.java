import java.time.format.SignStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import classes.Author;
import classes.Book;
import classes.Customer;
import classes.Gender;

public class App {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    public static void main(String[] args) {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("A", "a@a.com", Gender.MALE));
        authors.add(new Author("B", "b@b.com", Gender.FEMALE));
        authors.add(new Author("C", "c@c.com", Gender.MALE));
        authors.add(new Author("D", "d@d.com", Gender.FEMALE));

        List<Book> books = new ArrayList<>();
        books.add(new Book("A", authors.get(0), 1200, 100));
        books.add(new Book("B", authors.get(1), 1300, 110));
        books.add(new Book("C", authors.get(2), 1400, 120));
        books.add(new Book("D", authors.get(3), 1100, 100));
        books.add(new Book("X", authors.get(0), 1300, 130));
        books.add(new Book("Y", authors.get(1), 1800, 170));
        books.add(new Book("Z", authors.get(2), 1500, 120));
        books.add(new Book("W", authors.get(2), 1200, 150));

        System.out.println("Sorted book array:");
        books.stream()
            .forEach(System.out::println);

        System.out.println("\nBook with max price:");
        System.out.println(books.stream()
            .max(Comparator.comparing(Book::getPrice)).get());

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nName of author:");
        String authorName = scanner.nextLine().trim();
        scanner.close();
        System.out.println("------");
        books.stream()
            .filter(x -> x.getAuthor().getName().equals(authorName))
            .forEach(System.out::println);

    }
}