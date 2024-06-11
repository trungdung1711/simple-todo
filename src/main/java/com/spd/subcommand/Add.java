package com.spd.subcommand;


import com.spd.tasks.Priority;
import com.spd.tasks.Task;
import com.spd.tasks.TaskList;
import com.spd.tasks.Type;

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
        paramLabel = "type",
        description = "Type of task: STUDY  WORK  EXERCISES  GROCERIES"
    )
    private String type;


    @Parameters
    (
        paramLabel = "priority",
        description = "Priority of task: OPTIONAL  SHOUD_DO  MUST_DO"
    )
    private String prio;


    @Parameters
    (
        paramLabel = "content",
        description = "Content of your task"
    )
    private String content;


    @Parameters
    (
        paramLabel = "due",
        description = "The due of your task"
    )
    private String dueString;

    public void run()
    {
        /**
         * This method will add a tasks to the todo list
         */
        TaskList newList = TaskList.generateList();
        Type type = null;
        Priority prio = null;
        try 
        {
            type = Type.valueOf(this.type);
        }
        catch (Exception e)
        {
            System.err.println("Parsing error: " + e.getLocalizedMessage());
            return;
        }

        try
        {
            prio = Priority.valueOf(this.prio);
        }
        catch (Exception e)
        {
            System.err.println("Parsing error: " + e.getLocalizedMessage());
            return;
        }

        Task newTask = new Task(type, Boolean.FALSE, prio, this.content, this.dueString);
        newList.add(newTask);
        newList.saveList();
    }
};