package cz.mka.csvProcessing.api;

import cz.mka.csvProcessing.api.model.Output;

import java.util.List;

/**
 * Created by Martin Kaspar on 01/02/2017.
 */
public interface DataExportService {

    /**
     * Exports formatted data table.
     * @param outputList
     * @param method export method (e.g. csv, excel, html)
     */
    void exportFormattedData(List<Output> outputList,
                             String method);

}
