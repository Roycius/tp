# User Guide

NUS Buddy is a **fast, flexible and unobtrusive weekly planner**.
Designed specifically for NUS students, it includes an intuitive **task and lesson manager**, as well as a **module planner** 
with CAP calculation features that can be accessed quickly via a Command Line Interface (CLI), taking up the least of 
your time, so you can focus on tasks and lessons that are important to you.

This user guide will help you get started on using NUS Buddy, so you can go about organising your tasks, 
lessons and modules right away. The [quick start](#quick-start) will show you how to set up NUS Buddy 
on your computer. The [features](#features) section will show you the list of features and how to use 
them. If you encounter any issues while using NUS Buddy, the [FAQ](#faq) section may help with troubleshooting 
your problems. Finally, the [command summary](#command-summary) will give you an overview of all the 
commands that you can execute.

> Symbols used in this guide:
> 
> * 💡 denotes important information
> * ❗ denotes a warning

## Table of contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Getting help - `help`](#getting-help---help)
  * [Adding a Task, Lesson or Module - `add`](#adding-a-task-lesson-or-module---add)
  * [Listing Tasks, Lessons or Modules - `list`](#listing-tasks-lessons-or-modules---list)
  * [Marking a Task as done - `done`](#marking-a-task-as-done---done)
  * [Deleting Tasks, Lessons or Modules - `delete`](#deleting-tasks-lessons-or-modules---delete)
  * [Finding Tasks or lessons by keyword - `find task/lesson`](#finding-tasks-or-lessons-by-keyword---find-tasklesson)
  * [Retrieving Module information - `find module`](#retrieving-module-information---find-module)
  * [Editing module grade - `edit module`](#editing-module-grade---edit-module)
  * [Launching Lesson URL - `launch lesson`](#launching-lesson-url---launch-lesson)
  * [Exiting the program - `exit`](#exiting-the-program---exit)
  * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed on your machine. You can follow the guide 
[here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html) for more information.
2. Download the latest version of NUS Buddy from our GitHub repository.
3. Copy the `.jar` file to the folder you want to use as the home directory for the application.
4. To launch the app, run the command `java -jar NUSBuddy.jar`.

You should see the following output when you start NUSBuddy for the first time.

```
    _______________________________________________________________________________
     I can't retrieve the saved task data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
     I can't retrieve the saved lesson data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
     I can't retrieve the saved module data. Creating new file..
    _______________________________________________________________________________

    _______________________________________________________________________________
       _   _       _   _   ____           ____     _   _   ____     ____   __   __ 
      | \ |"|   U |"|u| | / __"| u     U | __")uU |"|u| | |  _"\   |  _"\  \ \ / / 
     <|  \| |>   \| |\| |<\___ \/       \|  _ \/ \| |\| |/| | | | /| | | |  \ V /  
     U| |\  |u    | |_| | u___) |        | |_) |  | |_| |U| |_| |\U| |_| |\U_|"|_u 
      |_| \_|    <<\___/  |____/>>       |____/  <<\___/  |____/ u |____/ u  |_|   
      ||   \\,-.(__) )(    )(  (__)     _|| \\_ (__) )(    |||_     |||_ .-,//|(_  
      (_")  (_/     (__)  (__)         (__) (__)    (__)  (__)_)   (__)_) \_) (__) 
    _______________________________________________________________________________
```

> ❗ The new files created are used to store the data of your tasks/lessons/modules. 
> Please refrain from deleting these files in order to preserve the data.

## Features

> 💡 The **UPPER_CASE** in the command denotes the parameter that you can enter your value into.
> 
> `[COMPULSORY_PARAM]`: Square brackets denote a compulsory parameter.
> 
> `[OPTION1/OPTION2]`: Denotes either `OPTION1` or `OPTION2` must be chosen as the parameter.
> 
> `{OPTIONAL_PARAM}`: Braces denote an optional parameter.

### Getting help - `help`

Shows you a summary of the commands you can use with NUSBuddy, and the format of their parameters if applicable.

Format: `help`

Example usage:

![HelpCommandExample](images/HelpCommandExample.png)

You can use this as a quick guide while using NUS Buddy. The format of the commands shown by `help` 
is the same as the one used by this guide, which can be found [here](#features).

### Adding a Task, Lesson or Module - `add`

Adds a task, lesson or module of your choice to your list.

#### Task

A task contains a title, day of the week, a priority, and an information.

Format: `add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`

* `TITLE` refers to the title of the task.
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, 
  `sat`, `sun`.
* `PRIORITY` can be either `L`, `M` or `H`, for low, medium, and high priority respectively.
* `INFORMATION` refers to any additional information about the task.

> 💡 The task priority is low by default.

> ❗ Any `|` symbol used in the title will be replaced by a `/` symbol. 

Example:

```
$ add task Do CS2113T tP -d sat -p H
      ______________________________________________________________________________________
       Noted. I've added this task:
         [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 tasks in the list.
      ______________________________________________________________________________________
$ add task Update CS2113T docs -d thu -i User Guide
      ______________________________________________________________________________________
       Noted. I've added this task:
         [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       Now you have 2 tasks in the list.
      ______________________________________________________________________________________
```

> 💡 The empty braces `[ ]` indicates that the task is incomplete. You can
> [visit this section](#marking-a-task-as-done---done) for more information
> on how to mark a task as done.

#### Lesson

A lesson contains a title, day of the week, the start time and the end time of the lesson.

Format: `add lesson [TITLE] -d [DAY_OF_THE_WEEK] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}`

* `TITLE` refers to the title of the lesson.
* `DAY_OF_THE_WEEK` can be any of the following, not case-sensitive: `mon`, `tue`, `wed`, `thu`, `fri`, 
  `sat`, `sun`.
* `START_TIME` refers to the start time of the lesson, uses 24-hour clock format `HH:MM`.
* `END_TIME` refers to the end time of the lesson, uses 24-hour clock format `HH:MM`.
* `MEETING_URL` refers to the meeting url/link (if available).

Example:

```
$ add lesson CS2113T Lecture -d fri -s 16:00 -e 18:00 -l https://www.google.com/
      ______________________________________________________________________________________
       Noted. I've added this lesson:
         CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: https://www.google.com/
       Now you have 1 lessons in the list.
      ______________________________________________________________________________________

$ add lesson CG2028 Tutorial -d thu -s 13:00 -e 14:00
      ______________________________________________________________________________________
       Noted. I've added this lesson:
         CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
       Now you have 2 lessons in the list.
      ______________________________________________________________________________________
```

> 💡 The input times are automatically converted to 12-hour clock format.

#### Module

A module is referred to by its module code, and the data is retrieved from [NUSMods](https://nusmods.com/).

Format: `add module [MODULE_CODE] -g {GRADE}`

* `MODULE_CODE` refers to the module (based on NUSMods), not case-sensitive.
* `GRADE` refers to your grade for the module (if available)

Example:

```
$ add module CS2113T
      ______________________________________________________________________________________
       Noted. I've added this module:
         CS2113T Software Engineering & Object-Oriented Programming (4MCs) | Grade: NONE
       Now you have 1 modules in the list.
      ______________________________________________________________________________________

$ add module CG2028 -g A
      ______________________________________________________________________________________
       Noted. I've added this module:
         CG2028 Computer Organization (2MCs) | Grade: A
       Now you have 2 modules in the list.
      ______________________________________________________________________________________
```

### Listing Tasks, Lessons or Modules - `list`

Lists out tasks, lessons or modules on your list with an option to sort or filter the list.

#### Task

Format: `list task {PERIOD/PRIORITY}`

* `PERIOD` can be either day of the week, and `today` or `tomorrow`.

Example:

```
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________
```

> 💡 By default, the list of tasks is sorted by the day the of the week.

```
$ list task today
      ______________________________________________________________________________________
       Here are the tasks on Thursday:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________

$ list task priority
      ______________________________________________________________________________________
       Here are the tasks sorted by priority:
       1. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       2. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Format: `list lesson {PERIOD/PRIORITY}`

* `PERIOD` can be either day of the week, and `today` or `tomorrow`.

```
$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: https://meetinglink.com/
       2. CG2028 Lab
          Wednesday, 02:00 PM - 05:00 PM
          Meeting URL: -
      ______________________________________________________________________________________

$ list lesson tomorrow
      ______________________________________________________________________________________
       Here are the lessons on Friday:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
``` 

#### Module

Format: `list module`

```
$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) | Grade: A
       2. CG2028 Computer Organization (2MCs) | Grade: B
       ------------------------------------------------------------------------------------
       You have a total of 6 MCs
       Your current CAP is: 4.50
      ______________________________________________________________________________________
```

### Marking a Task as done - `done`

Marks a task of your specification as done.

Format: `done task [INDEX]`

* `INDEX` refers to the index of the task. You can view it by executing `list task`.

Example:

```
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [ ] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________

$ done task 2
      ______________________________________________________________________________________
       Nice! I've marked this task as done: 
         [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 pending tasks.
      ______________________________________________________________________________________

$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________
```

> 💡 `[X]` indicates that the task is completed.

### Deleting Tasks, Lessons or Modules - `delete`

Removes a task, lesson or module of your specification from your list.

> ❗ Once removed, the deleted task/lesson/module cannot be restored.

#### Task

Format: `delete task [INDEX]`

Example:

```
$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
       2. [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
      ______________________________________________________________________________________

$ delete task 2
      ______________________________________________________________________________________
       Ok. The following task has been deleted:
         [X] Do CS2113T tP (Saturday)
              Priority: HIGH
              Info: -
       Now you have 1 task(s) in the list.
      ______________________________________________________________________________________

$ list task
      ______________________________________________________________________________________
       Here are the tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Format: `delete lesson [INDEX]`

Example:

```
$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
       2. CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
      ______________________________________________________________________________________

$ delete lesson 2
      ______________________________________________________________________________________
       Ok. The following lesson has been deleted:
         CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
       Now you have 1 lesson(s) in the list.
      ______________________________________________________________________________________

$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
```

#### Module

Format: `delete module [MODULE_CODE]`

Example:

```
$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) Grade: A
       2. CG2028 Computer Organization (2MCs) Grade: B
       ------------------------------------------------------------------------------------
       You have a total of 6 MCs
       Your current CAP is: 4.50
      ______________________________________________________________________________________

$ delete module CG2028
      ______________________________________________________________________________________
       Ok. The following module has been deleted:
         CG2028 Computer Organization (2MCs) 
       Now you have 1 module(s) in the list.
      ______________________________________________________________________________________

$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) Grade: A
       2. CG2028 Computer Organization (2MCs) Grade: B
       ------------------------------------------------------------------------------------
       You have a total of 6 MCs
       Your current CAP is: 4.50
      ______________________________________________________________________________________
```

### Finding Tasks or Lessons by keyword - `find task/lesson`

#### Task

Find tasks matching the keyword you specify.

Format: `find task [KEYWORD]`

* `KEYWORD` refers to the intended keyword, not case-sensitive

Example:

```
$ find task CS2113T
      ______________________________________________________________________________________
       Here are the matching tasks in your list:
       1. [ ] Update CS2113T docs (Thursday)
              Priority: LOW
              Info: User Guide
      ______________________________________________________________________________________
```

#### Lesson

Find lessons matching the keyword you specify.

Format: `find lesson [KEYWORD]`

* `KEYWORD` refers to the intended keyword, not case-sensitive

Example:

```
$ find lesson CS2113T
      ______________________________________________________________________________________
       Here are the matching lessons in your list:
       1. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: -
      ______________________________________________________________________________________
```

### Retrieving Module information - `find module`

Shows you a complete list of information for any modules that are listed on NUSMods. You can find information
such as module title, modular credits, department, faculty, preclusion, pre-requisites, etc.

Format: `find module [MODULE_CODE] {verbose/-v}`

* Adding `verbose` or `-v` at the end will provide more detailed information.

Example:

![FindModuleExample](images/FindModuleExample.png)

### Editing module grade - `edit module`

Allows you to set a grade value for a module in your list.

Format: `edit module [MODULE_CODE] -g [GRADE]`

Example:

```
$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) | Grade: B
       2. CS1231 Discrete Structures (4MCs) | Grade: NONE
       ------------------------------------------------------------------------------------
       You have a total of 8 MCs
       Your current CAP is: 3.50
      ______________________________________________________________________________________

$ edit module cs1231 -g A+
      ______________________________________________________________________________________
       You have changed your grade for this module: 
          CS1231 Discrete Structures (4MCs) | Grade: A+
      ______________________________________________________________________________________

$ list module
      ______________________________________________________________________________________
       Here are the modules in your list:
       1. CS2113T Software Engineering & Object-Oriented Programming (4MCs) | Grade: B
       2. CS1231 Discrete Structures (4MCs) | Grade: A+
       ------------------------------------------------------------------------------------
       You have a total of 8 MCs
       Your current CAP is: 4.25
      ______________________________________________________________________________________
```

### Launching Lesson URL - `launch lesson`

Launches the meeting URL of the lesson of the given index.

Format: `launch lesson [INDEX]`

Example:

```
$ list lesson
      ______________________________________________________________________________________
       Here are the lessons in your list:
       1. CG2028 Tutorial
          Thursday, 01:00 PM - 02:00 PM
          Meeting URL: -
       2. CS2113T Lecture
          Friday, 04:00 PM - 06:00 PM
          Meeting URL: https://www.google.com/
      ______________________________________________________________________________________

$ launch lesson 2
      ______________________________________________________________________________________
       Launching URL...
      ______________________________________________________________________________________
```

In this example, the meeting URL of lesson number 2 (which is https://www.google.com/) is launched.

### Exiting the program - `exit`

Exits the programme.

Format: `exit`

Example:

```
$ exit
      _______________________________________________________________________________
       Bye!
      _______________________________________________________________________________
```

### Saving the data

Data from NUS Buddy is automatically saved on your local machine.

Tasks are stored in `tasks.txt`, lessons are stored in `lessons.txt`, and modules are stored in `modules.txt`.

All save files use one line for each task, lesson or module.

The format of the save files is as follows:

Tasks:

```text
[Completion status] | [Task title] | [Day of the week] | [Priority] | [Information]
```

Lessons:

```text
[Lesson title] | [Day of the week] | [Start time] | [End time] | [Lesson URL]
```

Modules:

```text
[Module code] | [Module title] | [Number of MCs] | [Grade]
```

> ❗ You can edit the save files using a text editor, if you wish to do so. However, do note that if the save files are edited in a way that causes the data to be invalid, NUS Buddy will discard the invalid data, which may cause data loss. 

## FAQ

**Q:** How do I transfer my data to another Computer?<br>
**A:** Launch the app once on the new computer and exit. Afterwards, replace the data folder in the Duke home folder of your new computer with that of your previous computer.<br>

## Command summary

| Command                                                                                                                  | Purpose                                          |
| :----------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------- |
| [`add task [TITLE] -d [DAY_OF_THE_WEEK] -p {PRIORITY} -i {INFORMATION}`](#adding-a-task-lesson-or-module---add)          | To add a task                                    |
| [`add lesson [TITLE] -d [DAY_OF] -s [START_TIME] -e [END_TIME] -l {MEETING_URL}`](#adding-a-task-lesson-or-module---add) | To add a lesson                                  |
| [`add module [MODULE_CODE] -g {GRADE}`](#adding-a-task-lesson-or-module---add)                                           | To add a module                                  |
| [`list task {PERIOD/PRIORITY}`](#listing-tasks-lessons-or-modules---list)                                                | To list all tasks                                |
| [`list lesson {PERIOD}`](#listing-tasks-lessons-or-modules---list)                                                       | To list all lessons                              |
| [`list module`](#listing-tasks-lessons-or-modules---list)                                                                | To list all modules                              |
| [`delete task [INDEX]`](#deleting-tasks-lessons-or-modules---delete)                                                     | To delete a task                                 |
| [`delete lesson [INDEX]`](#deleting-tasks-lessons-or-modules---delete)                                                   | To delete a lesson                               |
| [`delete module [MODULE_CODE]`](#deleting-tasks-lessons-or-modules---delete)                                             | To delete a module                               |
| [`done task [INDEX]`](#marking-a-task-as-done---done)                                                                    | To mark a task as done                           |
| [`find [task/lesson] [KEYWORD]`](#finding-tasks-or-lessons-by-keyword---find-tasklesson)                                 | To find tasks/lessons with the specified keyword |
| [`find module [MODULE_CODE] {verbose/-v}`](#retrieving-module-information---find-module)                                 | To display module details                        |
| [`edit module [MODULE_CODE] -g [GRADE]`](#editing-module-grade---edit-module)                                            | To edit the grade for a particular module        |
| [`launch lesson [INDEX]`](#launching-lesson-url---launch-lesson)                                                         | To launch a meeting URL                          |
| [`exit`](#exiting-the-program---exit)                                                                                    | To exit the program                              |
