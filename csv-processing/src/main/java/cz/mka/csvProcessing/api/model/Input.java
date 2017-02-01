package cz.mka.csvProcessing.api.model;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public class Input {

    private String country;
    private String timescale;
    private String vendor;
    private Double units;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimescale() {
        return timescale;
    }

    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }
}
