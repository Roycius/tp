package seedu.duke.model.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.duke.commons.core.Message;
import seedu.duke.model.task.exceptions.TaskIndexException;
import seedu.duke.ui.Ui;

//@@author rebchua39
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        Collections.sort(taskList);
        this.taskList = taskList;
    }

    public TaskList(List<Task> taskList, boolean isSort) {
        if (isSort) {
            Collections.sort(taskList);
        }
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) throws TaskIndexException {
        try {
            return taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_NUMBER);
        }
    }

    public int getNumberOfPendingTasks() {
        int count = 0;
        for (Task task : taskList) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        Collections.sort(taskList);
    }

    public void deleteTask(int taskIndex) throws TaskIndexException {
        try {
            taskList.remove(taskIndex);
            Collections.sort(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_NUMBER);
        }
    }

    public void markTaskAsDone(int taskIndex) throws TaskIndexException {
        try {
            taskList.get(taskIndex).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new TaskIndexException(Message.ERROR_INVALID_NUMBER);
        }
    }

    //@@author richwill28
    /**
     * Filters the list of tasks based on the specified keyword.
     *
     * @param keyword the specified keyword
     * @return the filtered task list
     */
    public TaskList filterTasksByKeyword(String keyword) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList()));
    }

    /**
     * Filters the list of tasks based on the specified period.
     *
     * @param period the specified period
     * @return the filtered task list
     */
    public TaskList filterTasksByPeriod(String period) {
        return new TaskList(taskList.stream()
                .filter(task -> task.getDayOfTheWeek().toLowerCase().contains(period.toLowerCase()))
                .collect(Collectors.toList()));
    }

    public TaskList sortTasksByPriority() {
        List<Task> sortedTaskList = new ArrayList<>(taskList);
        sortedTaskList.sort((t1, t2) -> {
            Map<String, Integer> order = new HashMap<>();
            order.put("LOW", 1);
            order.put("MEDIUM", 2);
            order.put("HIGH", 3);

            String p1 = t1.getPriority();
            String p2 = t2.getPriority();
            return Integer.compare(order.get(p2), order.get(p1));
        });

        return new TaskList(sortedTaskList, false);
    }

    //@@author Roycius
    /**
     * Serializes the task list into its file data storage format.
     *
     * @return the serialized task list
     */
    public String serialize() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.serialize()).append(System.lineSeparator());
        }
        return data.toString();
    }

    /**
     * Deserializes the storage file and returns the correct task list.
     *
     * @param data a list of strings representing the serialized data
     * @return deserialized task list
     */
    public static List<Task> deserialize(Ui ui, List<String> data) {
        List<Task> taskList = new ArrayList<>();
        for (String line : data) {
            Task task = Task.deserialize(ui, line);
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(task).append(System.lineSeparator());
        }
        return s.toString();
    }
}
