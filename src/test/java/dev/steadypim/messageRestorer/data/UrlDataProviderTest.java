package dev.steadypim.messageRestorer.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UrlDataProviderTest {

    @Test
    void testGetData() {
        //arrange
        String url = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json";

        try {
            //act
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            //assert
            assertEquals(200, statusCode);

            // Проверка ненулевых данных
            //act
            if (entity != null) {
                byte[] data = entity.getContent().readAllBytes();
                String responseData = new String(data, StandardCharsets.UTF_8);
                //assert
                assertNotNull(responseData);
            } else {
                fail("Entity is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception occurred");
        }
    }
}
