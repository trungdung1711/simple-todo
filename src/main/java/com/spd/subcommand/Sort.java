package com.spd.subcommand;

import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;

@Command
(
    name = "sort",
    description = "Sort the lists of tasks based on the priority"
)
public class Sort implements Runnable
{

    @Override
    public void run() 
    {
        try
        {
            TaskList newList = DatabaseManager.generateList();
            newList.sortByPriority();
            DatabaseManager.saveList(newList);
        }
        catch (FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Sorting error: " + ftcdbe.getMessage());
            return;
        }
    }
    
};