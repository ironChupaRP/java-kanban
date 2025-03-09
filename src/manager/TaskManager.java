package manager;
import entities.Epic;
import entities.Task;
import entities.Subtask;
import java.util.ArrayList;
import java.util.List;


public interface TaskManager {

    // Получение списка всех задач
    ArrayList<Task> getAllTasks();

    // Удаление всех задач
    void deleteAllTasks();

    // Получение задачи по идентификатору
    Task getTaskById(int id);

    // Создание задачи
    Task createTask(Task task);

    // Обновление задачи
    void updateTask(Task task);

    // Удаление задачи по Id
    void deleteTaskById(int id);

    // Получение всех подзадач
    ArrayList<Subtask> getAllSubtasks();

    // Получение подзадачи по идентификатору
    Subtask getSubtaskById(int id);

    // Удаление подзадачи по Id
    void deleteSubtaskById(int id);

    // Получение всех эпиков
    ArrayList<Epic> getAllEpics();

    // Удаление всех эпиков и подзадач
    void deleteAllEpics();

    // Получение эпика по ID
    Epic getEpicById(int id);

    // Создание эпика
    Epic createEpic(Epic epic);

    // Обновление эпика
    void updateEpic(Epic epic);

    // Удаление эпика по ID
    void deleteEpicById(int id);

    // Удаление всех подзадач
    void deleteAllSubtasks();

    // Создание подзадачи и добавление в эпик
    Subtask createSubtask(Subtask subtask);


    // Обновление подзадачи
    void updateSubtask(Subtask subtask);

    // Получение подзадач по ID эпика
    ArrayList<Subtask> getSubtasksByEpicId(int epicId);

    //Получение истории
    List<Task> getHistory();
}