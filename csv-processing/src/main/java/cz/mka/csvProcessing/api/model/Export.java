package cz.mka.csvProcessing.api.model;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public class Export {

    private String vendor;
    private String units;
    private String share;

    public Export() {}

    public Export(String vendor, String units, String share) {
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }
}
