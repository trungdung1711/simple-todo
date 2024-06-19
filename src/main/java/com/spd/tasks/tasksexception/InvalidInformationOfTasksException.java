package com.spd.tasks.tasksexception;

public class InvalidInformationOfTasksException extends RuntimeException
{
    public InvalidInformationOfTasksException(String errMessage,Throwable err)
    {
        super(errMessage, err);
    }
};