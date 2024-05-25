package com.spd.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaskTest
{
    @Test
    public void should_initialise_a_new_task()
    {
        Task newTaks = new Task(Type.STUDY, Priority.MUST_DONE, "26/05/2024", "Doing the exercises of probabilities");

        assertEquals(Type.STUDY, newTaks.getType());
        assertEquals(Priority.MUST_DONE, newTaks.getPrio());
        assertEquals("26-5-2024", newTaks.getDueDay().toString());
        assertEquals("Doing the exercises of probabilities", newTaks.getClass());
    }
};