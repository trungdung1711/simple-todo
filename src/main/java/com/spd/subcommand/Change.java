package com.spd.subcommand;


import com.spd.tasks.Priority;
import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksexception.InvalidInformationOfTasksException;
import com.spd.tasks.tasksexception.InvalidTaskIdentifierException;
import com.spd.tasks.tasksmachines.DatabaseManager;
import com.spd.tasks.Type;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command
(
    name = "change",
    description = "Change the information of a task"
)
public class Change implements Runnable
{
    @Option
    (
        names = {"-t","--type"},
        description = "The type to be changed",
        paramLabel = "<type>"
    )
    Type type;


    @Option
    (
        names = {"-p","--priority"},
        description = "The priority to be changed",
        paramLabel = "<priority>"
    )
    Priority prio;


    @Option
    (
        names = {"-c","--content"},
        description = "The content to be changed",
        paramLabel = "content"
    )
    String content;


    @Option
    (
        names = {"-d","--due"},
        description = "The due to be changed",
        paramLabel = "<due>"
    )
    String dueString;


    @Parameters
    (
        paramLabel = "<identifier>",
        description = "The identifier of the task to be changed",
        index = "0"
    )
    Integer id;


    @Override
    public void run()
    {
        try
        {
            Type type = null;
            Priority prio = null;
            String content = null;
            String due = null;

            TaskList newList = DatabaseManager.generateList();

            if (this.type != null)
            {
                type = this.type;
            }
            if (this.prio != null)
            {
                prio = this.prio;
            }
            if (this.content != null)
            {
                content = this.content;
            }
            if (dueString != null)
            {
                due = dueString;
            }
            newList.changeTaskInfor(type, prio, content, due, id);
            DatabaseManager.saveList(newList);
        }
        catch (FailToConnectDataBaseException ftcndbe)
        {
            System.err.println("Changing error: " + ftcndbe.getMessage());
            return;
        }
        catch (InvalidInformationOfTasksException ivlifote)
        {   
            System.err.println("Changing error: " + ivlifote.getMessage());
        }
        catch (InvalidTaskIdentifierException ivltie)
        {
            System.err.println("Changing error: " + ivltie.getMessage());
        }
    }
};