package service;

public class SimpleTask extends Task {

    public SimpleTask(String description, String name) {
        super(description, name);
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }
}
