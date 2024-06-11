package com.spd.subcommand;

import com.spd.tasks.TaskList;

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


    public void run()
    {
        TaskList newList = TaskList.generateList();
        if (this.all)
        {
            newList.deleteAll();
        }
        newList.saveList();

    }
};