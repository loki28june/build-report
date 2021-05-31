package com.oracle.report.process;

import com.oracle.report.model.InputData;
import com.oracle.report.model.ReportData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class processes the input data objects and produces the report data object to be used by writer service to write report.
 */
public class ProcessingService {

    public ReportData buildReportData(List<InputData> inputDataList) {
        ReportData reportData = new ReportData();
        for (InputData input : inputDataList) {
            populateContractIdToCustomerIdsMap(reportData, input);
            populateGeoZoneToCustomerIdsMap(reportData, input);
            populateGeoZoneToAvgBuildDurationMap(reportData, input);
        }
        return reportData;
    }

    private void populateContractIdToCustomerIdsMap(ReportData reportData, InputData inputData) {
        Set<Integer> customerIds = new HashSet<>();
        if (reportData.getContractIdToCustomerIds().containsKey(inputData.getContractId())) {
            customerIds = reportData.getContractIdToCustomerIds().get(inputData.getContractId());
        }
        customerIds.add((Integer) inputData.getCustomerId());
        reportData.getContractIdToCustomerIds().put((Integer) inputData.getContractId(), customerIds);
    }

    private void populateGeoZoneToCustomerIdsMap(ReportData reportData, InputData inputData) {
        Set<Integer> customerIds = new HashSet<>();
        if (reportData.getGeoZoneToCustomerIds().containsKey((String) inputData.getGeoZone())) {
            customerIds = reportData.getGeoZoneToCustomerIds().get(inputData.getGeoZone());
        }
        customerIds.add((Integer) inputData.getCustomerId());
        reportData.getGeoZoneToCustomerIds().put((String) inputData.getGeoZone(), customerIds);
    }

    private void populateGeoZoneToAvgBuildDurationMap(ReportData reportData, InputData inputData) {
        double currentDuration = (double) inputData.getBuildDuration();
        if (reportData.getGeoZoneToAvgBuildDuration().containsKey(inputData.getGeoZone())) {
            double existingDuration = reportData.getGeoZoneToAvgBuildDuration().get(inputData.getGeoZone());
            currentDuration = (existingDuration + currentDuration) / 2;
        }
        reportData.getGeoZoneToAvgBuildDuration().put((String) inputData.getGeoZone(), currentDuration);
    }
}
