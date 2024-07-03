package com.spd.subcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksexception.InvalidTaskIdentifierException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command
(
    name = "extend",
    description = "Extend the due of tasks"
)
public class Extend implements Runnable
{
    @Option
    (
        names = {"-a","--all"},
        description = "Extend the due of all overdued tasks by one day",
        paramLabel = "<all>"
    )
    Boolean all;


    @Option 
    (
        names = {"-id", "--identifier"},
        description = "Extend the due of the tasks by one day",
        arity = "1..*",
        paramLabel = "<identifier>"
    )
    Integer[] ids;
    

    @Override
    public void run() 
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            if (this.all != null && this.all == Boolean.TRUE)
            {
                newList.extendDueAll();
            }
            else if (this.ids != null)
            {
                List<Integer> idsList = Arrays.asList(ids);
                ArrayList<Integer> idsArray = new ArrayList<Integer>(idsList);
                idsArray.stream().forEach( (Integer id) ->
                {
                    newList.extendDue(id);
                }
                );
            }
            DatabaseManager.saveList(newList);
        }
        catch (FailToConnectDataBaseException ftcde)
        {
            System.err.println("Extending error: " + ftcde.getMessage());
            return;
        }
        catch (InvalidTaskIdentifierException ivtie)
        {
            System.err.println("Extending error: " + ivtie.getMessage());
            return;
        }
    }

};