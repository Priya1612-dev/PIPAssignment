package com.employeeCrudDetails.employeePermissionManagement.Helper;

import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class XlsHelper {
    public List<EmployeeDetails> readXlsFile(InputStream is ){
        List<EmployeeDetails> records=new ArrayList<>();
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
                            employeeDetails.setCrudToDo(cell.getStringCellValue());
                            break;
                        case 2:
                            employeeDetails.setDomain(cell.getStringCellValue());
                            break;
                        case 3:
                            employeeDetails.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            employeeDetails.setName(cell.getStringCellValue());
                            break;
                        case 5:
                            employeeDetails.setOrg(cell.getStringCellValue());
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

