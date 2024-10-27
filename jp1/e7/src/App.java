import controller.StaffController;
import entity.Manager;
import entity.Technician;

public class App {
    public static void main(String[] args) {
        StaffController controller = new StaffController();
        controller.addStaff(new Manager("ASdl ALsdk gwegef", 2000, 200));
        controller.addStaff(new Technician("asfqwr dfasld wewd", 1000, 100));
        controller.addStaff(new Technician("Fsdjw wfjslfw wdl", 1200, 80));

        System.out.print("Enter name to pay: ");
        String name = System.console().readLine().trim();
        controller.payStaff(name);

        System.out.print("\nDisplay one: ");
        controller.displayOne();
    }
}
