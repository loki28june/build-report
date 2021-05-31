package com.oracle.report.writer;

import com.oracle.report.model.ReportData;
import com.oracle.report.service.ReportRulesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class responsible to write report data in output file.
 */
public class CsvFileWriter implements OutputWriter {
    private static Logger logger = LoggerFactory.getLogger(CsvFileWriter.class);

    @Override
    public void write(ReportData reportData) {
        ReportRulesService reportRulesService = new ReportRulesService();
        StringBuilder sb = new StringBuilder();
        sb.append(reportRulesService.fetchUniqueCustomerIdsStringForContractId(reportData));
        sb.append(System.getProperty("line.separator"));
        sb.append(reportRulesService.fetchUniqueCustomerIdsStringForGeoZone(reportData));
        sb.append(System.getProperty("line.separator"));
        sb.append(reportRulesService.fetchAverageBuildDurationStringForGeoZone(reportData));
        sb.append(System.getProperty("line.separator"));
        sb.append(reportRulesService.fetchUniqueCustomerIdsListString(reportData));
        sb.append(System.getProperty("line.separator"));

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(getOutputFilePath())) {
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            logger.error("Exception occurred while writing report output to file", e);
        }
    }

    private String getOutputFilePath() {
        try (FileReader reader = new FileReader("src/main/resources/application.properties")) {
            Properties p = new Properties();
            p.load(reader);
            return p.getProperty("report.output.file.path");
        } catch (FileNotFoundException e) {
            logger.error("Property file not found ", e);
        } catch (IOException e) {
            logger.error("IO Exception while reading property file ", e);
        }
        return StringUtils.EMPTY;
    }

}
