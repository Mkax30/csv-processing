package cz.mka.csvProcessing.api;

import cz.mka.csvProcessing.api.model.Input;
import cz.mka.csvProcessing.api.model.Output;

import java.util.List;

/**
 * Created by Martin Kaspar on 30/01/2017.
 */
public interface CsvProcessingService {

    /**
     * Loads data from csv file.
     * @return data input
     */
    List<Input> loadCsvData();

    /**
     * Creates data table from input list.
     * @param inputList
     * @param country Country value for filter data list (e.g. 'Czech Republic')
     * @param quarter quarter value for filter data list (e.g. '2010 Q4')
     * @return output data list
     */
    List<Output> getDataByCountryAndQuarter(List<Input> inputList,
                                            String country,
                                            String quarter);

    /**
     * Returns units and share by vendor.
     * @param outputList
     * @param vendor vendor value for filter data list (e.g. 'Dell')
     * @return formatted string with units and share
     */
    String getUnitsAndShareByVendor(List<Output> outputList,
                                    String vendor);

    /**
     * Returns specific row by vendor.
     * @param outputList
     * @param vendor vendor value for filter data list (e.g. 'Dell')
     * @return integer value of data list row
     */
    Integer getRowNumberByVendor(List<Output> outputList,
                                 String vendor);

    /**
     * Sorts data list alphabetically.
     * @param outputList
     * @param sort true or false (ascending or descending sorting)
     * @return
     */
    List<Output> sortAlphabetically(List<Output> outputList,
                                    boolean sort);

    /**
     * Sorts data list by units.
     * @param outputList
     * @param sort true or false (ascending or descending sorting)
     * @return
     */
    List<Output> sortByUnitValues(List<Output> outputList,
                                  boolean sort);


}
