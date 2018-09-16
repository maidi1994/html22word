package com.panda.html2word.utils;

import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Word转html
 */
public class Word2Html {

    /**
     * 私有化构造
     */
    private Word2Html (){}


    /**
     * 获取html从word文档中
     * @param fileInputStream
     * @param imageFilePath
     * @return
     * @throws Exception
     */
    public static String getHtmlFromDocx(InputStream fileInputStream, String imageFilePath) throws Exception {
        WordprocessingMLPackage wordMLPackage = Docx4J.load(fileInputStream);
        ContentType contentType =  new ContentType("charset=utf-8");
        wordMLPackage.setContentType(contentType);
        //html设置 图片的处理
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setImageDirPath(imageFilePath);
        htmlSettings.setImageTargetUri("images");
        htmlSettings.setWmlPackage(wordMLPackage);
        //word转htm
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);
        Docx4J.toHTML(htmlSettings, baos, Docx4J.FLAG_EXPORT_PREFER_XSL);
        //解决乱码问题
        byte[] lens = baos.toByteArray();
        String result = new String(lens,"utf-8");
        return result ;
    }


    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            final FileInputStream fileInputStream = new FileInputStream(new File("/Users/panda/Desktop/html2word/out_story.docx"));
            final String htmlFromDocx = getHtmlFromDocx(fileInputStream, "/Users/panda/Desktop/html2word/image");
            int a=1;
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
