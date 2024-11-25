import Interface.Worker;

public class Teacher extends AbstractPerson implements Worker {
    private String subject;

    public Teacher(String id, String name, int age, String subject) {
        super(id, name, age);
        this.subject = subject;
    }

    @Override
    public void displayRole() {
        // TODO Auto-generated method stub
    }

    @Override
    public void joinMeeting() {
        // TODO Auto-generated method stub
    }

    @Override
    public void work() {
        // TODO Auto-generated method stub
    }
    

    
}
