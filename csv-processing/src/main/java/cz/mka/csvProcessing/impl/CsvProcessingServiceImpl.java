package cz.mka.csvProcessing.impl;


import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import cz.mka.csvProcessing.api.CsvProcessingService;
import cz.mka.csvProcessing.api.model.Input;
import cz.mka.csvProcessing.api.model.Output;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public class CsvProcessingServiceImpl implements CsvProcessingService {

    public List<Input> loadCsvData() {
        String dataFile = "data/data.csv";

        // load data file from resources
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(dataFile);

        HeaderColumnNameTranslateMappingStrategy<Input> mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<>();
        mappingStrategy.setType(Input.class);

        Map<String, String> columnMapping = new HashMap<>();
        columnMapping.put("country", "country");
        columnMapping.put("timescale", "timescale");
        columnMapping.put("vendor", "vendor");
        columnMapping.put("units", "units");
        mappingStrategy.setColumnMapping(columnMapping);

        CsvToBean ctb = new CsvToBean();
        List list = ctb.parse(mappingStrategy, new InputStreamReader(is));

        List<Input> inputList = new ArrayList<>();
        for (Object o : list) {
            inputList.add((Input) o);
        }

        return inputList;
    }

    public List<Output> getDataByCountryAndQuarter(List<Input> inputList, String country, String quarter) {
        if (inputList.isEmpty() || StringUtils.isBlank(country) ||  StringUtils.isBlank(quarter)) {
            throw new NullPointerException("Input data list, country or quarter are null or empty!");
        }

        // filter input data list
        List<Input> inputListFiltered = inputList.stream()
                .filter(l -> l.getCountry().equals(country) && l.getTimescale().equals(quarter))
                .collect(Collectors.toList());

        // count all units from filtered list
        Double totalUnits = 0D;
        for (Input in : inputListFiltered) {
                totalUnits += in.getUnits();
        }

        DecimalFormat df = new DecimalFormat("##.00");
        List<Output> outputList = new ArrayList<>();

        // make new data list
        for (Input in : inputListFiltered) {
            Output output = new Output();
            output.setVendor(in.getVendor());
            output.setUnits(in.getUnits());
            output.setShare(Double.parseDouble(df.format(in.getUnits() / totalUnits * 100.0)));

            outputList.add(output);
        }

        return outputList;
    }

    public String getUnitsAndShareByVendor(List<Output> outputList, String vendor) {
        if (outputList.isEmpty() || StringUtils.isBlank(vendor)) {
            throw new NullPointerException("Output data list or vendor is null or empty!");
        }

        List<Output> filteredList = outputList.stream()
                .filter(l -> l.getVendor().equals(vendor))
                .collect(Collectors.toList());

        Output out = filteredList.get(0);
        return vendor + ": " + out.getUnits() + ", " + out.getShare() + "%";
    }

    public Integer getRowNumberByVendor(List<Output> outputList, String vendor) {
        if (outputList.isEmpty() || StringUtils.isBlank(vendor)) {
            throw new NullPointerException("Output data list or vendor is null or empty!");
        }

        for (Output out : outputList) {
            if (vendor.equals(out.getVendor())) {
                return outputList.indexOf(out) + 1;
            }
        }
        return null;
    }

    public List<Output> sortAlphabetically(List<Output> outputList, boolean sort) {
        if (outputList.isEmpty()) {
            throw new NullPointerException("Output data list is null or empty!");
        }
        if (sort) {
            outputList.sort(Comparator.comparing(Output::getVendor).reversed());
        } else {
            outputList.sort(Comparator.comparing(Output::getVendor));
        }
        return outputList;
    }

    public List<Output> sortByUnitValues(List<Output> outputList, boolean sort) {
        if (outputList.isEmpty()) {
            throw new NullPointerException("Output data list is null or empty!");
        }
        if (sort) {
            outputList.sort(Comparator.comparing(Output::getUnits).reversed());
        } else {
            outputList.sort(Comparator.comparing(Output::getUnits));
        }
        return outputList;
    }
}
