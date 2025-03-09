package manager;

public class Managers {

    // Получение TaskManager
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    // Получение HistoryManager
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}