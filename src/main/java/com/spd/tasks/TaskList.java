package com.spd.tasks;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TaskList
{
    private LinkedList<Task> list;


    public TaskList(LinkedList<Task> list)
    {
        this.list = list;
    }


    public TaskList()
    {
        this.list = new LinkedList<Task>();
    }


    public LinkedList<Task> getList()
    {
        return this.list;
    }


    public void add(Task newTask)
    {
        this.list.add(newTask);
    }


    public void saveList()
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
                System.err.println("Creating error: " + e.getLocalizedMessage());
            }
        }
        try
        {
            mp.writeValue(fdb, this.list);
        }
        catch(Exception e)
        {
            System.err.println("Writing error: " + e.getLocalizedMessage());
        }
    }


    public static TaskList generateList()
    {
        ObjectMapper mp = new ObjectMapper();
        File fdb = new File("./fdb.txt");
        LinkedList<Task> newList = null;
        if (!fdb.exists())
        {
            System.err.println("There is no list");
            return null;
        }
        try
        {
            newList = mp.readValue(fdb, new TypeReference<LinkedList<Task>>(){});
        }
        catch(Exception e)
        {
            System.err.println("Generate error: " + e.getLocalizedMessage());
        }
        return new TaskList(newList);
    }


    public void printList()
    {
        System.out.printf("---------------------------------------------------------------------");
    }
};