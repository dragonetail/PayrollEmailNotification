package com.github.dragonetail.payroll.emailnotification.employees.service;

import com.github.dragonetail.payroll.emailnotification.employees.bean.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private List<Employee> employees = new ArrayList<>();
    private Map<String, Employee> employeeMap = new HashMap<>();

    @PostConstruct
    public void init() {
        importFile("classpath:templates/employees.xlsx");
    }

    public void importFile(String templateFileLocation) {
        List<Employee> employees = new ArrayList<>();
        Map<String, Employee> employeeMap = new HashMap<>();
        try {
            URL templateFile = ResourceUtils.getURL(templateFileLocation);
            try (InputStream inp = templateFile.openStream()) {
                Workbook wb = WorkbookFactory.create(inp);
                Sheet sheet = wb.getSheetAt(0);

                int rowNo = 3;
                while (true) {
                    Row row = sheet.getRow(rowNo++);
                    if (row == null) {
                        break;
                    }
                    int colNo = 0;
                    Employee employee = new Employee();
                    Cell firstCell = row.getCell(colNo++);
                    firstCell.setCellType(CellType.STRING);
                    String idStr = firstCell.getStringCellValue();
                    if (StringUtils.isEmpty(idStr)) {
                        break;
                    }
                    long id = Long.valueOf(idStr).longValue();
                    employee.setId(id);
                    employee.setNo(getCellStringValue(row, colNo++, id));
                    employee.setName(getCellStringValue(row, colNo++, id));
                    employee.setEmail(getCellStringValue(row, colNo++, id));
                    employee.setPassword(getCellStringValue(row, colNo++, id));

                    employees.add(employee);
                    employeeMap.put(employee.getNo(), employee);
                }


                for (int i = 0; i < employees.size() - 1; i++) {
                    Employee left = employees.get(i);
                    for (int j = i + 1; j < employees.size(); j++) {
                        Employee right = employees.get(j);
                        if ((left.getId() == right.getId()) ||
                                left.getNo().equals(right.getNo()) ||
                                left.getEmail().equals(right.getEmail())) {
                            throw new IllegalStateException("数据有重复：#" + left.getId() + "（" + left.getNo() + "）" + "（" + left.getEmail() + "）");
                        }
                    }
                }

                this.employees = employees;
                this.employeeMap = employeeMap;
            }
        } catch (FileNotFoundException e) {
            logger.error("文件找不到。", e);
            throw new IllegalStateException("文件找不到: " + e.getMessage());
        } catch (InvalidFormatException e) {
            logger.error("文件格式错误。", e);
            throw new IllegalStateException("文件格式错误: " + e.getMessage());
        } catch (IOException e) {
            logger.error("文件读取失败。", e);
            throw new IllegalStateException("文件读取失败: " + e.getMessage());
        }
    }

    private String getCellStringValue(Row row, int colNo, long id) {
        String value = row.getCell(colNo).getStringCellValue();
        if (!StringUtils.hasText(value)) {
            throw new IllegalStateException("数据有空值：#" + id + "@" + colNo + "列");
        }
        return value;
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee get(String no) {
        return employeeMap.get(no);
    }

}
