package com.oracle.report;

import com.oracle.report.process.CSVFileProcessor;
import com.oracle.report.process.InputFileProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is responsible for starting and executing the application code.
 */
@SpringBootApplication
public class ReportApplication {

	public static void main(String[] args) {
		InputFileProcessor csvFileProcessor = new CSVFileProcessor();
		csvFileProcessor.generateReport("input.csv");
	}
}
