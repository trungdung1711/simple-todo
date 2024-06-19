package com.spd.tasks.tasksexception;

public class InvalidTaskIdentifierException extends RuntimeException
{
    public InvalidTaskIdentifierException(String errMessage, Throwable err)
    {
        super(errMessage,err);
    }
};