package com.oracle.report;

import com.oracle.report.constant.FileType;
import com.oracle.report.constant.InputType;
import com.oracle.report.factory.InputSourceFactory;
import com.oracle.report.factory.ReaderFactory;
import com.oracle.report.factory.WriterFactory;
import com.oracle.report.model.InputData;
import com.oracle.report.model.ReportData;
import com.oracle.report.process.ProcessingService;
import com.oracle.report.reader.InputReader;
import com.oracle.report.reader.ReaderService;
import com.oracle.report.validation.InputSourceValidator;
import com.oracle.report.writer.OutputWriter;
import com.oracle.report.writer.WriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * This class is responsible for starting and executing the application code.
 */
public class ReportApplication {
    private static Logger logger = LoggerFactory.getLogger(ReportApplication.class);

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/main/resources/application.properties");
        Properties p = new Properties();
        p.load(reader);

        //input source and data validation
        InputSourceValidator inputSourceValidator = InputSourceFactory.createInputSource(InputType.resolve(p.getProperty("input.source.type")));
        inputSourceValidator.validate();
        logger.info("input validation is completed");

        // reader
        InputReader inputReader = ReaderFactory.createReader(FileType.resolve(p.getProperty("input.source.file.type")));
        ReaderService readerService = new ReaderService();
        readerService.setInputReader(inputReader);
        List<InputData> inputDataList = readerService.performRead();
        logger.info("Reader Execution is completed");

        //processor
        ProcessingService processingService = new ProcessingService();
        ReportData reportData = processingService.buildReportData(inputDataList);
        logger.info("Processor execution is completed");

        //writer
        WriterService writerService = new WriterService();
        OutputWriter outputWriter = WriterFactory.createWriter(FileType.resolve(p.getProperty("input.source.file.type")));
        writerService.setOutputWriter(outputWriter);
        writerService.write(reportData);
        logger.info("Writer is completed");
    }
}
