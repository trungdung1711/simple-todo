package com.spd.subcommand;

import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksexception.InvalidTaskIdentifierException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
(
    name = "finish",
    description = "Mark a task to be finished"
)
public class Finish implements Runnable
{
    @Parameters
    (
        paramLabel = "<identifier>",
        description = "The identifier of the task to be finished"
    )
    Integer id;


    public void run()
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            try
            {
                newList.finish(id);
            }
            catch (InvalidTaskIdentifierException ivtie)
            {
                System.err.println("Finishing error: " + ivtie.getMessage());
                return;
            }
            DatabaseManager.saveList(newList);
        }
        catch (FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Finishing error: " + ftcdbe.getMessage());
            return;
        }
    }
};