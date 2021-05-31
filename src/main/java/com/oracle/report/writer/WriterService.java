package com.oracle.report.writer;

import com.oracle.report.model.ReportData;

public class WriterService {
   private OutputWriter outputWriter;

   public void write(ReportData reportData){
       outputWriter.write(reportData);
   }

   public void setOutputWriter(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }
}
