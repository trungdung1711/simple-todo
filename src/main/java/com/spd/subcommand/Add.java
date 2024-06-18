package com.spd.subcommand;


import com.spd.tasks.Priority;
import com.spd.tasks.Task;
import com.spd.tasks.TaskList;
import com.spd.tasks.Type;
import com.spd.tasks.tasksexception.InvalidInformationOfTasksException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
(
    name = "add",
    description = "Add a new task to your todo list"
)
public class Add implements Runnable 
{
    @Parameters
    (
        paramLabel = "<type>",
        description = "Type of task: STUDY  WORK  EXERCISES  GROCERIES"
    )
    private String type;


    @Parameters
    (
        paramLabel = "<priority>",
        description = "Priority of task: OPTIONAL  SHOUD_DO  MUST_DO"
    )
    private String prio;


    @Parameters
    (
        paramLabel = "<content>",
        description = "Content of your task"
    )
    private String content;


    @Parameters
    (
        paramLabel = "<due>",
        description = "The due of your task"
    )
    private String dueString;

    public void run()
    {
        /**
         * This method will add a tasks to the todo list
         */
        TaskList newList = DatabaseManager.generateList();
        Type type = null;
        Priority prio = null;
        try 
        {
            type = Type.valueOf(this.type);
            prio = Priority.valueOf(this.prio);
        }
        catch (Exception e)
        {
            System.err.println("Adding error: invalid Type or Priority");
            return;
        }

        try
        {
            Task newTask = new Task(type, Boolean.FALSE, prio, this.content, this.dueString);
            newList.add(newTask);
        }
        catch (InvalidInformationOfTasksException iviote)
        {
            System.err.println("Adding error: " +  iviote.getMessage());
            return;
        }
        DatabaseManager.saveList(newList);
    }
};