package cz.mka.csvProcessing.impl;

import cz.mka.csvProcessing.api.DataExportService;
import cz.mka.csvProcessing.api.model.Export;
import cz.mka.csvProcessing.api.model.Output;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Martin Kaspar on 01/02/2017.
 */
public class DataExportServiceImpl implements DataExportService {

    // constants for header and total row
    public static final String C_VENDOR = "Vendor";
    public static final String C_UNITS = "Units";
    public static final String C_SHARE = "Share";
    public static final String C_TOTAL = "Total";
    public static final String C_100 = "100 %";

    public void exportFormattedData(List<Output> outputList, String method) {
        if (outputList.isEmpty() || StringUtils.isBlank(method)) {
            throw new NullPointerException("Data list or export method is null or empty!");
        }
        switch (method) {
            case "csv": exportToCsv(outputList);
                break;
            case "excel": exportToExcel(outputList);
                break;
            case "html": exportToHtml(outputList);
                break;
            default: exportToCsv(outputList);
                break;
        }
    }

    private void exportToCsv(List<Output> outputList) {
        // TODO
    }

    private void exportToExcel(List<Output> outputList) {
        // TODO
    }

    private void exportToHtml(List<Output> outputList) {
        List<Export> exportList = prepareForExport(outputList);

        File file = new File("test.html");

        StringBuilder sb = new StringBuilder();

        sb.append("<html>");
        // no need head
        sb.append("<body>");
        sb.append("<table>");

        for (Export ex : exportList) {
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(ex.getVendor());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(ex.getUnits());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(ex.getShare());
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");

        try {
            FileUtils.writeStringToFile(file, sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Export> prepareForExport(List<Output> outputList) {
        List<Export> exportList = outputList.stream()
                .map(o -> new Export(o.getVendor(), String.valueOf(o.getUnits()), String.valueOf(o.getShare()) + " %")).collect(Collectors.toList());

        Double totalUnits = 0D;
        for (Output out : outputList) {
            totalUnits += out.getUnits();
        }

        exportList.add(0, new Export(C_VENDOR, C_UNITS, C_SHARE));
        exportList.add(new Export(C_TOTAL, String.valueOf(totalUnits), C_100));

        return exportList;
    }


}
