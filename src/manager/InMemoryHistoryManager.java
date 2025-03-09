package manager;

import entities.Task;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> history = new LinkedList<>(); // История просмотров

    // Изменил этот метод на добавлении копии задачи иначе не срабатывал последний тест. Не уверен, что это правильное решение
    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        history.addFirst(task.copy());
        if (history.size() > 10) {
            history.removeLast();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}