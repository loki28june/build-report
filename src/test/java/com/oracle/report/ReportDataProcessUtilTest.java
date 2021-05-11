package com.oracle.report;

import com.oracle.report.util.ReportDataProcessUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportDataProcessUtilTest {
    private static String[] lineArray1;
    private static String[] lineArray2;
    private static String[] lineArray3;

    @BeforeAll
    public static void setup(){
        lineArray1 = new String[]{"2343225","2345","us_east","RedTeam","ProjectApple","3445s"};
        lineArray2 = new String[]{"1223456","2345","us_west","BlueTeam","ProjectBanana","2211s"};
        lineArray3 = new String[]{"1233456","2345","us_west","BlueTeam","ProjectDate","2221s"};
        ReportDataProcessUtil.populateData(lineArray1);
        ReportDataProcessUtil.populateData(lineArray2);
        ReportDataProcessUtil.populateData(lineArray3);
    }

    @Test
    public void testMapPopulation(){
        ReportDataProcessUtil.populateData(lineArray1);
        Assert.notEmpty(ReportDataProcessUtil.contractIdToCustomerIdSet);
    }

    @Test
    public void testUniqueCustomerIdNumberForContractId(){
        assertEquals(3,ReportDataProcessUtil.contractIdToCustomerIdSet.get("2345").size());
    }

    @Test
    public void testUniqueCustomerIdNumberForGeoZone(){
        assertEquals(2,ReportDataProcessUtil.geoZoneToCustomerIdSet.get("us_west").size());
    }

    @Test
    public void testAverageBuildDurationForGeoZone(){
        assertEquals(2216.0,ReportDataProcessUtil.geoZoneToAvgBuildDuration.get("us_west"));
    }

    @Test
    public void testCustomerIdsListForGeoZone(){
        Set<String> customerIds = new HashSet<>();
        customerIds.add("1223456");
        customerIds.add("1233456");
        assertEquals(customerIds,ReportDataProcessUtil.geoZoneToCustomerIdSet.get("us_west"));
    }
}
