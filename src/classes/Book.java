package classes;

public class Book {
    private String name;
    public String getName() {
        return name;
    }

    private Author author;
    public Author getAuthor() {
        return author;
    }
    
    private double price;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    private int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(String name, Author author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.quantity = 0;
    }
    public Book(String name, Author author, double price, int quantity) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Book [name=" + name + ", author=" + author + ", price=" + price + ", quantity=" + quantity + "]";
    }

    
}
