package com.oracle.report.writer;

import com.oracle.report.model.ReportData;

public interface OutputWriter {
    void write(ReportData reportData);
}
