package com.spd.subcommand;

import com.spd.tasks.Task;
import com.spd.tasks.TaskList;

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
         * Print the while list for the users
         * 
         * 
         */
        TaskList newList = TaskList.generateList();
        if (newList.getList().isEmpty())
        {
            System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
            System.out.printf("|%-100s|%n","OHAYYO ~~ YOUR TO DO LIST IS CURRENTLY EMPTY, TRY TO PLAN AND ADD SOME FANTASTIC TASKS TO SEE ^.^ ~~");
            System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
            return;
        }
        if (urgent == Boolean.TRUE)
        {
            Task urgentTask = newList.getUrgent();
            urgentTask.printTask();
            return;
        }
        newList.printList();
    }
};