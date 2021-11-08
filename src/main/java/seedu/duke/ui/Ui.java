package seedu.duke.ui;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;

import seedu.duke.Duke;
import seedu.duke.DukeException;
import seedu.duke.commons.core.CommandFormat;

import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.model.lesson.Lesson;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.Module;
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.module.exceptions.ModuleNotFoundException;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;

import static seedu.duke.commons.util.DayUtil.getToday;
import static seedu.duke.commons.util.DayUtil.getTomorrow;
import static seedu.duke.commons.util.DayUtil.isToday;
import static seedu.duke.commons.util.DayUtil.isTomorrow;
import static seedu.duke.commons.util.StringUtil.splitToLength;

//@@author richwill28
public class Ui {
    public static final String LINE =
            "    ______________________________________________________________________________________"
                    + System.lineSeparator();

    public static final String PADDING = "     ";

    public static final String SHORT_LINE = PADDING
            + "------------------------------------------------------------------------------------"
            + System.lineSeparator();

    public static final int MAX_DESCRIPTION_LENGTH = 84;

    /**
     * Temporary logo.
     */
    public static final String LOGO = PADDING
            + "  _   _       _   _   ____           ____     _   _   ____     ____   __   __ "
            + System.lineSeparator() + PADDING
            + " | \\ |\"|   U |\"|u| | / __\"| u     U | __\")uU |\"|u| | |  _\"\\   |  _\"\\  \\ \\ / / "
            + System.lineSeparator() + PADDING
            + "<|  \\| |>   \\| |\\| |<\\___ \\/       \\|  _ \\/ \\| |\\| |/| | | | /| | | |  \\ V /  "
            + System.lineSeparator() + PADDING
            + "U| |\\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\\U| |_| |\\U_|\"|_u "
            + System.lineSeparator() + PADDING
            + " |_| \\_|    <<\\___/  |____/>>       |____/  <<\\___/  |____/ u |____/ u  |_|   "
            + System.lineSeparator() + PADDING
            + " ||   \\\\,-.(__) )(    )(  (__)     _|| \\\\_ (__) )(    |||_     |||_ .-,//|(_  "
            + System.lineSeparator() + PADDING
            + " (_\")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \\_) (__) "
            + System.lineSeparator();

    private final Scanner sc = new Scanner(System.in);

    /**
     * Reads user response from the standard input.
     *
     * @return the user response, with leading and trailing whitespaces removed
     */
    public String readUserResponse() {
        return sc.nextLine().strip();
    }

    /**
     * Greets user by displaying the logo.
     */
    public void printGreeting() {
        System.out.print(LINE);
        System.out.print(LOGO);
        System.out.println(LINE);
    }

    /**
     * Displays goodbye message to user on exit.
     */
    public void printExit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye!");
        System.out.print(LINE);
    }

    /**
     * Displays the specified custom message.
     *
     * @param message the specified message
     */
    public void printMessage(String message) {
        System.out.print(LINE);
        System.out.println(PADDING + message);
        System.out.println(LINE);
    }

    /**
     * Displays a list of possible input commands and link to user guide.
     */
    public void printHelpMessage() {
        System.out.print(LINE);
        System.out.println(PADDING
                + "Here are the list of commands that you can try.");
        System.out.print(SHORT_LINE);

        System.out.println(PADDING + CommandFormat.ADD_TASK_FORMAT);
        System.out.println(PADDING + CommandFormat.ADD_LESSON_FORMAT);
        System.out.println(PADDING + CommandFormat.ADD_MODULE_FORMAT);

        System.out.println(PADDING + CommandFormat.LIST_TASK_FORMAT);
        System.out.println(PADDING + CommandFormat.LIST_LESSON_FORMAT);
        System.out.println(PADDING + CommandFormat.LIST_MODULE_FORMAT);

        System.out.println(PADDING + CommandFormat.DELETE_TASK_FORMAT);
        System.out.println(PADDING + CommandFormat.DELETE_LESSON_FORMAT);
        System.out.println(PADDING + CommandFormat.DELETE_MODULE_FORMAT);

        System.out.println(PADDING + CommandFormat.DONE_TASK_FORMAT);

        System.out.println(PADDING + CommandFormat.FIND_TASK_FORMAT);
        System.out.println(PADDING + CommandFormat.FIND_LESSON_FORMAT);
        System.out.println(PADDING + CommandFormat.FIND_MODULE_FORMAT);

        System.out.println(PADDING + CommandFormat.EDIT_MODULE_FORMAT);

        System.out.println(PADDING + CommandFormat.LAUNCH_LESSON_FORMAT);

        System.out.println(PADDING + CommandFormat.EXIT_FORMAT);

        System.out.println();
        System.out.println(PADDING + "Notes: Square brackets -> [COMPULSORY_PARAMETER]");
        System.out.println(PADDING + "       Curly braces    -> {OPTIONAL_PARAMETER}");
        System.out.println(PADDING
                + "       More details: https://ay2122s1-cs2113t-w11-3.github.io/tp/UserGuide.html");
        System.out.println(LINE);
    }

    // Task-related methods

    /**
     * Displays a message to inform user that the specified task
     * has been successfully added.
     *
     * @param task the specified task
     * @param size the number of tasks in the list
     */
    public void printTaskAdded(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this task:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified task
     * has been successfully deleted.
     *
     * @param task the Task type object that has been deleted
     * @param size the number of remaining tasks in the list
     */
    public void printTaskDeleted(Task task, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following task has been deleted:");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + size + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified task
     * has been successfully marked as done.
     *
     * @param taskList the list of tasks
     * @param task the task that had been marked as done
     */
    public void printTaskMarkedAsDone(TaskList taskList, Task task) {
        System.out.print(LINE);
        System.out.println(PADDING + "Nice! I've marked this task as done: ");
        System.out.println(PADDING + "  " + task);
        System.out.println(PADDING + "Now you have " + taskList.getNumberOfPendingTasks() + " pending tasks.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the list of tasks
     */
    public void printTaskList(TaskList taskList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(LINE);
    }

    public void printTasksByPriority(TaskList taskList) {
        TaskList sortedTaskList = taskList.sortTasksByPriority();
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the tasks sorted by priority:");
        System.out.print(sortedTaskList);
        System.out.println(LINE);
    }

    // TODO: Combine the two methods below

    /**
     * Displays the list of tasks filtered based on the specified keyword.
     *
     * @param taskList the unfiltered list of tasks
     * @param keyword the specified keyword
     */
    public void printTasksWithKeyword(TaskList taskList, String keyword) {
        TaskList filteredTaskList = taskList.filterTasksByKeyword(keyword);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no matching task in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching tasks in your list:");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the list of tasks filtered based on the specified period.
     *
     * @param taskList the initial task list
     * @param period the specified period
     */
    public void printTasksWithPeriod(TaskList taskList, String period) throws DukeException {
        if (isToday(period)) {
            period = getToday();
        } else if (isTomorrow(period)) {
            period = getTomorrow();
        }

        period = DayOfTheWeek.toProper(period);
        TaskList filteredTaskList = taskList.filterTasksByPeriod(period);

        System.out.print(LINE);
        if (filteredTaskList.isEmpty()) {
            System.out.println(PADDING + "There is no task on " + period + ".");
        } else {
            System.out.println(PADDING + "Here are the tasks on " + period + ":");
            System.out.print(filteredTaskList);
        }
        System.out.println(LINE);
    }

    // Lesson-related methods

    /**
     * Displays a message to inform user that the specified lesson
     * has been successfully added.
     *
     * @param lesson the specified lesson
     * @param size the number of lessons in the list
     */
    public void printLessonAdded(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this lesson:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lessons in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays a message to inform user that the specified lesson
     * has been successfully deleted.
     *
     * @param lesson the Lesson type object that has been deleted
     * @param size the number of remaining lessons in the list
     */
    public void printLessonDeleted(Lesson lesson, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following lesson has been deleted:");
        System.out.println(PADDING + "  " + lesson);
        System.out.println(PADDING + "Now you have " + size + " lesson(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of lessons.
     *
     * @param lessonList the list of lessons
     */
    public void printLessonList(LessonList lessonList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the lessons in your list:");
        System.out.print(lessonList);
        System.out.println(LINE);
    }

    // TODO: Combine the two methods below

    /**
     * Displays the list of lessons filtered based on the specified keyword.
     *
     * @param lessonList the unfiltered list of lessons
     * @param keyword the specified keyword
     */
    public void printLessonsWithKeyword(LessonList lessonList, String keyword) {
        LessonList filteredLessonList = lessonList.filterLessonsByKeyword(keyword);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no matching lesson in your list.");
        } else {
            System.out.println(PADDING + "Here are the matching lessons in your list:");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    /**
     * Displays the list of lessons filtered based on the specified period.
     *
     * @param lessonList the unfiltered list of lessons
     * @param period the specified period
     */
    public void printLessonsWithPeriod(LessonList lessonList, String period) throws DukeException {
        if (isToday(period)) {
            period = getToday();
        } else if (isTomorrow(period)) {
            period = getTomorrow();
        }

        period = DayOfTheWeek.toProper(period);
        LessonList filteredLessonList = lessonList.filterLessonsByPeriod(period);

        System.out.print(LINE);
        if (filteredLessonList.isEmpty()) {
            System.out.println(PADDING + "There is no lesson on " + period + ".");
        } else {
            System.out.println(PADDING + "Here are the lessons on " + period + ":");
            System.out.print(filteredLessonList);
        }
        System.out.println(LINE);
    }

    //@@author ptejasv
    // Module-related methods

    /**
     * Displays a message to inform user that the specified module
     * has been successfully added.
     *
     * @param module the specified module added
     * @param size the number of modules in the list
     */
    public void printModuleAdded(Module module, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Noted. I've added this module:");
        System.out.println(PADDING + "  " + module);
        System.out.println(PADDING + "Now you have " + size + " modules in the list.");
        System.out.println(LINE);
    }

    //@@author Roycius

    /**
     * Displays a message to inform user that the specified module
     * has been successfully deleted.
     *
     * @param module the Module type object that has been deleted
     * @param size the number of remaining modules in the list
     */
    public void printModuleDeleted(Module module, int size) {
        System.out.print(LINE);
        System.out.println(PADDING + "Ok. The following module has been deleted:");
        System.out.println(PADDING + "  " + module);
        System.out.println(PADDING + "Now you have " + size + " module(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays the list of modules with only the basic module information.
     *
     * @param moduleList the list of modules
     */
    public void printModuleList(ModuleList moduleList) {
        System.out.print(LINE);
        System.out.println(PADDING + "Here are the modules in your list:");
        System.out.print(moduleList);
        System.out.print(SHORT_LINE);
        printTotalMC(moduleList);
        printCap(moduleList);
        System.out.println(LINE);
    }

    private void printTotalMC(ModuleList moduleList) {
        System.out.println(PADDING + "You have a total of " + moduleList.getTotalMC() + " MCs");
    }

    //@@author rebchua39

    /**
     * Displays the user's cumulative average point (CAP) or a message if there is no valid CAP.
     *
     * @param moduleList the list of modules
     */
    private void printCap(ModuleList moduleList) {
        double cap = moduleList.calculateCap();
        boolean isCapValid = (cap >= 0);
        if (isCapValid) {
            DecimalFormat df = new DecimalFormat("0.00");
            cap = moduleList.calculateCap();
            System.out.println(PADDING + "Your current CAP is: " + df.format(cap));
        } else {
            System.out.println(PADDING + "If you have received grades for your modules, set them to see your CAP!");
        }
    }

    /**
     * Displays a message to inform the user that the grade for the specified module has been successfully changed.
     *
     * @param module the module object whose grade has been changed
     */
    public void printModuleGradeChanged(Module module) {
        System.out.print(LINE);
        System.out.println(PADDING + "You have changed your grade for this module: ");
        System.out.println(PADDING + "   " + module);
        System.out.println(LINE);
    }

    //@@author richwill28

    /**
     * Displays the full module information of the specified module code.
     *
     * @param moduleCode the module code
     * @throws ModuleNotFoundException when there is no module in FullModuleList with a matching module code
     */
    public void printModuleInfo(String moduleCode, boolean isVerbose) throws ModuleNotFoundException {
        Module module = Duke.fullModuleList.findModule(moduleCode);
        String[] moduleInfo = module.getInfo(isVerbose);
        if (isVerbose) {
            printModuleWithDescription(moduleInfo);
        } else {
            printModuleWithoutDescription(moduleInfo);
        }
    }

    public void printModuleWithDescription(String[] moduleInfo) {
        System.out.print(LINE);
        for (int i = 0; i < moduleInfo.length; i++) {
            if (i == 1 || i == 4 || i == 5 || i == 6) {
                // param 1, 4, 5, and 6 have long description
                String[] params = splitToLength(moduleInfo[i], MAX_DESCRIPTION_LENGTH);
                for (String param : params) {
                    System.out.println(PADDING + param.strip());
                }
            } else {
                System.out.println(PADDING + moduleInfo[i]);
            }
        }
        System.out.println(LINE);
    }

    public void printModuleWithoutDescription(String[] moduleInfo) {
        System.out.print(LINE);
        for (int i = 0; i < moduleInfo.length; i++) {
            if (i == 3 || i == 4 || i == 5) {
                // param 3, 4, and 5 have long descriptions
                String[] params = splitToLength(moduleInfo[i], MAX_DESCRIPTION_LENGTH);
                for (String param : params) {
                    System.out.println(PADDING + param.strip());
                }
            } else {
                System.out.println(PADDING + moduleInfo[i]);
            }
        }
        System.out.println(LINE);
    }
}
