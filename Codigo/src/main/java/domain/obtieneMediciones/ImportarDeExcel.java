package domain.obtieneMediciones;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ImportarDeExcel{
    public static void main(String[] args)
    {
        try
        {
            File file = new File(".\\excelDDS2.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            Row row = itr.next();
            row = itr.next();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            Boolean esMensual=false;
            Boolean esAnual=false;
            while (itr.hasNext())
            {
                row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column



                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
                        case STRING:    //field that represents string cell type
                            String leido=cell.getStringCellValue();
                            System.out.print(leido + "\t\t\t");
                            if(Objects.equals(leido, "Mensual")){
                                esMensual=true;
                            }
                            if(Objects.equals(leido, "Anual")){
                                esAnual=true;
                            }
                            break;
                        case NUMERIC:    //field that represents number cell type
                            if(esAnual){
                                int leido2= (int) cell.getNumericCellValue();
                                System.out.print(leido2 + "\t\t\t");
                                esAnual=false;
                            }
                            else if(esMensual) {
                                System.out.print(sdf.format(cell.getDateCellValue()) + "\t\t\t");
                                esMensual=false;
                            }
                            else {
                                System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            }
                            break;
                        default:break;
                    }
                }
                System.out.println("");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

