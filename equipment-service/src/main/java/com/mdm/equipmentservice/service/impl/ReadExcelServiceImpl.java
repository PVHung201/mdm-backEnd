package com.mdm.equipmentservice.service.impl;

import com.mdm.equipmentservice.model.entity.EquipmentStatus;
import com.mdm.equipmentservice.model.entity.RiskLevel;
import com.mdm.equipmentservice.service.ReadExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.mdm.equipmentservice.constant.DateTimeFormatConstant.DATE_EXCEL_FORMATTER;

@Service
public class ReadExcelServiceImpl<T> implements ReadExcelService<T> {

    private static final int ROW_INDEX_START = 4;

    public List<T> readExcelFile(InputStream inputStream, String excelFile, Class<T> tClass)
            throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        List<T> list = new ArrayList<>();
        Workbook workbook = getWorkbook(inputStream, excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() < ROW_INDEX_START - 1) {
                continue;
            }

            Iterator<Cell> cellIterator = row.cellIterator();

            T instance = tClass.getDeclaredConstructor().newInstance();
            List<Method> setterMethods = getSetterMethods(tClass);

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                String cellValue = getCellValue(nextCell);
                Method method = setterMethods.get(columnIndex);

                Class<?> arg = method.getParameterTypes()[0];
                switch (arg.getSimpleName()) {
                    case "LocalDateTime" -> {
                        LocalDateTime localDateTime = LocalDate.parse(
                                cellValue,
                                DATE_EXCEL_FORMATTER
                        ).atStartOfDay();
                        method.invoke(instance, localDateTime);
                    }
                    case "Year" -> method.invoke(instance, Year.parse(cellValue));
                    case "Integer" -> {
                        Integer integer = Integer.valueOf(cellValue);
                        method.invoke(instance, integer);
                    }
                    case "Long" -> {
                        Long longValue = Long.valueOf(cellValue);
                        method.invoke(instance, longValue);
                    }
                    case "EquipmentStatus" -> {
                        EquipmentStatus equipmentStatus = EquipmentStatus.valueOf(cellValue);
                        method.invoke(instance, equipmentStatus);
                    }
                    case "RiskLevel" -> {
                        RiskLevel riskLevel = RiskLevel.valueOf(cellValue);
                        method.invoke(instance, riskLevel);
                    }
                    default -> method.invoke(instance, cellValue);
                }

            }
            list.add(instance);
        }
        workbook.close();
        inputStream.close();
        return list;
    }


    // get workbook from file
    public Workbook getWorkbook(InputStream inputStream, String excelFile) throws IOException {
        Workbook workbook;
        if (excelFile.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFile.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    // get cell value with string format
    public String getCellValue(Cell cell) {
        CellType cellStyle = cell.getCellType();
        DataFormatter dataFormatter = new DataFormatter();
        String cellValue = dataFormatter.formatCellValue(cell);
        switch (cellStyle) {
            case STRING, NUMERIC -> {
                return cellValue;
            }
            default -> {
                return null;
            }
        }
    }

    // get all setter methods of class
    private List<Method> getSetterMethods(Class<T> tClass) {
        List<String> fieldName = Arrays.stream(tClass.getDeclaredFields()).map(Field::getName).toList();
        List<Method> listSetMethods = new ArrayList<>();
        for (String name : fieldName) {
            try {
                String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                Class<?> type = tClass.getDeclaredField(name).getType();
                Method m = tClass.getDeclaredMethod(setMethodName, type);
                listSetMethods.add(m);
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return listSetMethods;
    }

}
