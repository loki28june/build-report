package com.oracle.report.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
    public static final String BASE_FILE_PATH = "src/main/resources/";

    public static String fetchFileName() throws IOException{
        FileReader fileReader = new FileReader(BASE_FILE_PATH + "application.properties");
        Properties p = new Properties();
        p.load(fileReader);
        return BASE_FILE_PATH + p.getProperty("input.source.file.name");
    }
}
