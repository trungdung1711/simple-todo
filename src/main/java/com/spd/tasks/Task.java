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
        catch (DateTimeParseException e)
        {
            throw new InvalidInformationOfTasksException("Date format is \"dd MMM yyyy\"", e);
        }
        this.type = type;
        this.prio = prio;
        this.content = content;
        this.dueString = dueString;
        this.done = done;
    }


    public void changeType(Type newType)
    {
        this.type = newType;
    }


    public void finishTask()
    {
        this.done = Boolean.TRUE;
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