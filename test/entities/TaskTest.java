package entities;
import manager.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

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

}