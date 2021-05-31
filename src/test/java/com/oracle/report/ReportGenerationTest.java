package com.oracle.report;

import com.oracle.report.model.InputData;
import com.oracle.report.model.ReportData;
import com.oracle.report.process.ProcessingService;
import com.oracle.report.reader.CSVFileReader;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class ReportGenerationTest {
    List<InputData> inputDataList = new ArrayList<>();
    ReportData reportData = new ReportData();

    @Before
    public void setup(){
        InputData<Integer, String, Double> inputData = populateInputData(1223456, "ProjectBanana", 2211);
        InputData<Integer, String, Double> inputData2 = populateInputData(1233456, "ProjectDate", 2221);
        inputDataList.add(inputData);
        inputDataList.add(inputData2);
        ProcessingService processingService = new ProcessingService();
        reportData = processingService.buildReportData(inputDataList);
    }

    @NotNull
    private InputData<Integer, String, Double> populateInputData(int customerId, String projectBanana, int buildDuration) {
        InputData<Integer, String, Double> inputData = new InputData();
        inputData.setCustomerId(customerId);
        inputData.setContractId(2345);
        inputData.setGeoZone("us_west");
        inputData.setTeamCode("BlueTeam");
        inputData.setProjectCode(projectBanana);
        inputData.setBuildDuration(Double.valueOf(buildDuration));
        return inputData;
    }

    @Test
    public void testReader(){
        CSVFileReader csvFileReader = new CSVFileReader();
        List<InputData> inputData = csvFileReader.read();
        assertEquals(5,inputData.size());
    }

    @Test
    public void testProcessor(){
        assertThat(reportData.getContractIdToCustomerIds().size(), is(1));
        assertThat(reportData.getGeoZoneToCustomerIds().size(), is(1));
        assertThat(reportData.getGeoZoneToAvgBuildDuration().size(), is(1));
    }

    @Test
    public void testUniqueCustomerIdNumberForContractId(){
        assertEquals(2, reportData.getContractIdToCustomerIds().get(2345).size());
    }

    @Test
    public void testUniqueCustomerIdNumberForGeoZone(){
        assertEquals(2, reportData.getGeoZoneToCustomerIds().get("us_west").size());
    }

    @Test
    public void testAverageBuildDurationForGeoZone(){
        assertEquals(Double.valueOf(2216), reportData.getGeoZoneToAvgBuildDuration().get("us_west").doubleValue(),Double.valueOf(0.1));
    }
}
