package com.yyh.authoritymanagement.util.file;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by sunhui on 2016/7/22.
 */
//读取Excel文件内容  xls和xlsx格式
public class ExcelUtil {

  public static List<List<String>> readExcel(String path) {
    if (path == null || path.equals("")) {
      return null;
    }
    if (path.endsWith(".xlsx") || path.endsWith(".XLSX")) {
      return readXlsx(path, false);
    } else if (path.endsWith(".xls") || path.endsWith(".XLS")) {
      return readXls(path, false);
    }
    return null;
  }


  //    Read the Excel 2010
  private static List<List<String>> readXlsx(String path, boolean withTitle) {
    List<List<String>> resultList = new ArrayList<>();
    try {
      InputStream inputStream = new FileInputStream(path);
      XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        XSSFSheet sheet = workbook.getSheetAt(i);
        if (sheet == null) {
          continue;
        }
        int j = 1;
        if (withTitle) {
          j = 0;
        }

        for (; j <= sheet.getLastRowNum(); j++) {
          XSSFRow row = sheet.getRow(j);
          if (row != null) {
            int minColIndex = row.getFirstCellNum();
            int maxColIndex = row.getLastCellNum();
            List<String> rowList = new ArrayList<>();
            for (int k = minColIndex; k < maxColIndex; k++) {
              XSSFCell cell = row.getCell(k);
              if (cell == null) {
                rowList.add(null);
              } else {
                rowList.add(cell.toString());
              }
            }
            resultList.add(rowList);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return resultList;
  }


  //Read the Excel 2003-2007
  private static List<List<String>> readXls(String path, boolean withTitle) {
    List<List<String>> resultList = new ArrayList<>();
    try {
      InputStream inputStream = new FileInputStream(path);
      HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        HSSFSheet sheet = workbook.getSheetAt(i);
        if (sheet == null) {
          continue;
        }
        int j = 1;
        if (withTitle) {
          j = 0;
        }
        for (; j <= sheet.getLastRowNum(); j++) {
          HSSFRow row = sheet.getRow(j);
          if (row != null) {
            int minColIndex = row.getFirstCellNum();
            int maxColIndex = row.getLastCellNum();
            List<String> rowList = new ArrayList<>();
            for (int k = minColIndex; k < maxColIndex; k++) {
              HSSFCell cell = row.getCell(k);
              if (cell == null) {
                rowList.add(null);
              } else {
                rowList.add(getStringVal(cell));
              }
            }
            resultList.add(rowList);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return resultList;
  }


  private static String getStringVal(HSSFCell cell) {
    switch (cell.getCellType()) {
      case BOOLEAN:
        return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
      case FORMULA:
        return cell.getCellFormula();
      case NUMERIC:
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
      case STRING:
        return cell.getStringCellValue();
      default:
        return "";
    }
  }


  public static List<List<String>> readExcel(String path, boolean withTitle) {
    if (path == null || path.equals("")) {
      return null;
    }
    if (path.endsWith(".xlsx") || path.endsWith(".XLSX")) {
      return readXlsx(path, withTitle);
    } else if (path.endsWith(".xls") || path.endsWith(".XLS")) {
      return readXls(path, withTitle);
    }
    return null;
  }


  public static Map<String, List<List<String>>> readExcelSheets(String path) {
    if (path == null || path.equals("")) {
      return null;
    }
    if (path.endsWith(".xlsx") || path.endsWith(".XLSX")) {
      return readXlsxSheets(path, false);
    } else if (path.endsWith(".xls") || path.endsWith(".XLS")) {
      return readXlsSheets(path, false);
    }
    return null;
  }


  private static Map<String, List<List<String>>> readXlsxSheets(String path, boolean withTitle) {
    Map<String, List<List<String>>> listsMap = new HashMap<>();
    try {
      InputStream inputStream = new FileInputStream(path);
      XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        List<List<String>> resultList = new ArrayList<>();
        XSSFSheet sheet = workbook.getSheetAt(i);
        if (sheet == null) {
          continue;
        }
        int j = 1;
        if (withTitle) {
          j = 0;
        }

        for (; j <= sheet.getLastRowNum(); j++) {
          XSSFRow row = sheet.getRow(j);
          if (row != null) {
            int minColIndex = row.getFirstCellNum();
            int maxColIndex = row.getLastCellNum();
            List<String> rowList = new ArrayList<>();
            for (int k = minColIndex; k < maxColIndex; k++) {
              XSSFCell cell = row.getCell(k);
              if (cell == null) {
                rowList.add(null);
              } else {
                rowList.add(cell.toString());
              }
            }
            resultList.add(rowList);
          }
        }
        listsMap.put(sheet.getSheetName(), resultList);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return listsMap;
  }


  //Read the Excel 2003-2007
  private static Map<String, List<List<String>>> readXlsSheets(String path, boolean withTitle) {
    Map<String, List<List<String>>> listsMap = new HashMap<>();
    try {
      InputStream inputStream = new FileInputStream(path);
      HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        List<List<String>> resultList = new ArrayList<>();
        HSSFSheet sheet = workbook.getSheetAt(i);
        if (sheet == null) {
          continue;
        }
        int j = 1;
        if (withTitle) {
          j = 0;
        }
        for (; j <= sheet.getLastRowNum(); j++) {
          HSSFRow row = sheet.getRow(j);
          if (row != null) {
            int minColIndex = row.getFirstCellNum();
            int maxColIndex = row.getLastCellNum();
            List<String> rowList = new ArrayList<>();
            for (int k = minColIndex; k < maxColIndex; k++) {
              HSSFCell cell = row.getCell(k);
              if (cell == null) {
                rowList.add(null);
              } else {
                rowList.add(getStringVal(cell));
              }
            }
            resultList.add(rowList);
          }
        }
        listsMap.put(sheet.getSheetName(), resultList);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return listsMap;
  }


  public static Map<String, List<List<String>>> readExcelSheets(String path, boolean withTitle) {
    if (path == null || path.equals("")) {
      return null;
    }
    if (path.endsWith(".xlsx") || path.endsWith(".XLSX")) {
      return readXlsxSheets(path, withTitle);
    } else if (path.endsWith(".xls") || path.endsWith(".XLS")) {
      return readXlsSheets(path, withTitle);
    }
    return null;
  }


  /**
   * 输出到Excel文件，后缀名是.xls
   *
   * @param titleList 标题头集合
   * @param list      数据集合
   * @param outPath   输出文件路径
   */
  public static void writeExcel(List<String> titleList, List<List<String>> list, String outPath) {
    File file = new File(outPath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();//创建父目录
    }
    if (titleList == null || list == null) {
      System.out.println("写入的数据为空");
      return;
    }
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("sheet1");
    HSSFRow row = sheet.createRow(0);
    HSSFCellStyle style = wb.createCellStyle();
    style.setAlignment(HorizontalAlignment.CENTER);

    //设置标题头
    for (int i = 0; i < titleList.size(); i++) {
      HSSFCell cell = row.createCell((short) i);
      cell.setCellValue(titleList.get(i));
      cell.setCellStyle(style);
    }
    //写入数据
    for (int i = 0; i < list.size(); i++) {
      List<String> innerList = list.get(i);
      HSSFRow dataRow = sheet.createRow(i + 1);
      for (int j = 0; j < innerList.size(); j++) {
        HSSFCell dataCell = dataRow.createCell((short) j);
        dataCell.setCellValue(innerList.get(j));
        dataCell.setCellStyle(style);
      }
    }


    try {
      FileOutputStream outputStream = new FileOutputStream(outPath);
      wb.write(outputStream);
      outputStream.close();
      wb.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  public static void writeExcels(Map<String, List<List<String>>> listsMap, String outPath) {
    File file = new File(outPath);
    if (!file.getParentFile().exists()) {
      file.getParentFile().mkdirs();//创建父目录
    }

    HSSFWorkbook wb = new HSSFWorkbook();
    Set<String> sheetNames = listsMap.keySet();
    for (String sheetName : sheetNames) {
      List<List<String>> list = listsMap.get(sheetName);
      if (list == null) {
        System.out.println(sheetName + "写入的数据为空");
        //return;
        continue;
      }
      HSSFSheet sheet = wb.createSheet(sheetName);
      /*HSSFRow row = sheet.createRow(0);*/
      HSSFCellStyle style = wb.createCellStyle();
      style.setAlignment(HorizontalAlignment.CENTER);

      //设置标题头
      /*  for (int i = 0; i < titleList.size(); i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellValue(titleList.get(i));
            cell.setCellStyle(style);
        }*/
      //写入数据
      for (int i = 0; i < list.size(); i++) {
        List<String> innerList = list.get(i);
        HSSFRow dataRow = sheet.createRow(i);
        for (int j = 0; j < innerList.size(); j++) {
          HSSFCell dataCell = dataRow.createCell((short) j);
          dataCell.setCellValue(innerList.get(j));
          dataCell.setCellStyle(style);
        }
      }
    }
    try {
      FileOutputStream outputStream = new FileOutputStream(outPath);
      wb.write(outputStream);
      outputStream.close();
      wb.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
