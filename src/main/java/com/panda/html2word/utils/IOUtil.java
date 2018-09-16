package com.panda.html2word.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 方便关闭io流
 */
public class IOUtil {
    public static void close(Closeable... closeableList) {

        for (Closeable closeable : closeableList) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
