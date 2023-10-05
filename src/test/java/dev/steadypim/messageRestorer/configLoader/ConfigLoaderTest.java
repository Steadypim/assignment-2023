package dev.steadypim.messageRestorer.configLoader;

import dev.steadypim.messageRestorer.config.ConfigLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigLoaderTest {
    @Test
    void testGetDataUrl(){
        //arrange
        String exceptedUrl = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json";
        ConfigLoader configLoader = new ConfigLoader();

        //act
        String url = configLoader.getDataUrl();

        //assert
        assertEquals(exceptedUrl, url);
    }
}
