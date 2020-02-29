package com.yzx.shiro;

import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws Exception {
        String urlStr = "https://www.baidu.com"; // 网址
        try { //创建一个url对象来指向要采集信息的网址
            StringBuffer html = new StringBuffer();
            URL url = new URL(urlStr); //将读取到的字节转化为字符
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream a = url.openStream();
            InputStreamReader inStrRead = new InputStreamReader(a, "utf-8"); //读取InputStreamReader转化成的字符
            BufferedReader bufRead = new BufferedReader(inStrRead); //读到的内容不为空
            String line;
            while ((line = bufRead.readLine()) != null) {
                html.append(line);
            }
            System.out.println(html);
            bufRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
