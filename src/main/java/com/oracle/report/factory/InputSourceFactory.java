package com.oracle.report.factory;
import com.oracle.report.constant.InputType;
import com.oracle.report.validation.InputFileValidator;
import com.oracle.report.validation.InputSourceValidator;

public class InputSourceFactory {
    public static InputSourceValidator createInputSource(InputType inputType)  {
        switch (inputType) {
            case FILE:
                return new InputFileValidator();
            case DATABASE:
                // return database source implementation class
            case QUEUE:
                // return queue based implementation class
            default:
                throw new RuntimeException(String.format("Wrong input type => <%s> passed.",inputType.value()));
        }
    }
}
