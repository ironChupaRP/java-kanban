package manager;
import entities.Epic;
import entities.Task;
import entities.Subtask;
import entities.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCounter;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public int generateId() {
        return ++idCounter;
    }

    // Получение списка всех задач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Удаление всех задач
    public void deleteAllTasks() {
        tasks.clear();
    }

    // Получение задачи по идентификатору
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    // Создание задачи
    public Task createTask(Task task) {
        if (task == null) {
            System.out.println("Ошибка: Задача не может быть null.");
            return null;
        }
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    // Обновление задачи
    public void updateTask(Task task) {
        if (task == null) {
            System.out.println("Ошибка: Задача не может быть null.");
            return;
        }
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    // Удаление задачи по Id
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    // Получение всех подзадач
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    // Получение подзадачи по идентификатору
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    // Удаление подзадачи по Id
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove((Integer) id);
                updateEpicStatus(epic.getId());
            }
        }
    }

    // Получение всех эпиков
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    // Удаление всех эпиков и подзадач
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    // Получение эпика по ID
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    // Создание эпика
    public Epic createEpic(Epic epic) {
        if (epic == null) {
            System.out.println("Ошибка: Эпик не может быть null.");
            return null;
        }
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    // Обновление эпика
    public void updateEpic(Epic epic) {
        if (epic == null) {
            System.out.println("Ошибка: Эпик не может быть null.");
            return;
        }
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            updateEpicStatus(epic.getId());
        }
    }

    // Удаление эпика по ID
    public void deleteEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            // Удаляем все подзадачи эпика
            for (int subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    // Удаление всех подзадач
    public void deleteAllSubtasks() {
        subtasks.clear();
        // Обновляем статусы всех эпиков
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateEpicStatus(epic.getId());
        }
    }

    // Создание подзадачи и добавление в эпик
    public Subtask createSubtask(Subtask subtask) {
        if (subtask == null) {
            System.out.println("Ошибка: Подзадача не может быть null.");
            return null;
        }
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        if (epic != null) {
            epic.addSubtask(subtask.getId());
            updateEpicStatus(epic.getId());
        }

        return subtask;
    }

    // Обновление подзадачи
    public void updateSubtask(Subtask subtask) {
        if (subtask == null) {
            System.out.println("Ошибка: Подзадача не может быть null.");
            return;
        }
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            updateEpicStatus(subtask.getEpicId());
        }
    }

    // Получение подзадач по ID эпика
    public ArrayList<Subtask> getSubtasksByEpicId(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return new ArrayList<>();
        }
        ArrayList<Subtask> result = new ArrayList<>();
        for (int subtaskId : epic.getSubtaskIds()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask != null) {
                result.add(subtask);
            }
        }
        return result;
    }

    // Обновление статуса эпика
    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }

        ArrayList<Subtask> epicSubtasks = getSubtasksByEpicId(epicId);
        if (epicSubtasks.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (Subtask subtask : epicSubtasks) {
            if (subtask == null) {
                continue;
            }
            if (subtask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
            if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                anyInProgress = true;
            }
        }

        if (allDone) {
            epic.setStatus(TaskStatus.DONE);
        } else if (anyInProgress) {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            epic.setStatus(TaskStatus.NEW);
        }
    }
}