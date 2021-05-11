package com.oracle.report.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.oracle.report.constant.IndexPosition.*;
import static com.oracle.report.constant.IndexPosition.CONTRACT_ID;

/**
 * This is the utility class to populate the data in local hashmap cache
 * and expose public methods to get the string representation of the report that needs to be
 * written in output source.
 */
public class ReportDataProcessUtil {

    public static Map<String,Set<String>> contractIdToCustomerIdSet = new HashMap<>();
    public static Map<String,Double> geoZoneToAvgBuildDuration = new HashMap<>();
    public static Map<String, Set<String>> geoZoneToCustomerIdSet = new HashMap<>();

    public static void populateData(String[] line){
        populateContractIdToCustomerIdsMap(line[CONTRACT_ID.value()], line[CUSTOMER_ID.value()]);
        populateGeoZoneToAvgBuildDurationMap(line[GEO_ZONE.value()],line[BUILD_DURATION.value()]);
        populateGeoZoneToCustomerIdsMap(line[GEO_ZONE.value()],line[CUSTOMER_ID.value()]);
    }

    private static void populateContractIdToCustomerIdsMap(String contractId, String customerId) {
        Set<String> customerIdSet;
        if(contractIdToCustomerIdSet.containsKey(contractId)){
            customerIdSet = contractIdToCustomerIdSet.get(contractId);
        }
        else{
            customerIdSet = new HashSet<>();
        }
        customerIdSet.add(customerId);
        contractIdToCustomerIdSet.put(contractId , customerIdSet);
    }

    private static void populateGeoZoneToCustomerIdsMap(String geoZone, String customerId) {
        Set<String> customerIds;
        if(geoZoneToCustomerIdSet.containsKey(geoZone)){
            customerIds = geoZoneToCustomerIdSet.get(geoZone);
        }
        else{
            customerIds = new HashSet<>();
        }
        customerIds.add(customerId);
        geoZoneToCustomerIdSet.put(geoZone , customerIds);
    }

    private static void populateGeoZoneToAvgBuildDurationMap(String geoZone, String buildDuration) {
        String buildDurationWithoutUnit = buildDuration.substring(0,buildDuration.length()-1);
        if(geoZoneToAvgBuildDuration.containsKey(geoZone)){
            double value = geoZoneToAvgBuildDuration.get(geoZone);
            value = (value + Double.valueOf(buildDurationWithoutUnit))/2;
            geoZoneToAvgBuildDuration.put(geoZone , value);
        }
        else{
            geoZoneToAvgBuildDuration.put(geoZone,Double.valueOf(buildDurationWithoutUnit));
        }
    }

    public static String fetchUniqueCustomerIdsStringForContractId() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Set<String>> entry: contractIdToCustomerIdSet.entrySet()){
            sb.append(String.format("The number of unique customerId for contractId :%s is %s"
                    ,entry.getKey(),entry.getValue().size()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static String fetchUniqueCustomerIdsStringForGeoZone() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Set<String>> entry: geoZoneToCustomerIdSet.entrySet()){
            sb.append(String.format("The number of unique customerId for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue().size()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static String fetchAverageBuildDurationStringForGeoZone() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Double> entry: geoZoneToAvgBuildDuration.entrySet()){
            sb.append(String.format("The average build duration for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static String fetchUniqueCustomerIdsListString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Set<String>> entry: geoZoneToCustomerIdSet.entrySet()){
            sb.append(String.format("The unique customerId list for geo zone :%s is %s"
                    ,entry.getKey(),entry.getValue().toString()));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
