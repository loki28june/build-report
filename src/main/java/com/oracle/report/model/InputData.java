package com.oracle.report.model;

public class InputData<T, S, U> {
    private T customerId;
    private T contractId;
    private S geoZone;
    private S teamCode;
    private S projectCode;
    private U buildDuration;

    public T getCustomerId() {
        return customerId;
    }

    public void setCustomerId(T customerId) {
        this.customerId = customerId;
    }

    public T getContractId() {
        return contractId;
    }

    public void setContractId(T contractId) {
        this.contractId = contractId;
    }

    public S getGeoZone() {
        return geoZone;
    }

    public void setGeoZone(S geoZone) {
        this.geoZone = geoZone;
    }

    public S getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(S teamCode) {
        this.teamCode = teamCode;
    }

    public S getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(S projectCode) {
        this.projectCode = projectCode;
    }

    public U getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(U buildDuration) {
        this.buildDuration = buildDuration;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "customerId=" + customerId +
                ", contractId=" + contractId +
                ", geoZone=" + geoZone +
                ", teamCode=" + teamCode +
                ", projectCode=" + projectCode +
                ", buildDuration=" + buildDuration +
                '}';
    }
}
