import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter a number:");
            double x = sc.nextDouble();
        } catch (InputMismatchException | ArithmeticException e) {
            System.out.println(e.toString());
        } finally {
            System.out.println("Valid number!");
        }
    }
}