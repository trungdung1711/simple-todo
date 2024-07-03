package com.spd.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.spd.tasks.tasksexception.InvalidInformationOfTasksException;


public class Task
{
    private Type        type = Type.STUDY;
    private Boolean     done = Boolean.FALSE;
    private Priority    prio = Priority.SHOULD_DO;
    private String      content;
    private String      dueString;

    private Boolean     overDue;

    public Task()
    {
        content = null;
        dueString = "00 000 0000";
    }


    public Task(Type type, Boolean done, Priority prio, String content, String dueString) throws InvalidInformationOfTasksException
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try
        {
            LocalDate.parse(dueString, fmt);
        }
        catch (DateTimeParseException dtpe)
        {
            throw new InvalidInformationOfTasksException("Date format is \"dd MMM yyyy\"", dtpe);
        }
        this.type = type;
        this.prio = prio;
        this.content = content;
        this.dueString = dueString;
        this.done = done;
        this.overDue = Boolean.FALSE;
    }

    
    /**
     * Extend the due of this task by today
     */
    public void extendDue()
    {
        if (isOverDue())
        {
            DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd MMM yyyy");
            changeDueDay(LocalDate.now().format(fm));
        }
    }


    public void changeType(Type newType)
    {
        this.type = newType;
    }


    public void finishTask()
    {
        this.done = Boolean.TRUE;
    }


    public void unfinishTask()
    {
        this.done = Boolean.FALSE;
    }


    public void changePrio(Priority newPrio)
    {
        this.prio = newPrio;
    }


    public void changeDueDay(String newDueDay) throws InvalidInformationOfTasksException
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try
        {
            LocalDate.parse(newDueDay, fmt);
        }
        catch (DateTimeParseException e)
        {
            throw new InvalidInformationOfTasksException("Date format is \"dd MMM yyyy\"", e);
        }
        this.dueString = newDueDay;
    }


    public void changeContent(String newContent)
    {
        this.content = newContent;
    }


    public Type getType()
    {
        return this.type;
    }


    public Boolean isDone()
    {
        return this.done;
    }


    public Boolean isOverDue() throws InvalidInformationOfTasksException
    {
        if (isDone())
        {
            return Boolean.FALSE;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try
        {
            LocalDate due = LocalDate.parse(dueString,fmt);
            LocalDate today = LocalDate.now();
            return due.isBefore(today);
        }
        catch (DateTimeParseException dtpe)
        {
            throw new InvalidInformationOfTasksException("Date format is \"dd MMM yyyy\"", dtpe); 
        }
    }


    public Priority getPrio()
    {
        return this.prio;
    }


    public String getContent()
    {
        return this.content;
    }


    public String getDueString()
    {
        return this.dueString;
    }
}