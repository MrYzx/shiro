package com.yzx.shiro;

import com.yzx.shiro.beans.SysUser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class MyTest {

    @Test
    public void testString() throws UnsupportedEncodingException {
        String a = "我爱你";
        System.out.println(a.getBytes());
        String b = new String(a.getBytes("utf-8"),"utf-8");
        System.out.println("bb==="+b);
    }

    @Test
    public void testOs(){
        String aa = System.getProperty("os.name").toLowerCase();
        System.out.println("aa=="+aa);
    }

    @Test
    public void FanShe() throws IllegalAccessException {
        SysUser sysUser = new SysUser();
        sysUser.setUserName("lisi");
        Class clazz = SysUser.class;
        Field[] field = clazz.getDeclaredFields();
        for(Field field1 : field){
            field1.setAccessible(true); //设置私有属性的获取为true
            System.out.println("filed=="+field1.get(sysUser));
        }
    }

    @Test
    public void testPOI() throws IOException, InvalidFormatException {
        //POIFSFileSystem ps= new POIFSFileSystem(new FileInputStream(""));
        Workbook workbook = WorkbookFactory.create(new FileInputStream("d://cccc.xlsx"));
        //得到Excel工作表对象
        Sheet sheet = workbook.getSheetAt(0);
        //获取有效的行数
        int number = sheet.getLastRowNum();
        //遍历 Excle 的行
        for (int i = 0; i <= number; i++) {
            //得到Excel工作表的行
            Row row = sheet.getRow(i);
            //获取有效列数
            int num = row.getLastCellNum();
            //遍历 Excle 的列
            for (int j = 0; j < num; j++) {
                //方法一:
                //先设置单元格的属性为string类型
                //至于为什么这里这样写 是为了防止获取的数据以科学计数法的方式出现
                //正常来说应该要一个一个进行判断,判断单元格的属性,然后进行读取
                //row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                //再读取
                //String s= row.getCell(j).getStringCellValue();
                //System.out.println(s);

                //方法二:
                //获取单元格
                Cell cell = row.getCell(j);
                //调用下面的方法
                String value = getCellStringValue(cell);
                if(value.length() >2){
                    System.out.println(value);
                }
            }
        }
    }

    /**
     * Excle 中对单元格的操作
     * @param cell
     * @return
     */
    public String getCellStringValue(Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {//获取单个单元格的类型
            case Cell.CELL_TYPE_STRING://字符串类型
                cellValue = cell.getStringCellValue();
                if(cellValue.trim().equals("") || cellValue.trim().length()<=0)
                    cellValue=" ";
                break;
            case Cell.CELL_TYPE_NUMERIC://数值类型
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA://公式
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BLANK://空单元格
                cellValue=" ";
                break;
            case Cell.CELL_TYPE_BOOLEAN: //布尔
                break;
            case Cell.CELL_TYPE_ERROR:  //错误单元格
                break;
            default:
                break;
        }
        return cellValue;
    }
}
