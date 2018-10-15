package com.xt.m_common_utils;

import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对Log的封装
 */
public final class ALogUtil {
    private static final Boolean IS_SHOW = true;
    private static final String TAG = "LOG";

    private ALogUtil() {
    }

    /**
     * 默认tag 为LOG
     *
     * @param text
     */
    public static void i(String text) {
        if (IS_SHOW) {
            Log.i(TAG, text);
            print2File(text);
        }
    }

    /**
     * 默认tag 为LOG
     *
     * @param text
     */
    public static void e(String text) {
        if (IS_SHOW) {
            Log.e(TAG, text);
            print2File(text);
        }
    }

    /**
     * 默认tag 为LOG
     *
     * @param text
     */
    public static void d(String text) {
        if (IS_SHOW) {
            Log.d(TAG, text);
            print2File(text);
        }
    }

    /**
     * 默认tag 为LOG
     *
     * @param text
     */
    public static void v(String text) {
        if (IS_SHOW) {
            Log.v(TAG, text);
            print2File(text);
        }
    }

    /**
     * 自定义tag
     *
     * @param tag
     * @param text
     */
    public static void i(String tag, String text) {
        if (IS_SHOW) {
            Log.i(tag, text);
            print2File(text);
        }
    }

    /**
     * 自定义tag
     *
     * @param tag
     * @param text
     */
    public static void e(String tag, String text) {
        if (IS_SHOW) {
            Log.e(tag, text);
            print2File(text);
        }
    }

    /**
     * 自定义tag
     *
     * @param tag
     * @param text
     */
    public static void w(String tag, String text) {
        if (IS_SHOW) {
            Log.w(tag, text);
            print2File(text);
        }
    }

    /**
     * 自定义tag
     *
     * @param tag
     * @param text
     */
    public static void d(String tag, String text) {
        if (IS_SHOW) {
            Log.d(tag, text);
            print2File(text);
        }
    }

    /**
     * 自定义tag
     *
     * @param tag
     * @param text
     */
    public static void v(String tag, String text) {
        if (IS_SHOW) {
            Log.v(tag, text);
            print2File(text);
        }
    }

    public static void a(String tag, String s) {
        Log.println(Log.ASSERT, tag, s);
    }


    private static ExecutorService executor;

    public static final String logFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "m_log" + File.separator;

    private static void doPrint2File(final String msg) {
        /*Date now = new Date(System.currentTimeMillis());
        String format = FORMAT.format(now);
        final String content = format + "-------------" + msg;*/
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(new Date());
                String filePath = logFileDir + "motorcycle_log_" + format + ".txt";
                if (!FileUtils.isFileExists(filePath)) {
                    FileUtils.deleteFilesInDir(logFileDir);

                }
                if (FileUtils.createOrExistsFile(filePath)) {
                    FileCacheUtils.write2File(msg, filePath);
                }
            }
        });
    }

    private static void print2File(String text) {
        doPrint2File(text);
    }

    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault());

    /*private static void print2File(final int type, final String tag, final String msg) {
        Date now = new Date(System.currentTimeMillis());
        String format = FORMAT.format(now);
        String date = format.substring(0, 5);
        String time = format.substring(6);
        final String fullPath = (dir == null ? defaultDir : dir) + date + ".txt";
        if (!createOrExistsFile(fullPath)) {
            Log.e(tag, "log to " + fullPath + " failed!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(time)
                .append(T[type - V])
                .append("/")
                .append(tag)
                .append(msg)
                .append(LINE_SEP);
        final String content = sb.toString();
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(fullPath, true));
                    bw.write(content);
                    Log.d(tag, "log to " + fullPath + " success!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(tag, "log to " + fullPath + " failed!");
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }*/
}
