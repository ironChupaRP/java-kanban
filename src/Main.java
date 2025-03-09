import entities.Epic;
import entities.Subtask;
import entities.Task;
import entities.TaskStatus;
import manager.Managers;
import manager.TaskManager;
import manager.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        // Создаем менеджер задач
        TaskManager manager = Managers.getDefault();

        // Создаем задачи
        Task task1 = manager.createTask(new Task(0, "Задача 1", "Описание задачи 1"));
        Task task2 = manager.createTask(new Task(0, "Задача 2", "Описание задачи 2"));

        // Создаем эпик с двумя подзадачами
        Epic epic1 = manager.createEpic(new Epic(0, "Эпик 1", "Описание эпика 1"));
        Subtask subtask1 = manager.createSubtask(new Subtask(0, "Подзадача 1", "Описание подзадачи 1", epic1.getId()));
        Subtask subtask2 = manager.createSubtask(new Subtask(0, "Подзадача 2", "Описание подзадачи 2", epic1.getId()));

        // Создаем эпик с одной подзадачей
        Epic epic2 = manager.createEpic(new Epic(0, "Эпик 2", "Описание эпика 2"));
        Subtask subtask3 = manager.createSubtask(new Subtask(0, "Подзадача 3", "Описание подзадачи 3", epic2.getId()));

        // Выводим все задачи
        System.out.println("Все задачи после создания:");
        printAllTasks(manager);

        // Изменяем статусы задач и подзадач
        task1.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateTask(task1);

        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask2);

        subtask3.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask3);

        // Выводим все задачи после обновления статусов
        System.out.println("\nВсе задачи после обновления статусов:");
        printAllTasks(manager);

        // Просматриваем задачи, чтобы добавить их в историю
        manager.getTaskById(task1.getId());
        manager.getEpicById(epic1.getId());
        manager.getSubtaskById(subtask1.getId());

        // Выводим историю просмотров
        System.out.println("\nИстория просмотров после вызова get-методов:");
        printAllTasks(manager);

        // Удаляем одну задачу и один эпик
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        // Выводим все задачи после удаления
        System.out.println("\nВсе задачи после удаления:");
        printAllTasks(manager);
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : manager.getAllEpics()) {
            System.out.println(epic);

            for (Subtask subtask : manager.getSubtasksByEpicId(epic.getId())) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory() ) {
            System.out.println(task);
        }
    }
}