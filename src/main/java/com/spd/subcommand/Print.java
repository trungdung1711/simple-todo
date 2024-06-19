package com.spd.subcommand;

import com.spd.tasks.Task;
import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;
import com.spd.tasks.tasksmachines.DatabaseManager;
import com.spd.tasks.tasksmachines.Printer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command
(
    name = "print",
    description = "Print your todo list",
    helpCommand = true
)
public class Print implements Runnable
{
    @Option
    (
        names = {"-u","--urgent"},
        description = "Print the most urgent task"
    )
    Boolean urgent;


    public void run()
    {
        /**
         * Print the list for the user
         * 
         * 
         */
        try
        {
            TaskList newList = DatabaseManager.generateList();
            if (urgent != null && urgent == Boolean.TRUE)
            {
                Task urgentTask = newList.getUrgent();
                Printer.printTask(urgentTask);
                return;
            }
            Printer.printList(newList);
        }
        catch (FailToConnectDataBaseException ftcdbe)
        {
            System.err.println("Printing error: " + ftcdbe.getMessage());
            return;
        }
    }
};