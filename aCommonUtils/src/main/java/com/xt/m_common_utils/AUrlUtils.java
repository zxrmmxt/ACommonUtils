package com.xt.m_common_utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xuti on 2017/9/6.
 */

public class AUrlUtils {
    public static long getLastModified(String urlStr) {
        try {
            URL url = new URL(urlStr);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return connection.getLastModified();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
