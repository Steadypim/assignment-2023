package dev.steadypim.messageRestorer.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Реализация загрузки данных по URL
 */
public class UrlDataProvider implements DataProvider {
    private final String url;

    public UrlDataProvider(String url) {
        this.url = url;
    }

    @Override
    public String getData() {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                byte[] data = entity.getContent().readAllBytes();
                return new String(data, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
