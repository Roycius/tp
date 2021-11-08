package seedu.duke.logic.commands.lesson;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author richwill28
public class ListLessonCommand extends Command {
    private final String period;

    public ListLessonCommand(String period) {
        this.period = period;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException {
        if (lessonList.isEmpty()) {
            ui.printMessage("There are no lessons in the list.");
        } else if (period.isBlank()) {
            ui.printLessonList(lessonList);
        } else {
            ui.printLessonsWithPeriod(lessonList, period);
        }
    }
}
