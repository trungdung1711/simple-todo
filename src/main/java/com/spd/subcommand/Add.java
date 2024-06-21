package com.spd.subcommand;


import com.spd.subcommand.converter.TypeConverter;
import com.spd.tasks.Priority;
import com.spd.tasks.Task;
import com.spd.tasks.TaskList;
import com.spd.tasks.Type;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
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
        description = "Type of task",
        index = "0",
        converter = TypeConverter.class
    )
    private Type type;


    @Parameters
    (
        paramLabel = "<priority>",
        description = "Priority of task",
        index = "1"
    )
    private Priority prio;


    @Parameters
    (
        paramLabel = "<content>",
        description = "Content of your task",
        index = "2"
    )
    private String content;


    @Parameters
    (
        paramLabel = "<due>",
        description = "The due of your task",
        index = "3"
    )
    private String dueString;

    public void run()
    {
        /**
         * This method will add a tasks to the todo list
         */
        try
        {
            TaskList newList = DatabaseManager.generateList();

            try
            {
                Task newTask = new Task(this.type, Boolean.FALSE, this.prio, this.content, this.dueString);
                newList.add(newTask);
            }
            catch (InvalidInformationOfTasksException iviote)
            {
                System.err.println("Adding error: " +  iviote.getMessage());
                return;
            }
            DatabaseManager.saveList(newList);
        }
        catch(FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Adding error: " + ftcdbe.getMessage());
            return;
        }
    }
};