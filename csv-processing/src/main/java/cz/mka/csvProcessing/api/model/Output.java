package cz.mka.csvProcessing.api.model;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public class Output {

    private String vendor;
    private Double units;
    private Double share;

    public Output() {}

    public Output(String vendor, Double units, Double share) {
        this.vendor = vendor;
        this.units = units;
        this.share = share;
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

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }
}
