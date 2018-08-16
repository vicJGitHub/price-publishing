package com.hywa.pricepublish.common.utils;

import com.hywa.pricepublish.common.exception.GlobalException;
import jxl.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelUtils {
    /**
     * 导出excel
     */
    public static void exportExcel(List<?> list, HttpServletResponse response, String tableName, String tableTitle,
                                   String sheetName, LinkedHashMap<String, String> filedNames) {

        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //中文文件名做iso-8859-1转码
            String convertCode = new String(tableName.getBytes("gbk"), "iso8859-1") + ".xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + convertCode);
            //创建Excel文件
            WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
            //创建Sheet页
            WritableSheet sheet = workbook.createSheet(sheetName, 0);

            // 设置标题字体,字体为Arial,字号大小为22,采用黑体显示
            WritableFont titleBold = new WritableFont(WritableFont.ARIAL, 22, WritableFont.BOLD);

            // 设置列名字体,字体为COURIER,字号大小为14,采用黑体显示
            WritableFont columnBold = new WritableFont(WritableFont.COURIER, 18, WritableFont.BOLD);

            // 设置单元格字体,字体为COURIER,字号大小为14,采用黑体显示
            WritableFont cellBold = new WritableFont(WritableFont.COURIER, 14, WritableFont.NO_BOLD);

            // 标题单元格样式控制对象
            WritableCellFormat titleFormat = new WritableCellFormat(titleBold);
            titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleFormat.setAlignment(Alignment.CENTRE);
            titleFormat.setWrap(true);//是否自动换行
            titleFormat.setBackground(Colour.BRIGHT_GREEN);//单元格背景色
            titleFormat.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //给单元格加边框

            // 列名单元格样式控制对象
            WritableCellFormat columnFormat = new WritableCellFormat(columnBold);
            columnFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            columnFormat.setAlignment(Alignment.CENTRE);
            columnFormat.setBackground(Colour.ORANGE);//单元格背景色
            columnFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //给单元格加边框
            //columnFormat.setShrinkToFit(true);//设置缩放字体适应单元格大小
            //columnFormat.setOrientation(Orientation.VERTICAL);//设置字体方向

            // 列名单元格样式控制对象
            WritableCellFormat cellFormat = new WritableCellFormat(cellBold);
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            cellFormat.setAlignment(Alignment.CENTRE);
            cellFormat.setWrap(true);//是否自动换行
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //给单元格加边框

            // 添加表头
            Label label0 = new Label(0, 0, tableTitle, titleFormat);
            sheet.setRowView(0, 1300, false); //设置行高
            sheet.addCell(label0);

            //添加列名
            List<String> column = new ArrayList<>(filedNames.values());
            for (int i = 0; i < column.size(); i++) {
                Label label = new Label(i, 1, column.get(i), columnFormat);
                CellView cellView = new CellView();
                cellView.setAutosize(true); //设置自动大小
                sheet.setColumnView(i, cellView);//根据内容自动设置列宽
                sheet.addCell(label);
            }

            //添加数据
            List<String> fields = new ArrayList<>(filedNames.keySet());
            addData(list, sheet, fields, cellFormat);

            //合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
            sheet.mergeCells(0, 0, column.size() - 1, 0);

            //将数据写入文件
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入excel
     */
    public static List<Map<String, String>> importExcel(MultipartFile file, String filePath) {

        if (file == null) {
            throw new GlobalException("文件为空");
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        if (!suffixName.equals(".xlsx") && !suffixName.equals(".xls")) {
            throw new GlobalException("上传文件格式错误，仅支持.xls和.xlsx两种格式的文件");
        }

        try {
            File dest = new File(filePath + fileName);
            // 检测是否存在目录
            if (dest.exists()) {
                throw new GlobalException("文件已经存在");
            } else if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);

            List<Map<String, String>> list = new ArrayList<>();
            //打开文件
            Workbook book = Workbook.getWorkbook(dest);
            Sheet sheet = book.getSheet(0);
            for (int i = 2; i < sheet.getRows(); i++) {//sheet.getRows()获取行数
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < sheet.getColumns(); j++) {//sheet.getColumns()获取列数
                    Cell cell = sheet.getCell(j, i);

                    if (cell.getType() == CellType.NUMBER) { //取数字的时候强转一下,否则默认只取出小数点后3位
                        NumberCell numberCell = (NumberCell) cell;
                        double value = numberCell.getValue();
                        map.put(sheet.getCell(j, 1).getContents(), String.valueOf(value));
                        continue;
                    }
                    map.put(sheet.getCell(j, 1).getContents(), cell.getContents());
                }
                list.add(map);
            }

            book.close();
            return list;
        } catch (Exception e) {
            throw new GlobalException("上传失败" + e.getMessage());
        }
    }

    private static void addData(List<?> list, WritableSheet sheet, List<String> fields,
                                CellFormat cellFormat) throws WriteException {
        for (int j = 0; j < list.size(); j++) {
            for (int i = 0; i < fields.size(); i++) {
                Object fieldValueByName = getFieldValueByName(fields.get(i), list.get(j));
                Label label = new Label(i, j + 2, fieldValueByName == null ? " " : fieldValueByName.toString(), cellFormat);
                sheet.addCell(label);
            }
        }
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "getChildren" + firstLetter + fieldName.substring(1);
            String methodName="get"+ firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(methodName, new Class[]{});
            return method.invoke(o, new Object[]{});
        } catch (Exception e) {
            return null;
        }
    }

}
