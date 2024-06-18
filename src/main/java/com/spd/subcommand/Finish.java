package com.spd.subcommand;

import com.spd.tasks.TaskList;
import com.spd.tasks.tasksmachines.DatabaseManager;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
(
    name = "finish",
    description = "Mark a task to be finished"
)
public class Finish implements Runnable
{
    @Parameters
    (
        paramLabel = "<identifier>",
        description = "The identifier of the task to be finished"
    )
    Integer id;


    public void run()
    {
        TaskList newList = DatabaseManager.generateList();
        newList.finish(id);
        DatabaseManager.saveList(newList);
    }
};