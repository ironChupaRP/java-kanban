package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    //ПРоверка сохранения предыдущей версии и данных
    @Test
    void historyManagerShouldPreserveTaskState() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        Task task = new Task(1, "Задача", "Описание задачи");

        historyManager.add(task);
        task.setStatus(TaskStatus.IN_PROGRESS);

        Task taskFromHistory = historyManager.getHistory().get(0);
        assertEquals(TaskStatus.NEW, taskFromHistory.getStatus(), "История должна сохранять предыдущую версию задачи");
    }

}