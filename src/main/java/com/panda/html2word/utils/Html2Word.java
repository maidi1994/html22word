package com.panda.html2word.utils;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;

import java.io.File;
import java.io.IOException;

/**
 * Html转word方法
 */
public class Html2Word {

    /**
     * 私有化构造
     */
    private Html2Word() {
    }

    /**
     * html字符串转word
     */
    public static File convert(String content) {
        try {
            //创建临时文件
            File file = File.createTempFile("temp", "docx");
            //html转word
            return convert(content, file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * html转word
     */
    public static File convert(String content, File file) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
            wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
            ndp.unmarshalDefaultNumbering();
            XHTMLImporterImpl xmlImporter = new XHTMLImporterImpl(wordMLPackage);
            xmlImporter.setHyperlinkStyle("Hyperlink");
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xmlImporter.convert(handleHtml(content), null));
            //将html内容转换成word文件
            wordMLPackage.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * html内容过滤
     *
     * @param content
     * @return
     */
    private static String handleHtml(String content) {
        //替换
        String html = content
                .replaceAll("(<>)", "</p>")
                .replaceAll("(</<.*?>)", "")
                .replaceAll("(<!--.*?>)", "")
                .replaceAll("(class=\".*?\")", "")
                .replaceAll(":=", "=")
                .replaceAll("([a-zA-Z]*\"=\"[a-zA-Z]*\")", "")
                .replaceAll("(<\\?xml:.*?>)", "")
                .replaceAll("(<\\/\\?xml:.*?>)", "")
                .replaceAll("(<!--\\?xml:.*?>)", "")
                .replaceAll("(<br.*?>)", "")
                .replaceAll("<o:p>", "")
                .replaceAll("</o:p>", "")
                .replaceAll("(&[a-zA-Z]*?;)", "")
                .replaceAll("(&[a-zA-Z]*)", "");
        //添加根标签
        if (!html.contains("<html")) {
            html = "<html>" + html + "</html>";
        }
        return html;
    }

}
