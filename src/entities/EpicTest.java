package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    //Проверка задач с одинаковым ID
    @Test
    void tasksWithSameIdShouldBeEqual() {
        // Создаем две задачи с одинаковым id
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1");
        Task task2 = new Task(1, "Задача 2", "Описание задачи 2");

        // Проверяем, что задачи равны
        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    //Проверка задач с одинаковым описанием
    @Test
    void tasksWithDifferentIdsShouldNotBeEqual() {
        // Создаем две задачи с разными id
        Task task1 = new Task(1, "Задача 1", "Описание задачи 1");
        Task task2 = new Task(2, "Задача 1", "Описание задачи 1");

        // Проверяем, что задачи не равны
        assertNotEquals(task1, task2, "Задачи с разными id не должны быть равны");
    }


    //Наследники равны если равны их ID
    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Subtask subtask1 = new Subtask(1, "Подзадача 1", "Описание подзадачи 1", 0);
        Subtask subtask2 = new Subtask(1, "Подзадача 2", "Описание подзадачи 2", 0);
        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны");
    }

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic epic1 = new Epic(1, "Эпик 1", "Описание эпика 1");
        Epic epic2 = new Epic(1, "Эпик 2", "Описание эпика 2");
        assertEquals(epic1, epic2, "Эпики с одинаковым id должны быть равны");
    }

    //Эпик нельзя добавить в самого себя
    @Test
    void epicCannotBeAddedAsItsOwnSubtask() {
        TaskManager manager = Managers.getDefault();
        Epic epic = manager.createEpic(new Epic(0, "Эпик", "Описание эпика"));

        // Попытка создать подзадачу, которая ссылается на саму себя как на эпик
        Subtask subtask = new Subtask(0, "Подзадача", "Описание подзадачи", epic.getId());

    }

    //Сабтаску нельзя сделать эпиком
    @Test
    void subtaskCannotBeItsOwnEpic() {
        TaskManager manager = Managers.getDefault();
        Subtask subtask = new Subtask(0, "Подзадача", "Описание подзадачи", 0);
    }

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