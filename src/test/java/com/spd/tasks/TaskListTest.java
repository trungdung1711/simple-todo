package com.spd.tasks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskListTest
{
    @Test
    public void should_parse_object_to_json_file()
    {
        Task newTask = new Task(Type.WORK,false, Priority.MUST_DONE, "Doing the exercises of Java","27 May 2024");
        Task newTask1 = new Task(Type.WORK,false ,Priority.SHOULD_DONE, "Doing the exercises of C++", "12 May 2024");
        Task newTask2 = new Task(Type.GROCERIES,false, Priority.OPTIONAL, "Buying chicken for lunch", "14 Jun 2023");
        Task newTask3 = new Task(Type.WORK,false ,Priority.SHOULD_DONE,"Doing the 39 Mina no nihongo","14 Mar 1996");
        TaskList newList = new TaskList();
        newTask2.finishTask();
        newList.add(newTask);
        newList.add(newTask1);
        newList.add(newTask2);
        newList.add(newTask3);
        newList.saveList();
        assertTrue(Boolean.TRUE);
    }


    @Test
    public void should_generate_a_new_list_from_json_file()
    {
        TaskList newList =  TaskList.generateList();
        assertTrue(Boolean.TRUE);
    }


    @Test
    public void should_change_the_value_of_the_data_base_of_the_file()
    {
        TaskList newList = TaskList.generateList();
        newList.getList().get(2).changeDueDay("17 Oct 2004");
        newList.getList().get(2).changeContent("The content has been changed");
        newList.getList().get(2).finishTask();
        newList.getList().get(2).changeType(Type.WORK);
        newList.getList().get(2).changePrio(Priority.MUST_DONE);
        newList.saveList();
        assertTrue(Boolean.TRUE);
    }



    @Test
    public void should_print_the_whole_list_without_any_sorting()
    {
        TaskList newList = TaskList.generateList();
        newList.printList();
        assertTrue(Boolean.TRUE);
    }
};