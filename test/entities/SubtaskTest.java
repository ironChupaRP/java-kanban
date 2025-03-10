package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    //Сабтаску нельзя сделать эпиком
    @Test
    void subtaskCannotBeItsOwnEpic() {
        TaskManager manager = Managers.getDefault();
        Subtask subtask = new Subtask(0, "Подзадача", "Описание подзадачи", 0);
    }

}