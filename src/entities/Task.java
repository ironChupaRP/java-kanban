package entities;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;

    public Task(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public TaskStatus getStatus(){
        return status;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
