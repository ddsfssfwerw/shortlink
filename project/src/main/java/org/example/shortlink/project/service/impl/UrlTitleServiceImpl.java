package org.example.shortlink.project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.shortlink.project.service.UrlTitleService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author LLY
 * @version v1.0.0
 * @date 2024/5/14 下午4:26
 * @className UrlTitleServiceImpl
 * @copyright LLY
 */
@Service
@Slf4j
@RequiredArgsConstructor//注入bean
public class UrlTitleServiceImpl implements UrlTitleService {
    @SneakyThrows
    @Override
    public String getTitleByUrl(String url) {
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Document document = Jsoup.connect(url).get();
            return document.title();
        }
        return "Error while getting title";
    }
}
