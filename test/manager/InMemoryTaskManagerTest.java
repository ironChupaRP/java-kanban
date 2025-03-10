package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {


    //Проверка добавлени язадач разного типа и поиск по ID
    @Test
    void taskManagerShouldAddAndFindTasks() {
        TaskManager manager = Managers.getDefault();

        Task task = manager.createTask(new Task(0, "Задача", "Описание задачи"));
        Epic epic = manager.createEpic(new Epic(0, "Эпик", "Описание эпика"));
        Subtask subtask = manager.createSubtask(new Subtask(0, "Подзадача", "Описание подзадачи", epic.getId()));

        assertNotNull(manager.getTaskById(task.getId()), "Задача должна быть найдена по id");
        assertNotNull(manager.getEpicById(epic.getId()), "Эпик должен быть найден по id");
        assertNotNull(manager.getSubtaskById(subtask.getId()), "Подзадача должна быть найдена по id");
    }








}