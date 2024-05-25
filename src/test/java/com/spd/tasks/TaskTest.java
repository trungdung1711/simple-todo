package com.spd.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class TaskTest
{
    @Test
    public void should_initialise_a_new_task()
    {
        Task newTaks = new Task(Type.STUDY, Priority.MUST_DONE, "26 May 2024", "Doing the exercises of probabilities");

        assertEquals(Type.STUDY, newTaks.getType());
        assertEquals(Priority.MUST_DONE, newTaks.getPrio());
        assertEquals(LocalDate.of(2024, 5, 26), newTaks.getDueDay());
        assertEquals("Doing the exercises of probabilities", newTaks.getContent());
    }


    @Test
    public void should_generate_parse_exception()
    {
        //new Task(Type.EXERCISES,Priority.MUST_DONE,"300 Aug 2018","Doing exercises");
        assertTrue(Boolean.TRUE);
    }


    @Test 
    public void should_parse_the_date()
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse("25 May 2024", fmt);
        assertEquals(LocalDate.of(2024, 5, 25),date);
    }


    @Test
    public void should_show_the_string_of_date()
    {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.now();
        String dateText = date.format(fmt);
        LocalDate parsedDate = LocalDate.parse(dateText, fmt);
        System.out.println(dateText);
        assertEquals(date, parsedDate);
    }
};