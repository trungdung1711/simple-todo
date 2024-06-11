package com.spd.subcommand;

import com.spd.tasks.TaskList;

import picocli.CommandLine.Command;

@Command
(
    name = "print",
    description = "Print your todo list",
    helpCommand = true
)
public class Print implements Runnable
{
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
        newList.printList();
    }
};