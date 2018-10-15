package com.xt.m_common_utils;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

/**
 * Created by xuti on 2017/7/20.
 * jcifs-1.3.15.jar
 */

public class SambaUtils {
    private static final String TAG = SambaUtils.class.getSimpleName();
    public static List<SmbFile> images = new ArrayList<>();
    public static void downloadViaShare(final String ip, final String user, final String password, final String dir) {
        ALogUtil.d("Share(SMB) download!");
        String newDir = dir;
        String url = "";
        SmbFile[] fileList = null;
        FileOutputStream fos = null;
        SmbFileInputStream smbIs = null;
        byte[] buffer = new byte[8192];
        int readBytes = 0;
        int totalBytes = 0;
        if (!dir.endsWith("/"))  //directory must end with "/"
            newDir = dir + "/";
        url = "smb://" + user + ":" + password + "@" + ip + "/" + newDir;
        long startTime = System.currentTimeMillis();
        List<SmbFile> smbFiles = listFilesInDir(url, true);
//        try {
//            SmbFile shareDir = new SmbFile(url);
//            if (shareDir.isDirectory()) {
//
//                fileList = shareDir.listFiles();
//
//                for (int i = 0; i < fileList.length; i++) {
//                    SmbFile smbFile = fileList[i];
//                    if (smbFile.isFile()) {
////                        smbIs = new SmbFileInputStream(fileList[i]);
////
////                        fos = new FileOutputStream(new File(tempDir + File.separator + fileList[i].getName()));
////
////                        while ((readBytes = smbIs.read(buffer)) > 0) {
////                            fos.write(buffer, 0, readBytes);
////
////                            totalBytes += readBytes;
////
////                        }
////
////                        smbIs.close();
////
////                        fos.close();
////
////                        XTLogUtil.d(fileList[i].getName() + " is downloaded!");
////
////                        try {
////
////                            fileList[i].delete();
////
////                        } catch (SmbAuthException smbae) {
////
////                            XTLogUtil.d(fileList[i].getName() + " can not be deleted！");
////
////                        }
//
//                        XTLogUtil.d(TAG, "文件----------------》" + fileList[i].getName());
//
//                    } else if (fileList[i].isDirectory()) {
//                        SmbFile[] smbFiles = smbFile.listFiles();
//                        XTLogUtil.d(TAG, "目录----------------》" + fileList[i].getName());
//                    }
//
//                }
//
//                long endTime = System.currentTimeMillis();
//                long timeTaken = endTime - startTime;
//
//                XTLogUtil.d(totalBytes + "bytes downloaded in " + timeTaken / 1000 + " seconds at " + ((totalBytes / 1000) / Math.max(1, (timeTaken / 1000))) + "Kb/sec");
//
//            }
//
//        } catch (MalformedURLException urle) {
//
//            XTLogUtil.d("Incorrect URL format!");
//
//        } catch (SmbException smbe) {
//
//            smbe.printStackTrace();
//
//            XTLogUtil.d(this.getClass().getName() + "||" + smbe.getMessage());
//
//        }
////        catch (IOException ioe) {
////
////            ioe.printStackTrace();
////
////            XTLogUtil.d(this.getClass().getName() + "||" + ioe.getMessage());
////
////        }
//        finally {
//            try {
//
//                smbIs.close();
//
//                fos.close();
//
//            } catch (Exception smbe) {
//
//                XTLogUtil.d(this.getClass().getName() + "||" + smbe.getMessage());
//
//            }
//        }

    }


    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<SmbFile> listFilesInDir(String dirPath, boolean isRecursive) {
        return listFilesInDir(getFileByPath(dirPath), isRecursive);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<SmbFile> listFilesInDir(SmbFile dir, boolean isRecursive) {
        if (!isDir(dir)){
            return null;
        }
        if (isRecursive) return listFilesInDir(dir);
        List<SmbFile> list = new ArrayList<>();
        SmbFile[] files = new SmbFile[0];
        try {
            files = dir.listFiles();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        if (files != null && files.length != 0) {
            Collections.addAll(list, files);
        }
        return list;
    }
    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dir 目录
     * @return 文件链表
     */
    public static List<SmbFile> listFilesInDir(SmbFile dir) {
        if (!isDir(dir)) return null;
        List<SmbFile> list = new ArrayList<>();
        SmbFile[] files = new SmbFile[0];
        try {
            files = dir.listFiles();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        if (files != null && files.length != 0) {
            for (SmbFile file : files) {
                list.add(file);
                try {
                    if (file.isDirectory()) {
                        ALogUtil.d(TAG,"目录-------------------》"+file.getName());
                        List<SmbFile> fileList = listFilesInDir(file);
                        if (fileList != null) {
                            list.addAll(fileList);
                        }
                    }else if(file.isFile()){
                        images.add(file);
                        ALogUtil.d(TAG,"文件-------------------》"+file.getPath());
                    }
                } catch (SmbException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(SmbFile file) {
        try {
            return isFileExists(file) && file.isDirectory();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(SmbFile file) {
        try {
            return file != null && file.exists();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static SmbFile getFileByPath(String filePath) {
        try {
            return isSpace(filePath) ? null : new SmbFile(filePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 判断字符串是否为null或全为空白字符
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空白字符<br> {@code false}: 不为null且不全空白字符
     */
    public static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
