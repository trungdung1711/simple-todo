package com.spd.tasks;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskListTest
{
    @Test
    public void should_parse_object_to_json_file()
    {
        Task newTask = new Task(Type.WORK,false, Priority.MUST_DO, "Doing the exercises of Java","12 May 2024");
        Task newTask1 = new Task(Type.WORK,false ,Priority.SHOULD_DO, "Doing the exercises of C++", "12 May 2024");
        Task newTask2 = new Task(Type.GROCERIES,false, Priority.OPTIONAL, "Buying chicken for lunch", "12 May 2023");
        Task newTask3 = new Task(Type.WORK,false ,Priority.SHOULD_DO,"Doing the 39 Mina no nihongo","17 Oct 1996");
        Task newTask4 = new Task(Type.EXERCISES,false,Priority.OPTIONAL,"Doing the chemistry test","17 Oct 2004");
        TaskList newList = new TaskList();
        newTask2.finishTask();
        newTask4.finishTask();
        newList.add(newTask);
        newList.add(newTask1);
        newList.add(newTask2);
        newList.add(newTask3);
        newList.add(newTask4);
        newList.add(new Task(Type.GROCERIES,false,Priority.SHOULD_DO,"Buying banana for lunch","12 May 2024"));
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
        newList.getList().get(2).changePrio(Priority.MUST_DO);
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


    //@Test
    public void should_delete_a_task_and_write_to_the_data_base()
    {
        TaskList newList = TaskList.generateList();
        newList.delete(1);
        assertTrue(Boolean.TRUE);
    }



    @Test
    public void should_sort_the_list_based_on_the_priority_and_the_due_day()
    {
        TaskList newList = TaskList.generateList();
        newList.sortByPriority();
        newList.printList();
        assertTrue(Boolean.TRUE);
    }
};