package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    //ПРоверка инициализации TaskManager
    @Test
    void getDefaultShouldReturnInitializedTaskManager() {
        TaskManager manager = Managers.getDefault();
        assertNotNull(manager, "Менеджер задач должен быть проинициализирован");
    }

    //ПРоверка инициализации HistoryManager
    @Test
    void getDefaultHistoryShouldReturnInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "Менеджер истории должен быть проинициализирован");
    }

    //Проверка конфликта задач с разынми ID
    @Test
    void tasksWithAssignedAndGeneratedIdsShouldNotConflict() {
        TaskManager manager = Managers.getDefault();

        Task task1 = manager.createTask(new Task(10, "Задача 1", "Описание задачи 1"));
        Task task2 = manager.createTask(new Task(0, "Задача 2", "Описание задачи 2"));

        assertNotEquals(task1.getId(), task2.getId(), "Задачи с заданным и сгенерированным id не должны конфликтовать");
    }

    //Проверка неизменности задачи
    @Test
    void taskShouldRemainUnchangedAfterAddingToManager() {
        TaskManager manager = Managers.getDefault();
        Task task = new Task(0, "Задача", "Описание задачи");

        Task addedTask = manager.createTask(task);
        assertEquals(task.getTitle(), addedTask.getTitle(), "Название задачи не должно измениться");
        assertEquals(task.getDescription(), addedTask.getDescription(), "Описание задачи не должно измениться");
        assertEquals(task.getStatus(), addedTask.getStatus(), "Статус задачи не должен измениться");
    }

}