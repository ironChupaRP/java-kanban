package manager;

import entities.Task;
import java.util.List;

public interface HistoryManager {

    //Дабавляем задачу в историю
    void add(Task task);

    //возвращаем историю
    List<Task> getHistory();
}