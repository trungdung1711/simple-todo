package com.spd.tasks;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            System.err.println("Deleting error: Fail to delete this task");
            return;
        }
        this.list.remove(id-1);
    }


    public void deleteAll()
    {
        this.list.clear();
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
                System.err.println("Saving error: " + e.getLocalizedMessage());
            }
        }
        try
        {
            mp.writeValue(fdb, this.list);
        }
        catch(Exception e)
        {
            System.err.println("Saving error: " + e.getLocalizedMessage());
        }
    }


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


    public Task getUrgent()
    {
        /**
         * Return the most urgent task
         * 
         * @return the most urgent task
         */
        if (this.list.isEmpty()) return null;
        Task urgent = this.list.getFirst();
        for (Task task : this.list)
        {
            if (task.getPrio().compareTo(urgent.getPrio()) > 0)
                urgent = task;
            else if (task.getPrio().compareTo(urgent.getPrio()) == 0)
            {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
                LocalDate t1_date = LocalDate.parse(task.getDueString(), fmt);
                LocalDate t2_date = LocalDate.parse(urgent.getDueString(), fmt);
                if (t1_date.isEqual(t2_date))
                {
                    urgent = task;
                }
                else if (t1_date.isBefore(t2_date))
                {
                    urgent = task;
                }
            }
        }
        return urgent;
    }
};