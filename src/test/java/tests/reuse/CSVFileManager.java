package tests.reuse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.*;

public class CSVFileManager {
    private FileReader rowReader;
    private FileReader reader;
    private CSVParser records;
    private Iterable<CSVRecord> rowRecords;
    private List<String[]> rows;
    private List<String> columns;
    private Map<String,List<String>> data;

    public CSVFileManager(String filePath){
        try
        {
            reader = new FileReader(filePath);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            rowReader = new FileReader(filePath);
            rowRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(rowReader);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<String[]> getRows(){
        rows = new ArrayList<>();
        try
        {
            for(CSVRecord record : rowRecords)
            {
                String [] rowCell = new String[record.size()];
                for(int i = 0 ; i < record.size();i++)
                {
                    rowCell[i] = record.get(i);
                }
                rows.add(rowCell);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return rows;
    }
    public List<String> getColumns(){
        try
        {
            columns = new ArrayList<>(records.getHeaderNames());
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    public Map<String,List<String>> getColumnsWithData(){
        data = new HashMap<>();
        try
        {
            List<String[]> rows = getRows();
            List<String> columns = getColumns();
            for(int i = 0; i< columns.size(); i++)
            {
                List<String> columnData = new ArrayList<>();
                for(String[] row : rows)
                {
                    columnData.add(row[i]);
                }
                data.put(columns.get(i),columnData);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    /*public String getLastColumn(){
        try
        {
            List<String> columns = getColumns();
            return columns.getLast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public String getSpecificColumnName(int ColumnNum){
        try
        {
            List<String> columns = getColumns();
            return columns.get(ColumnNum-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getSpecificColumnData(String ColumnName){
        try
        {
            Map<String,List<String>> columnData = getColumnsWithData();
            return columnData.get(ColumnName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getSpecificColumnData(int ColumnIndex){
        String columnName;
        try
        {
            Map<String,List<String>> columnData = getColumnsWithData();
            columnName = getSpecificColumnName(ColumnIndex);
            return columnData.get(columnName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getCellData(int RowNum, String ColumnName){
        try
        {
            List<String> cellData = getSpecificColumnData(ColumnName);
            return cellData.get(RowNum-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getCellData(int RowNum, int ColumnIndex){
        try
        {
            List<String> cellData = getSpecificColumnData(ColumnIndex);
            return cellData.get(RowNum-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getFirstColumn(){
        try
        {
             List<String> columns = getColumns();
             return columns.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getCellCount(String columnName){
        int cellCount = 0;
        try
        {
            List<String> columnData = getSpecificColumnData(columnName);
            cellCount = columnData.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellCount;
    }
}
