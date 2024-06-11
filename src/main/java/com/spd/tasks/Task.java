package com.spd.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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


    public Task(Type type, Boolean done, Priority prio, String content, String dueString)
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try
        {
            LocalDate.parse(dueString, fmt);
        }
        catch (DateTimeParseException e)
        {
            throw e;
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


    public void changeDueDay(String newDueDay)
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        try
        {
            LocalDate.parse(newDueDay, fmt);
        }
        catch (Exception e)
        {
            System.err.println("Parsing error" + e.getLocalizedMessage());
            return;
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


    public void printTask()
    {
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n","ID","CONTENTS","TYPE","PRIORITY","DUE","DONE");
        System.out.printf("|----------------------------------------------------------------------------------------------------|%n");
        System.out.printf("|%-5s%-45s%-15s%-15s%-15s%-5s|%n","*",getContent(),getType(),getPrio(),getDueString(),(isDone() == Boolean.TRUE)?("X"):("-"));    }
}