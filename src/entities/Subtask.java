package entities;

public class Subtask extends Task{
    private int epicId;

    public Subtask(int id, String title, String description, int epicId){
        super(id, title, description);
        this.epicId = epicId;
    }

    public int getEpicId(){
        return epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
