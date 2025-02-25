package entities;
import java.util.ArrayList;


public class Epic extends Task{
    public ArrayList<Integer> subtaskIds;

    public Epic(int id, String title, String descrioption){
        super(id, title, descrioption);
        this.subtaskIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtaskIds(){
        return subtaskIds;
    }

    public void addSubtask(int subtaskId){
        subtaskIds.add(subtaskId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtaskIds=" + subtaskIds +
                '}';
    }
}
