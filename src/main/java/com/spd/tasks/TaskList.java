package com.spd.tasks;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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


    public void delete(Integer id)
    {
        if (id < 1 || id > this.list.size())
        {
            System.err.println("Delete error: Fail to delete this task");
            return;
        }
        this.list.remove(id-1);
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
        System.out.printf("|--------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s %-40s %-10s %-15s %-12s %-4s |%n","ID","CONTENTS","TYPE","PRIORITY","DUE","DONE");
        System.out.printf("|--------------------------------------------------------------------------------------------|%n");
        Integer index = 1;
        for (Task task : this.list)
        {
            String doneMark = null;
            if (task.isDone())
                doneMark = "X";
            else
                doneMark = "-";
            System.out.printf("|%-5s %-40s %-10s %-15s %-12s %-4s |%n",index++,task.getContent(),task.getType(),task.getPrio(),task.getDueString(),doneMark);
        }
        System.out.printf("|--------------------------------------------------------------------------------------------|%n");
    }


    public void sortByPriority()
    {
        /*
         * MUST_DONE    0
         * SHOULD_DONE  1
         * OPTINAL      2
         */
        Collections.sort(this.list,new Comparator<Task>() 
        {
            @Override
            public int compare(Task t1,Task t2)    
            {
                if (t1.getPrio().compareTo(t2.getPrio()) == 0)
                {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    LocalDate t1_date = LocalDate.parse(t1.getDueString(), fmt);
                    LocalDate t2_date = LocalDate.parse(t2.getDueString(), fmt);
                    if (t1_date.isEqual(t2_date))
                    {
                        return -1;
                    }
                    else if (t1_date.isBefore(t2_date))
                    {
                        return -1;
                    }
                    return 1;
                }
                else if (t1.getPrio().compareTo(t2.getPrio()) < 0)
                {
                    return 1;
                }
                return -1;
            }
        });
    }
};