package tests.reuse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.invoke.MethodHandles.lookup;

public class CSVFileManager {
    private static final Logger log = LogManager.getLogger(lookup().lookupClass());
    private final List<Map<String, String>> csvData = new ArrayList<>();

    public CSVFileManager(String csvFilePath) {
        log.info("Loading and caching test data from file: [{}]", csvFilePath);
        try (FileReader reader = new FileReader(csvFilePath);
             CSVParser parser = CSVFormat.DEFAULT.builder()
                     .setHeader()
                     .setSkipHeaderRecord(true)
                     .build()
                     .parse(reader)) {

            for (CSVRecord record : parser) {
                csvData.add(record.toMap());
            }
            log.info("Successfully loaded {} rows from CSV.", csvData.size());
        } catch (IOException e) {
            log.error("Failed to read or parse CSV file [{}]: {}", csvFilePath, e.getMessage());
        }
    }

    public String getCellData(int rowIndex, String columnName) {
        if (rowIndex < 0 || rowIndex >= csvData.size()) {
            log.error("Row index {} is out of bounds (Total rows: {}).", rowIndex, csvData.size());
            return null;
        }
        Map<String, String> row = csvData.get(rowIndex);
        if (!row.containsKey(columnName)) {
            log.error("Column '{}' not found in row {}.", columnName, rowIndex);
            return null;
        }
        return row.get(columnName);
    }

    public Map<String, String> getRowData(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= csvData.size()) {
            log.error("Row index {} out of bounds.", rowIndex);
            return Collections.emptyMap();
        }
        return csvData.get(rowIndex);
    }
}