package config.utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ReadFromExcel {
    public static void main(String[] args) {
        String filePath= "../Practice_LearnJava_QE_SUMMER2022/DataTest/TestData-Automation.docx7.xlsx";
        Workbook workbook;
        FileInputStream inputSream;
        try{
            inputSream= new FileInputStream(filePath);
            workbook= new XSSFWorkbook(inputSream);
            Sheet dataTypeSheet=workbook.getSheetAt(1);
            Iterator<Row> rowIterator= dataTypeSheet.rowIterator();
            while (rowIterator.hasNext()){
                Row currentRow= rowIterator.next();
              //  Iterator<Cell> cellIterator= currentRow.iterator();

            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
