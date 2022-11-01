package io.atulandjava.org.Coronavirustrackerbeta.model;

public class LocationStats {
    private String state;
    private String country;
    private int confirmedCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int totalReportedCases) {
        this.confirmedCases = totalReportedCases;
    }

}
