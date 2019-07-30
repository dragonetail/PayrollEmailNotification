package com.github.dragonetail.payroll.emailnotification.payrolls.service;

import com.github.dragonetail.payroll.emailnotification.employees.bean.Employee;
import com.github.dragonetail.payroll.emailnotification.payrolls.bean.Payroll;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollService {
    private static Logger logger = LoggerFactory.getLogger(PayrollService.class);
    private List<Payroll> payrolls = new ArrayList<>();

    @PostConstruct
    public void init() {
        importFile("classpath:templates/payrolls.xlsx");
    }

    public void importFile(String templateFileLocation) {
        System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.SystemOutLogger");
        System.setProperty("poi.log.level", POILogger.INFO + "");

        List<Payroll> payrolls = new ArrayList<>();
        try {
            File templateFile = ResourceUtils.getFile(templateFileLocation);
            try (InputStream inp = new FileInputStream(templateFile)) {
                Workbook wb = WorkbookFactory.create(inp);
                Sheet sheet = wb.getSheetAt(0);

                XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
                HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);

                int rowNo = 3;
                while (true) {
                    Row row = sheet.getRow(rowNo++);
                    if (row == null) {
                        break;
                    }
                    int colNo = 0;
                    Payroll payroll = new Payroll();
                    Cell firstCell = row.getCell(colNo++);
                    firstCell.setCellType(CellType.STRING);
                    String idStr = firstCell.getStringCellValue();
                    if (StringUtils.isEmpty(idStr)) {
                        break;
                    }

                    long id = Long.valueOf(idStr).longValue();
                    payroll.setId(id);
                    payroll.setNo(getCellStringValue(wb, row, colNo++, id));
                    payroll.setName(getCellStringValue(wb, row, colNo++, id));
                    payroll.setDepartment(getCellStringValue(wb, row, colNo++, id));
                    payroll.setLevel(getCellStringValue(wb, row, colNo++, id));
                    payroll.setBaseSalary(getCellStringValue(wb, row, colNo++, id));
                    payroll.setJobSalary(getCellStringValue(wb, row, colNo++, id));
                    payroll.setAwardSalary(getCellStringValue(wb, row, colNo++, id));
                    payroll.setOtherSalary(getCellStringValue(wb, row, colNo++, id));
                    payroll.setSocialInsurance(getCellStringValue(wb, row, colNo++, id));
                    payroll.setHousingFund(getCellStringValue(wb, row, colNo++, id));
                    payroll.setSpecialAdditionalDeduction(getCellStringValue(wb, row, colNo++, id));
                    colNo = colNo + 5; //Skip 5 columns
                    payroll.setLeavingDeduction(getCellStringValue(wb, row, colNo++, id));
                    payroll.setOtherDeduction(getCellStringValue(wb, row, colNo++, id));
                    payroll.setGivingWithTax(getCellStringValue(wb, row, colNo++, id));
                    payroll.setTax(getCellStringValue(wb, row, colNo++, id));
                    payroll.setGiving(getCellStringValue(wb, row, colNo++, id));
                    payroll.setRemarks(getCellStringValue(wb, row, colNo++, id));

                    payroll.setSentStatus("-");
                    payroll.setSentTime("-");

                    payrolls.add(payroll);
                }

                for (int i = 0; i < payrolls.size() - 1; i++) {
                    Payroll left = payrolls.get(i);
                    for (int j = i; j < payrolls.size(); j++) {
                        Payroll right = payrolls.get(j);
                        if ((left.getId() == right.getId()) ||
                                left.getNo().equals(right.getNo())) {
                            throw new IllegalStateException("数据有重复：#" + left.getId() + "（" + left.getNo() + "）" );
                        }
                    }
                }
                this.payrolls = payrolls;
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

    private String getCellStringValue(Workbook wb, Row row, int colNo, long id) {
        String value = getCellStringInternal(wb, row, colNo);
        if (!StringUtils.hasText(value)) {
            throw new IllegalStateException("数据有空值：#" + id + "@" + colNo + "列");
        }
        return value;
    }

    private String getCellStringInternal(Workbook wb, Row row, int colNo) {
        Cell cell = row.getCell(colNo);
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case FORMULA:
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                evaluator.evaluateInCell(cell);
                return getCellStringInternal(wb, row, colNo);
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return new DecimalFormat("#.##").format(cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue() == true ? "true" : "false";
            default:
                cell.setCellType(CellType.STRING);
                return cell.getStringCellValue();
        }
    }

    public List<Payroll> findAll() {
        return payrolls;
    }

}
