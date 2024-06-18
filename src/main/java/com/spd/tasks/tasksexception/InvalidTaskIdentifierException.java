package com.spd.tasks.tasksexception;

public class InvalidTaskIdentifierException extends Exception
{
    public InvalidTaskIdentifierException(String errMessage, Throwable err)
    {
        super(errMessage,err);
    }
};