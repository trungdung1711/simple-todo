// package com.spd.tasks;

// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertTrue;

// import com.spd.tasks.tasksmachines.DatabaseManager;
// import com.spd.tasks.tasksmachines.Printer;

// public class TaskListTest
// {
//     //@Test
//     public void should_parse_object_to_json_file()
//     {
//         Task newTask = new Task(Type.WORK,false, Priority.MUST_DO, "Doing the exercises of Java","12 May 2024");
//         Task newTask1 = new Task(Type.WORK,false ,Priority.SHOULD_DO, "Doing the exercises of C++", "12 May 2024");
//         Task newTask2 = new Task(Type.GROCERIES,false, Priority.OPTIONAL, "Buying chicken for lunch", "12 May 2023");
//         Task newTask3 = new Task(Type.WORK,false ,Priority.SHOULD_DO,"Doing the 39 Mina no nihongo","17 Oct 1996");
//         Task newTask4 = new Task(Type.EXERCISES,false,Priority.OPTIONAL,"Doing the chemistry test","17 Oct 2004");
//         TaskList newList = new TaskList();
//         newTask2.finishTask();
//         newTask4.finishTask();
//         newList.add(newTask);
//         newList.add(newTask1);
//         newList.add(newTask2);
//         newList.add(newTask3);
//         newList.add(newTask4);
//         newList.add(new Task(Type.GROCERIES,false,Priority.SHOULD_DO,"Buying banana for lunch","12 May 2024"));
//         //DatabaseManager.saveList(newList);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_generate_a_new_list_from_json_file()
//     {
//         //TaskList newList =  DatabaseManager.generateList();
//         //newList.delete(null);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_change_the_value_of_the_data_base_of_the_file()
//     {
//         //TaskList newList =  DatabaseManager.generateList();
//         //newList.getList().get(2).changeDueDay("17 Oct 2004");
//         newList.getList().get(2).changeContent("The content has been changed");
//         newList.getList().get(2).finishTask();
//         newList.getList().get(2).changeType(Type.WORK);
//         newList.getList().get(2).changePrio(Priority.MUST_DO);
//         DatabaseManager.saveList(newList);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_print_the_whole_list_without_any_sorting()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         Printer.printList(newList);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_delete_a_task_and_write_to_the_data_base()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         newList.delete(1);
//         Printer.printList(newList);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_sort_the_list_based_on_the_priority_and_the_due_day()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         newList.sortByPriority();
//         Printer.printList(newList);
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_finish_all_the_list()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         newList.getList().forEach((Task task) -> 
//         {
//             task.finishTask();
//         });
//         Printer.printList(newList);
//     }



//     //@Test
//     public void should_print_the_most_urgent_task()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         Printer.printTask(newList.getUrgent());
//         assertTrue(Boolean.TRUE);
//     }


//     //@Test
//     public void should_show_the_new_list_generated_from_empty_database_null()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         assertNull(newList.getList());
//     }


//     //@Test
//     public void should_print_empty_list()
//     {
//         TaskList newList =  DatabaseManager.generateList();
//         newList.add(null);
//         assertTrue(Boolean.TRUE);
//     }
// };