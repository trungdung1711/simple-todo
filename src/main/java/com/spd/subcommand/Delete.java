package com.spd.subcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        description = "Delete your whole todo list",
        paramLabel = "<all>"
    )
    Boolean all;


    @Option
    (
        names = {"-id","--identifier"},
        description = "The identifiers of the tasks to be deleted",
        arity = "1..*",
        paramLabel = "<identifier>"
    )
    Integer[] ids;


    @Option
    (
        names = {"-f","--finished"},
        description = "Delete the finished tasks",
        paramLabel = "<finish>"

    )
    Boolean finished;


    public void run()
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            if (this.all != null && this.all == Boolean.TRUE)
            {
                newList.deleteAll();
            }
            if (this.finished != null && this.finished == Boolean.TRUE)
            {
                newList.deleteFinished();
            }
            else if (this.ids != null)
            {
                Set<Integer> idsSet = new HashSet<> (Arrays.asList(ids));
                ArrayList<Integer> idsList = new ArrayList<Integer> (idsSet);
                Collections.sort(idsList);
                Collections.reverse(idsList);
                idsList.stream().forEach( (Integer id) -> 
                {
                    newList.delete(id);
                });
            }
            DatabaseManager.saveList(newList);
        }
        catch(FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Deleting error: " + ftcdbe.getMessage());
            return;
        }
        catch(InvalidTaskIdentifierException itie)
        {
            System.err.println("Deleting error: " + itie.getMessage());
            return;
        }
    }
};