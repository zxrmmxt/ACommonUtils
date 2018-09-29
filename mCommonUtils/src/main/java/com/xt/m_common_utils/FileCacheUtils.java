package com.xt.m_common_utils;

import com.blankj.utilcode.util.TimeUtils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by admin on 2018/3/23.
 */

public class FileCacheUtils {
    public static void write2File(String dataStr, String path){
        FileWriter fileWriter = null;
        try {
            /*File file = new File(path);
            if(file.exists()){
                if(file.length() > MAX_FILE_SIZE){//控制文件大小
                    file.delete();
                }
            }*/
            fileWriter = new FileWriter(path,true);
            fileWriter.write(TimeUtils.getNowString() + "  " + dataStr);
            fileWriter.flush();
            fileWriter.write("\r\n");
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                    fileWriter = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
