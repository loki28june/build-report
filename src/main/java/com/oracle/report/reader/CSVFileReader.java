package com.oracle.report.reader;

import com.opencsv.CSVReader;
import com.oracle.report.exception.CanNotReadFileException;
import com.oracle.report.model.InputData;
import com.oracle.report.util.FileUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.oracle.report.constant.DataIndex.*;

/**
 * This class is the implementation of File reader and reads the file and save the data in map cache
 */
public class CSVFileReader implements InputReader {

    @Override
    public List<InputData> read() {
        List<InputData> inputDataList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new java.io.FileReader(FileUtil.fetchFileName()))) {
            String[] lineArray;
            while ((lineArray = reader.readNext()) != null) {
                inputDataList.add(getInputDataFromLineArray(lineArray));
            }
        } catch (IOException | IllegalArgumentException e) {
            throw new CanNotReadFileException("Exception occurred while reading the file");
        }
        return inputDataList;
    }

    private InputData getInputDataFromLineArray(String[] line) {
        InputData<Integer, String, Double> inputData = new InputData();
        inputData.setCustomerId(Integer.valueOf(line[CUSTOMER_ID.value()]));
        inputData.setContractId(Integer.valueOf(line[CONTRACT_ID.value()]));
        inputData.setGeoZone(line[GEO_ZONE.value()]);
        inputData.setTeamCode(line[TEAM_CODE.value()]);
        inputData.setProjectCode(line[PROJECT_CODE.value()]);
        String buildDurationWithoutUnit = line[BUILD_DURATION.value()].substring(0, line[BUILD_DURATION.value()].length() - 1);
        inputData.setBuildDuration(Double.valueOf(buildDurationWithoutUnit));
        return inputData;
    }
}
