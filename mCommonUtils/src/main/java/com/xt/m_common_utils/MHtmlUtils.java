package com.xt.m_common_utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by xuti on 2018/5/22.
 * jsoup-1.10.2.jar
 */

public class MHtmlUtils {
    public static void parseHtml() {
        Document doc = null;
        try {
            String url = "";
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
                /*for (Element file : doc.select("a[mp4sTask$=.mp4]")) {
                    LogUtils.d("getFileFromHtml------baseUri-------->" + file.baseUri());
                    LogUtils.d("getFileFromHtml-------mp4sTask------->" + file.attr("mp4sTask"));
                    LogUtils.d("getFileFromHtml--------text------>" + file.ownText());
                }*/
        if (doc == null) {
            return;
        }
        String cssQuery = "a[href$=.jpg]";
        cssQuery = "a[href$=.mp4]";
        Elements elements = doc.select(cssQuery);
        for (Element element : elements) {
//            element.ownText()
//            element.attr("href")
//            element.baseUri()
        }
    }
}
