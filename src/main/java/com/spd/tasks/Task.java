package com.spd.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task
{
    private Type        m_type = Type.STUDY;
    private Boolean     m_done = Boolean.FALSE;
    private Priority    m_prio = Priority.SHOULD_DONE;
    private LocalDate   m_due;
    private String      m_content;


    public Task(Type type,Priority prio, String dueDate, String content)
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM yyyy");
        try
        {
            LocalDate date = LocalDate.parse(dueDate, fmt);
            m_due = date;
        }
        catch (DateTimeParseException e)
        {
            System.err.println("Parsing error: " + e.getLocalizedMessage());
        }
        m_type = type;
        m_prio = prio;
        m_content = content;
    }


    public void changeType(Type newType)
    {
        m_type = newType;
    }


    public void finishTask()
    {
        m_done = Boolean.TRUE;
    }


    public void changePrio(Priority newPrio)
    {
        m_prio = newPrio;
    }


    public void changeDueDay(String newDueDay)
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM yyyy");
        try
        {
            LocalDate date = LocalDate.parse(newDueDay, fmt);
            m_due = date;
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
        }
    }


    public void changeContent(String newContent)
    {
        m_content = newContent;
    }


    public Type getType()
    {
        return m_type;
    }


    public Boolean isDone()
    {
        return m_done;
    }


    public Priority getPrio()
    {
        return m_prio;
    }


    public LocalDate getDueDay()
    {
        return m_due;
    }


    public String getContent()
    {
        return m_content;
    }
}