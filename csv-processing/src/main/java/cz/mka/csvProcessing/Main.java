package cz.mka.csvProcessing;

import cz.mka.csvProcessing.api.CsvProcessingService;
import cz.mka.csvProcessing.api.DataExportService;
import cz.mka.csvProcessing.api.model.Input;
import cz.mka.csvProcessing.api.model.Output;
import cz.mka.csvProcessing.impl.CsvProcessingServiceImpl;
import cz.mka.csvProcessing.impl.DataExportServiceImpl;

import java.util.List;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public class Main {

    public static void main(String[] args) {

        CsvProcessingService csvp = new CsvProcessingServiceImpl();

        List<Input> inputList = csvp.loadCsvData();
        List<Output> outputList = csvp.getDataByCountryAndQuarter(inputList, "Czech Republic", "2010 Q4");

        // show converted data list
        for (Output out : outputList) {
            System.out.println(out.getVendor() +  " | " + out.getUnits() + " | " + out.getShare());
        }

        // get units and share for vendor Dell
        System.out.println();
        System.out.println(csvp.getUnitsAndShareByVendor(outputList, "Dell"));

        // get row number for vendor Dell
        System.out.println();
        System.out.println(csvp.getRowNumberByVendor(outputList, "Dell"));

        // show sorted data list by vendor ascending.
        System.out.println();
        for (Output out : csvp.sortAlphabetically(outputList, false)) {
            System.out.println(out.getVendor() +  " | " + out.getUnits() + " | " + out.getShare());
        }

        // show sorted data list by unit values descending.
        System.out.println();
        for (Output out : csvp.sortByUnitValues(outputList, true)) {
            System.out.println(out.getVendor() +  " | " + out.getUnits() + " | " + out.getShare());
        }

        DataExportService des = new DataExportServiceImpl();
        // export html file to project root.
        des.exportFormattedData(outputList, "html");


    }
}
