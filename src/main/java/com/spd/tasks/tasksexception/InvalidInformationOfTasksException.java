package com.spd.tasks.tasksexception;

public class InvalidInformationOfTasksException extends Exception
{
    public InvalidInformationOfTasksException(String errMessage,Throwable err)
    {
        super(errMessage, err);
    }
};