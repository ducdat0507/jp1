import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import classes.Author;
import classes.Book;
import classes.Gender;

public class App {

    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();

    private static void addAuthor(Author author) 
    {
        if (authors.stream().anyMatch(x -> x.getName() == author.getName()))
            System.err.println("Author already exists");
        else authors.add(author);
    }

    private static void addBook(Book book) 
    {
        if (books.stream().anyMatch(x -> x.getName() == book.getName()))
            System.err.println("Book already exists");
        else books.add(book);
    }

    public static void main(String[] args) {
        addAuthor(new Author("A", "a@a.com", Gender.MALE));
        addAuthor(new Author("B", "b@b.com", Gender.FEMALE));
        addAuthor(new Author("C", "c@c.com", Gender.MALE));
        addAuthor(new Author("D", "d@d.com", Gender.FEMALE));

        addBook(new Book("Aasdsd", authors.get(0), 1200, 100));
        addBook(new Book("Basfa", authors.get(1), 1300, 110));
        addBook(new Book("Cqwf", authors.get(2), 1400, 120));
        addBook(new Book("Ddsdsv", authors.get(3), 1100, 100));
        addBook(new Book("Xfqwf", authors.get(0), 1300, 130));
        addBook(new Book("Ygwbg", authors.get(1), 1800, 170));
        addBook(new Book("Zsdfa", authors.get(2), 1500, 120));
        addBook(new Book("Wsdvds.toLower()", authors.get(2), 1200, 150));

        System.out.println("Sorted book array:");
        books.stream()
            .sorted(Comparator.comparing(Book::getName))
            .forEach(System.out::println);

        System.out.println("\nBook with max price:");
        System.out.println(books.stream()
            .max(Comparator.comparing(Book::getPrice)).get());

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nName of author:");
        String authorName = scanner.nextLine().trim();
        System.out.println("------");
        books.stream()
            .filter(x -> x.getAuthor().getName().equals(authorName))
            .forEach(System.out::println);

        System.out.println("\nName of book:");
        String bookName = scanner.nextLine().trim();
        Pattern bookSearchPattern = Pattern.compile(Pattern.quote(bookName), Pattern.CASE_INSENSITIVE);
        System.out.println("------");
        books.stream()
            .filter(x -> bookSearchPattern.matcher(x.getName()).find())
            .forEach(System.out::println);
        scanner.close();
    }
}