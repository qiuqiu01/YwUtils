/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/01/13.
 * All Rights Reserved.
 */
package com.wuyou.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <pre>
 *     author: YanWen
 *     time  : 2020/01/13
 *     desc  : 关闭相关工具类
 * </pre>
 */
public final class CloseUtils {

    private CloseUtils() {
        throw new UnsupportedOperationException("U can't instantiate me...");
    }

    /**
     * 关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭 IO
     *
     * @param closeables closeables
     */
    public static void closeIOQuietly(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
