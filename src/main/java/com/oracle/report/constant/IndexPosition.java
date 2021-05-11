package com.oracle.report.constant;

/**
 * Enumaration of index position in the input data line
 */
public enum IndexPosition {
    CUSTOMER_ID(0),
    CONTRACT_ID(1),
    GEO_ZONE(2),
    TEAM_CODE(3),
    PROJECT_CODE(4),
    BUILD_DURATION(5)
    ;

    private final Integer value;

    public Integer value(){
        return this.value;
    }

    IndexPosition(Integer value) {
        this.value = value;
    }
}
