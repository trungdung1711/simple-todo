package com.spd.tasks.tasksexception;

public class FailToConnectDataBaseException extends Exception
{
    public FailToConnectDataBaseException(String errMessage,Throwable err)
    {
        super(errMessage, err);
    }


    public FailToConnectDataBaseException(String errMessage)
    {
        super(errMessage);
    }
};