package com.lee.kevin.httpnetproject.core.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Lee
 * on 2017/1/5.
 */

public final class IO {

    public static void close(Closeable... closeables) {

        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }

                cb.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
