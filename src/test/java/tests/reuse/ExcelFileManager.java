package tests.reuse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFileManager {
    private XSSFWorkbook workbook;
    private Sheet sheet;
    public ExcelFileManager(String filePath,String sheetName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet(sheetName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ExcelFileManager(String filePath,int sheetIndex) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(sheetIndex);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    public int getColumnCount(){
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }
    public void changesheet(String sheetName){
        sheet = workbook.getSheet(sheetName);
    }
    public void changesheet(int sheetIndex){
        sheet = workbook.getSheetAt(sheetIndex);
    }
    public String getSpecificCellData(int rowNumber,int columnNumber){
        try{
            Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
            DataFormatter dataFormatter = new DataFormatter();
            return dataFormatter.formatCellValue(cell);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<String[]> getRows(){
        List<String[]> data = new ArrayList<>();
        for(int i =1;i<getColumnCount();i++)
        {
            String [] rowData = new String[getColumnCount()];
            for(int j = 0;i < rowData.length;j++)
            {
                rowData[j] = getSpecificCellData(i,j);
            }
            data.add(rowData);
        }
        return data;
    }
    public List<String> getHeaders(){
        List<String> headerName = new ArrayList<>();
        try
        {
            for(int i = 0;i<getColumnCount();i++){
                headerName.add(getSpecificCellData(0,i));
            }
            return headerName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Map<String,List<String>> getColumnData(){
        HashMap<String,List<String>> columnData = new HashMap<>();
        List<String> headers= getHeaders();
        try{
            for(int i  = 0;i<getColumnCount();i++)
            {
                List<String> data  = new ArrayList<>();
                for (int j = 1;j<getRowCount();j++){
                    data.add(getSpecificCellData(j,i));
                }
                columnData.put(headers.get(i),data);
            }
            return columnData;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
