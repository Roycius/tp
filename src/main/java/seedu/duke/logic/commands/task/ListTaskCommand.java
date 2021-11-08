package seedu.duke.logic.commands.task;

import seedu.duke.logic.commands.Command;
import seedu.duke.DukeException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author richwill28
public class ListTaskCommand extends Command {
    private final String period;

    public ListTaskCommand(String period) {
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
        throws DukeException {
        if (taskList.isEmpty()) {
            ui.printMessage("There are no tasks in the list.");
        } else if (period.isBlank()) {
            ui.printTaskList(taskList);
        } else if (period.equalsIgnoreCase("priority")) {
            ui.printTasksByPriority(taskList);
        } else {
            ui.printTasksWithPeriod(taskList, period);
        }
    }
}
