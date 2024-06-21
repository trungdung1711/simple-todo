package com.spd.subcommand.converter;

import picocli.CommandLine.ITypeConverter;

import com.spd.tasks.Type;
import com.spd.tasks.tasksexception.InvalidInformationOfTasksException;
import java.util.HashMap;

public class TypeConverter implements ITypeConverter<Type>
{   
    static HashMap<String,Type> typeDic;


    static
    {
        typeDic = new HashMap<String,Type>();
        typeDic.put("s", Type.STUDY);
        typeDic.put("w", Type.WORK);
        typeDic.put("e",Type.EXERCISES);
        typeDic.put("g", Type.GROCERIES);
        typeDic.put("study", Type.STUDY);
        typeDic.put("work", Type.WORK);
        typeDic.put("exercises",Type.EXERCISES);
        typeDic.put("groceries", Type.GROCERIES);
        typeDic.put("STUDY", Type.STUDY);
        typeDic.put("WORK", Type.WORK);
        typeDic.put("EXERCISES",Type.EXERCISES);
        typeDic.put("GROCERIES", Type.GROCERIES);
    }


    /**
     * Converts the string arguments to the Type Enum
     * param String enum
     * @return the correct enum com.spd.tasks.Type
     * @throws InvalidInformationOfTasksException when the conversion is wrong
     */
    @Override
    public Type convert(String type) throws InvalidInformationOfTasksException
    {
        if (typeDic.containsKey(type))
        {
            return typeDic.get(type);
        }
        else if (typeDic.containsKey(type.toLowerCase()))
        {
            return typeDic.get(type.toLowerCase());
        }
        throw new InvalidInformationOfTasksException("Error: Type mismatched",null);
    }
    
};