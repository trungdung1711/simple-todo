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


    @Option
    (
        names = {"-id","--identifier"},
        description = "The identifier of the task to be deleted"
    )
    Integer id;


    public void run()
    {
        TaskList newList = TaskList.generateList();
        if (this.all != null && this.all == Boolean.TRUE)
        {
            newList.deleteAll();
        }
        else if (this.id != null)
        {
            newList.delete(id);
        }
        newList.saveList();
    }
};