package com.oracle.report.constant;

import com.oracle.report.exception.CanNotResolveInputFileTypeException;

/**
 * Enumeration of inout data type for the application
 */
public enum InputType {
    FILE("file"),
    DATABASE("database"),
    QUEUE("queue")
    ;
    private final String value;
    public String value(){
        return this.value;
    }
    InputType(String value) {
        this.value = value;
    }

    public static InputType resolve(String value){
        for (InputType inputType : InputType.values()) {
            if(value.equalsIgnoreCase(inputType.value)){
                return inputType;
            }
        }
        throw new CanNotResolveInputFileTypeException
                (String.format("Input type <%s> is not supported by system",value));
    }
}
