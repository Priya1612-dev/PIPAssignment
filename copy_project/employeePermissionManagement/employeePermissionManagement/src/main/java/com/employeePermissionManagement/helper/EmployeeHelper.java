package com.employeePermissionManagement.helper;

import com.employeePermissionManagement.dto.EmployeeDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeHelper {


    //public ResponseEntity<List<EmployeeDetails>> readXlsFile(String filepath) throws  IOException{
    public static List<EmployeeDetails> convertExcelToList(InputStream is) {
        List<EmployeeDetails> records=new ArrayList<>();
////        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath))){
////            Sheet sheet = workbook.getSheetAt(0);
////           Iterator<Row> rowIterator = sheet.iterator();
////           while (rowIterator.hasNext()) {
////               Row row = rowIterator.next();
////               Iterator<Cell> cellIterator = row.cellIterator();
////               while (cellIterator.hasNext()) {
////                   Cell cell = cellIterator.next();
////                   switch (cell.getCellType()) {
////                       case STRING:
////                            records.add(cell.getStringCellValue());
////                   break;
////                   case NUMERIC:
////                       records.add(Double.toString(cell.getNumericCellValue()));
////                   break;
////
////                   }}}}
////        catch (IOException | IllegalArgumentException | IllegalStateException e)
////    {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(records);
////    }
////    return ResponseEntity.ok(records);}}
//        try
//        {
//            FileInputStream file = new FileInputStream(filepath);
//            HashMap<Integer, EmployeeDetails> mp= new HashMap<>();
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext())
//            {
//                Row row = rowIterator.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext())
//                {
//                    Cell cell = cellIterator.next();
//                    int i=0;
//                    int j=0;
//                    switch (cell.getCellType())
//                    {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            i=Integer.parseInt(cell.getNumericCellValue());
//                            EmployeeDetails employee= new EmployeeDetails();
//                            employee.setEmpId(cell.getNumericCellvalue());
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            if( j==0){
//                                EmployeeDetails data= mp.get(i);
//                                data.setName(cell.getStringCellValue());
//                                mp.put(i, data);
//                                j=j+1;
//                            }
//                            else
//                            {
//                                EmployeeDetails data= mp.get(i);
//                                data.setDomain(cell.getStringCellValue());
//                                mp.put(i, data);
//                                j=0;
//                            }
//                            break;
//                    }
//                }
//            }
//            List<Data> dataList=  new ArrayList<Data>();
//            for (Data d : mp.values()) {
//                dataList.add(d);
//
//            }
//            file.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            int rowNumber = 0;

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                EmployeeDetails employeeDetails = new EmployeeDetails();

                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            employeeDetails.setEmpId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            employeeDetails.setDomain(cell.getStringCellValue());
                            break;
                        case 2:
                            employeeDetails.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            employeeDetails.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            employeeDetails.setOrg(cell.getStringCellValue());
                            break;
                        case 5:
                            employeeDetails.setCrudToDo(cell.getStringCellValue());
                            break;

                        default:
                            break;
                    }
                    cid++;
                }

                records.add(employeeDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;


    }
}
