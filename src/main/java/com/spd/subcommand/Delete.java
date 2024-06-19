package com.spd.subcommand;

import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksexception.InvalidTaskIdentifierException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command
(
    name = "delete",
    description = "Delete a task in your todo list"
)
public class Delete implements Runnable
{
    @Option
    (
        names = {"-a","--all"},
        description = "Delete your whole todo list"
    )
    Boolean all;


    @Option
    (
        names = {"-id","--identifier"},
        description = "The identifier of the task to be deleted"
    )
    Integer id;


    public void run()
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            if (this.all != null && this.all == Boolean.TRUE)
            {
                newList.deleteAll();
            }
            else if (this.id != null)
            {
                try
                {
                    newList.delete(id);
                }
                catch(InvalidTaskIdentifierException itie)
                {
                    System.err.println("Deleting error: " + itie.getMessage());
                    return;
                }
            
            }
            DatabaseManager.saveList(newList);
        }
        catch(FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Deleting error: " + ftcdbe.getMessage());
            return;
        }
    }
};