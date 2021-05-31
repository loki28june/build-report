package com.oracle.report.validation;

import com.oracle.report.exception.FileNotValidException;
import com.oracle.report.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * This class validates the file and it's content.
 */
public class InputFileValidator implements InputSourceValidator{
    private  Logger logger = LoggerFactory.getLogger(InputFileValidator.class);

    @Override
    public void validate() {
        File file;
        try {
            String fileName = FileUtil.fetchFileName();
            file = new File(fileName);
            if(!file.isFile() || file.length() == 0){
                logger.error("File is not valid or empty");
                throw new FileNotValidException("File is invalid or empty");
            }
        } catch (IOException e) {
            logger.error("Exception occurred while file validation",e);
        }
    }
}
