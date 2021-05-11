package com.oracle.report.reader;

import com.opencsv.CSVReader;
import com.oracle.report.util.ReportDataProcessUtil;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class is the implementation of File reader and reads the file and save the data in map cache
 */
public class CSVFileReader implements FileReader {

    @Override
    public void read(String file) {
        try (CSVReader reader = new CSVReader(new java.io.FileReader(getFileFromResource(file)))) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                ReportDataProcessUtil.populateData(lineInArray);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
