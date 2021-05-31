package com.oracle.report.service;

import com.oracle.report.model.ReportData;

import java.util.Map;
import java.util.Set;

public class ReportRulesService {

    public String fetchUniqueCustomerIdsStringForContractId(ReportData reportData) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Set<Integer>> entry: reportData.getContractIdToCustomerIds().entrySet()){
            sb.append(String.format("The number of unique customerId for contractId :%s is %s"
                    ,entry.getKey(),entry.getValue().size()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public String fetchUniqueCustomerIdsStringForGeoZone(ReportData reportData) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Set<Integer>> entry: reportData.getGeoZoneToCustomerIds().entrySet()){
            sb.append(String.format("The number of unique customerId for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue().size()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public String fetchAverageBuildDurationStringForGeoZone(ReportData reportData) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Double> entry: reportData.getGeoZoneToAvgBuildDuration().entrySet()){
            sb.append(String.format("The average build duration for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public String fetchUniqueCustomerIdsListString(ReportData reportData) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Set<Integer>> entry: reportData.getGeoZoneToCustomerIds().entrySet()){
            sb.append(String.format("The unique customerId list for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue().toString()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
