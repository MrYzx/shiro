package com.yzx.shiro.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFUtil {
    private static String chinesePath="c://windows//fonts//simsun.ttc,1";//--测试使用
   // private static String chinesePath="/usr/share/fonts/zh_CN/simsun.ttc,0";//--正式使用

    //---创建一个pdf文件
    public File crateFile(String path,String fileNmae) {
        File pdfFile = null;
        try {
            System.out.print(path);
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            pdfFile = new File(dirFile, fileNmae);
            pdfFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfFile;
    }

    //---创建文档标题
    public  void createDocumentTitle(Document document, String title) throws Exception{
        BaseFont bfChinese =BaseFont.createFont(chinesePath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese,16, Font.BOLD);
        Paragraph paragraph=new Paragraph(title,fontChinese);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        PdfPTable table = new PdfPTable(12);

        PdfPCell pdfCelltitle = new PdfPCell(new Paragraph("",getPdfChineseTitle())); //表格的单元格
        pdfCelltitle.setFixedHeight(20);
        pdfCelltitle.setBorder(0);
        pdfCelltitle.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfCelltitle.setColspan(12*133);
        table.addCell(pdfCelltitle);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(table);
    }

    //创建table标题
    public Font getPdfChineseTitle() throws Exception {
        BaseFont bfChinese =BaseFont.createFont(chinesePath, //注意这里有一个,1
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese,7, Font.BOLD);
        return fontChinese;
    }

    //---设置内容的字体大小
    public Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese =BaseFont.createFont(chinesePath, //注意这里有一个,1
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 8, Font.NORMAL);
        return fontChinese;
    }

    //---设置内容的字体大小
    public Font getPdfChineseFont(int fontSize,int fontWeghit) throws Exception {
        BaseFont bfChinese =BaseFont.createFont(chinesePath, //注意这里有一个,1
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, fontSize, fontWeghit);
        return fontChinese;
    }

    public PdfPCell ceateCell(String value) throws Exception{
        PdfPCell cell=new PdfPCell();
        cell.setPadding(5);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        Paragraph p = new Paragraph(value, getPdfChineseFont());
        cell.setPhrase(p);
        return cell;
    }

    public PdfPCell ceateCell(String value,int rowSpan,int colSpan) throws Exception{
        PdfPCell cell=new PdfPCell();
        cell.setPadding(5);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        Paragraph p = new Paragraph(value, getPdfChineseFont());
        cell.setPhrase(p);
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        return cell;
    }

    public PdfPCell ceateCell(String value,int rowSpan,int colSpan,int horizontalAlignment,int verticalAlignment) throws Exception{
        PdfPCell cell=new PdfPCell();
        cell.setPadding(5);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(horizontalAlignment); //水平排列方式
        cell.setVerticalAlignment(verticalAlignment);//垂直排列方式
        Paragraph p = new Paragraph(value, getPdfChineseFont());
        cell.setPhrase(p);
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        return cell;
    }

    public PdfPCell ceateCell(String value,int padding,int rowSpan,int colSpan,int horizontalAlignment,int verticalAlignment) throws Exception{
        PdfPCell cell=new PdfPCell();
        cell.setPadding(padding);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(horizontalAlignment); //水平排列方式
        cell.setVerticalAlignment(verticalAlignment);//垂直排列方式
        Paragraph p = new Paragraph(value, getPdfChineseFont());
        cell.setPhrase(p);
        cell.setRowspan(rowSpan);
        cell.setColspan(colSpan);
        return cell;
    }

    public PdfPCell setTitleCell(String value,int padding,int colWidth) throws Exception{
        PdfPCell cell = new PdfPCell(new Paragraph(value,getPdfChineseTitle())); //表格的单元格
        cell.setPadding(padding);
        cell.setUseAscender(true);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cell.setColspan(colWidth);
        return cell;
    }

    //---添加下划线
    public void getDashLine(Document document){
        try {
            Paragraph p2=new Paragraph();
            DottedLineSeparator dottedLineSeparator=new DottedLineSeparator();
            p2.setSpacingBefore(20);
            dottedLineSeparator.setPercentage(82);
            p2.add(new Chunk(dottedLineSeparator));
            document.add(p2);
            String[]strNows= new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
            Integer year=Integer.parseInt(strNows[0]);
            Integer month=Integer.parseInt(strNows[1]);
            Integer day=Integer.parseInt(strNows[2]);
            Paragraph p3=new Paragraph("打印时间："+year+"年"+month+"月"+day+"日",getPdfChineseFont());
            p3.setIndentationRight(50);
            p3.setAlignment(Element.ALIGN_RIGHT);
            document.add(p3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
