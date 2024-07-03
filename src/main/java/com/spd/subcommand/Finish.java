package com.spd.subcommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksexception.InvalidTaskIdentifierException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
(
    name = "finish",
    description = "Mark tasks to be finished"
)
public class Finish implements Runnable
{
    @Parameters
    (
        paramLabel = "<identifier>",
        description = "The identifiers of the tasks to be finished",
        index = "0..*"
    )
    Integer[] ids;


    public void run()
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            List<Integer> idsList = Arrays.asList(ids);
            ArrayList<Integer> idsArrayList = new ArrayList<>(idsList);
            idsArrayList.stream().forEach( (Integer id) -> 
            {
                newList.finish(id);
            });
            DatabaseManager.saveList(newList);
        }
        catch (FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Finishing error: " + ftcdbe.getMessage());
            return;
        }
        catch (InvalidTaskIdentifierException ivtie)
        {
            System.err.println("Finishing error: " + ivtie.getMessage());
            return;
        }
    }
};