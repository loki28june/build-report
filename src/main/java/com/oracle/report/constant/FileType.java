package com.oracle.report.constant;

import com.oracle.report.exception.CanNotResolveInputFileTypeException;

/**
 * Enumeration of index position in the input data line
 */
public enum FileType {
    CSV("csv"),
    JSON("json"),
    XML("xml")
    ;
    private final String value;
    public String value(){
        return this.value;
    }
    FileType(String value) {
        this.value = value;
    }

    public static FileType resolve(String value){
        for (FileType fileType : FileType.values()) {
            if(value.equalsIgnoreCase(fileType.value)){
                return fileType;
            }
        }
        throw new CanNotResolveInputFileTypeException
                (String.format("Input file type <%s> is not supported by system",value));
    }
}
