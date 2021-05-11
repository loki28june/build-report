package com.oracle.report.writer;

import com.oracle.report.util.ReportDataProcessUtil;

import java.io.IOException;

/**
 * This class responsible to write report data in output file.
 */
public class CsvFileWriter implements FileWriter {

    @Override
    public void write() {
        StringBuilder sb = new StringBuilder();
        sb.append(ReportDataProcessUtil.fetchUniqueCustomerIdsStringForContractId());
        sb.append(System.getProperty("line.separator"));
        sb.append(ReportDataProcessUtil.fetchUniqueCustomerIdsStringForGeoZone());
        sb.append(System.getProperty("line.separator"));
        sb.append(ReportDataProcessUtil.fetchAverageBuildDurationStringForGeoZone());
        sb.append(System.getProperty("line.separator"));
        sb.append(ReportDataProcessUtil.fetchUniqueCustomerIdsListString());
        sb.append(System.getProperty("line.separator"));

        try (java.io.FileWriter fileWriter = new java.io.FileWriter("src/main/resources/output.txt")) {
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
