package com.spd.subcommand.converter;

import com.spd.tasks.Priority;
import com.spd.tasks.tasksexception.InvalidInformationOfTasksException;
import java.util.HashMap;

import picocli.CommandLine.ITypeConverter;

public class PriorityConverter implements ITypeConverter<Priority>
{
    static HashMap<String,Priority> prioDic;
    static
    {
        prioDic = new HashMap<String,Priority>();
        prioDic.put("o", Priority.OPTIONAL);
        prioDic.put("s", Priority.SHOULD_DO);
        prioDic.put("m", Priority.MUST_DO);

        prioDic.put("optional", Priority.OPTIONAL);
        prioDic.put("shoulddo", Priority.SHOULD_DO);
        prioDic.put("mustdo", Priority.MUST_DO);

        prioDic.put("optional", Priority.OPTIONAL);
        prioDic.put("should_do", Priority.SHOULD_DO);
        prioDic.put("must_do", Priority.MUST_DO);

        prioDic.put("OPTIONAL", Priority.OPTIONAL);
        prioDic.put("SHOULD_DO", Priority.SHOULD_DO);
        prioDic.put("MUST_DO", Priority.MUST_DO);
    }


    /**
     * Converts the string arguments to the Priority Enum
     * param String 
     * @return the correct enum com.spd.tasks.Priority
     * @throws InvalidInformationOfTasksException when the conversion is wrong
     */
    @Override
    public Priority convert(String value) throws InvalidInformationOfTasksException 
    {
        if (prioDic.containsKey(value))
        {
            return prioDic.get(value);
        }
        else if (prioDic.containsKey(value.toLowerCase()))
        {
            return prioDic.get(value.toLowerCase());
        }
        throw new InvalidInformationOfTasksException("Error: Priority mismatched", null);
    }
    
};