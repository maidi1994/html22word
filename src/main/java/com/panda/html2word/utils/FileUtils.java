package com.panda.html2word.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * Comments: <strong>文件处理</strong> <br/>
 * <br/>
 * CopyRright (c)2008-xxxx: 珠海政采软件技术有限公司 <br/>
 * Project: srplatform <br/>
 * Module ID: 权限平台 <br/>
 * Create Date：2010-6-28 下午07:45:13 by wangcl <br/>
 * Modified Date: 2010-6-28 下午07:45:13 by wangcl
 *
 * @version: 0.5
 * @since 0.5
 */
public class FileUtils {

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return
     */
    public static String readFileByLines(String fileName) {
        String content = "";
        BufferedReader reader = null;
        if (StringUtils.isNotEmpty(fileName)) {
            File file = new File(fileName);
            try {
                InputStream is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String tempString;
                while ((tempString = reader.readLine()) != null)
                    content = content + tempString + "\n";
            } catch (IOException e) {
                content = e.getMessage();
            } finally {
                IOUtil.close(reader);
            }
        }
        return content;
    }


}
