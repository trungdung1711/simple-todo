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


    public static void printList(TaskList list)
    {
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n","ID","CONTENTS","TYPE","PRIORITY","DUE","DONE");
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        Integer index = 1;
        for (Task task : list.getList())
        {
            System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n",index++,task.getContent(),task.getType(),task.getPrio(),task.getDueString(),(task.isDone() == Boolean.TRUE)?("X"):("-"));
        }
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
    }


    public static void printTask(Task task)
    {
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n","ID","CONTENTS","TYPE","PRIORITY","DUE","DONE");
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n","*",task.getContent(),task.getType(),task.getPrio(),task.getDueString(),(task.isDone() == Boolean.TRUE)?("X"):("-"));    
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
    }


    public void run()
    {
        /**
         * Print the list for the user
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
            printTask(urgentTask);
            return;
        }
        printList(newList);
    }
};