package com.spd;

import com.spd.subcommand.Add;
import com.spd.subcommand.Change;
import com.spd.subcommand.Delete;
import com.spd.subcommand.Extend;
import com.spd.subcommand.Finish;
import com.spd.subcommand.Print;
import com.spd.subcommand.Sort;
import com.spd.subcommand.Unfinish;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command
(
    name = "todo",
    version = "todo 1.0",
    mixinStandardHelpOptions = true,
    subcommands = {HelpCommand.class,Print.class,Add.class,Delete.class,Finish.class,Change.class,Unfinish.class,Sort.class, Extend.class}
)
public class Todo implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("Todo list - Plan your life (>.<)");
    }


    public static void main(String[] args)
    {
        new CommandLine(new Todo()).execute(args);
    }
};