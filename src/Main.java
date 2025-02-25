import entities.Epic;
import entities.Subtask;
import entities.Task;
import entities.TaskStatus;
import taskManager.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем задачи
        Task task1 = new Task(0, "Задача 1", "Описание задачи 1");
        task1 = manager.createTask(task1);

        Task task2 = new Task(0, "Задача 2", "Описание задачи 2");
        task2 = manager.createTask(task2);

        // Создаем эпик с двумя подзадачами
        Epic epic1 = new Epic(0, "Эпик 1", "Описание эпика 1");
        epic1 = manager.createEpic(epic1);

        Subtask subtask1 = new Subtask(0, "Подзадача 1", "Описание подзадачи 1", epic1.getId());
        subtask1 = manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask(0, "Подзадача 2", "Описание подзадачи 2", epic1.getId());
        subtask2 = manager.createSubtask(subtask2);

        // Создаем эпик с одной подзадачей
        Epic epic2 = new Epic(0, "Эпик 2", "Описание эпика 2");
        epic2 = manager.createEpic(epic2);

        Subtask subtask3 = new Subtask(0, "Подзадача 3", "Описание подзадачи 3", epic2.getId());
        subtask3= manager.createSubtask(subtask3);

        // Распечатываем списки задач, эпиков и подзадач
        System.out.println("Печать всех задач с типом Задача:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println();

        System.out.println("Печать всех эпиков:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println();

        System.out.println("Печать всех подзадач:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        // Изменяем статусы задач и подзадач
        task1.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateTask(task1);

        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask3);

        System.out.println();

        // Распечатываем обновленные списки
        System.out.println("После обновления статусов:");
        System.out.println("Печать всех задач с типом Задача:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println();

        System.out.println("Печать всех эпиков:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println();

        System.out.println("Печать всех подзадач:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        // Удаляем одну задачу и один эпик
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        System.out.println();

        // Распечатываем списки после удаления
        System.out.println("После удаления:");
        System.out.println("Печать всех задач с типом Задача:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println();

        System.out.println("Печать всех эпиков:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println();

        System.out.println("Печать всех подзадач:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
    }
}