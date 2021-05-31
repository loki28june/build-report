package com.oracle.report.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReportData {
    private Map<Integer, Set<Integer>> contractIdToCustomerIds = new HashMap<>();
    private Map<String, Set<Integer>> geoZoneToCustomerIds = new HashMap<>();
    private Map<String, Double> geoZoneToAvgBuildDuration = new HashMap<>();

    public Map<Integer, Set<Integer>> getContractIdToCustomerIds() {
        return contractIdToCustomerIds;
    }

    public Map<String, Double> getGeoZoneToAvgBuildDuration() {
        return geoZoneToAvgBuildDuration;
    }

    public Map<String, Set<Integer>> getGeoZoneToCustomerIds() {
        return geoZoneToCustomerIds;
    }
}
