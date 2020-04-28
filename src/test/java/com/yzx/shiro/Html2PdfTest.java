package com.yzx.shiro;

import com.itextpdf.text.pdf.BaseFont;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Html2PdfTest {
    @Test
    public void testHtml2Pdf() throws Exception {
        //指定PDF的存放路径
        String outputFile = "d:/test.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        //指定字体。为了支持中文字体
        fontResolver.addFont("D:/workFile/own/shiro/src/main/resources/static/statics/fonts/arial unicode ms.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        StringBuffer html = new StringBuffer();

        html.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\"></meta>\n" +
                "    <title>Issue Payment Receipt</title>\n" +
                "    <style type=\"text/css\">\n" +
                "    body {\n" +
                "        font-family: Arial Unicode MS;\n" +
                "    }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"https://www.baidu.com/img/pc_2e4ef5c71eaa9e3a3ed7fa3a388ec733.png\" style=\"width:80px;height:80px;\"></img>\n" +
                "    <br/>\n" +
                "    <br/> <div style=\"color:red;text-align: center;\"><font style=\"color:red;text-align: center;\">建设银行</font></div>\n" +
                "    <br/> 12345678901\n" +
                "    <br/> 1000RMB\n" +
                "    <br/> 姓名:李四\n" +
                "    <br/> 單號:123456\n" +
                "    <br/>\n" +
                "</body>\n" +
                "</html>");
        System.out.println(html.toString());
        renderer.setDocumentFromString(html.toString());
        // 解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:/" + "D:/workFile/own/shiro/src/main/resources/static/statics/images/login");
        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
        renderer = null;
        os.close();
    }
}
