package com.spd.tasks.tasksmachines;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spd.tasks.Task;
import com.spd.tasks.TaskList;

public class DatabaseManager
{
    public static TaskList generateList()
    {
        ObjectMapper mp = new ObjectMapper();
        File fdb = new File("./fdb.txt");
        LinkedList<Task> newList = null;
        if (!fdb.exists())
        {
            System.err.println("Generating error");
            return null;
        }
        try
        {
            newList = mp.readValue(fdb, new TypeReference<LinkedList<Task>>(){});
        }
        catch(Exception e)
        {
            System.err.println("Generating error: " + e.getLocalizedMessage());
            return null;
        }
        return new TaskList(newList);
    }


    public static void saveList(TaskList list)
    {
        ObjectMapper mp = new ObjectMapper();
        File fdb = new File("./fdb.txt");
        if (!fdb.exists())
        {
            try 
            {
                fdb.createNewFile();
            }
            catch(IOException e)
            {
                System.err.println("Saving error: " + e.getLocalizedMessage());
                return;
            }
        }
        try
        {
            mp.writeValue(fdb, list.getList());
        }
        catch(Exception e)
        {
            System.err.println("Saving error: " + e.getLocalizedMessage());
            return;
        }
    }
};