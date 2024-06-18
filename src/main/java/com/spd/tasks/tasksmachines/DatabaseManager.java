package com.spd.tasks.tasksmachines;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spd.tasks.Task;
import com.spd.tasks.TaskList;
import com.spd.tasks.tasksexception.FailToConnectDataBaseException;

public class DatabaseManager
{
    public static TaskList generateList() throws FailToConnectDataBaseException
    {
        ObjectMapper mp = new ObjectMapper();
        File fdb = new File("./fdb.txt");
        LinkedList<Task> newList = null;
        if (!fdb.exists())
        {
            throw new FailToConnectDataBaseException("fdb.txt doesn't exist");
        }
        try
        {
            newList = mp.readValue(fdb, new TypeReference<LinkedList<Task>>(){});
        }
        catch(Exception e)
        {
            throw new FailToConnectDataBaseException("Fail to read the database",e);
        }
        return new TaskList(newList);
    }


    public static void saveList(TaskList list) throws FailToConnectDataBaseException
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
                throw new FailToConnectDataBaseException("Fail to create new fdb.txt");
            }
        }
        try
        {
            mp.writeValue(fdb, list.getList());
        }
        catch(Exception e)
        {
            throw new FailToConnectDataBaseException("Fail to save data to the database",e);
        }
    }
};