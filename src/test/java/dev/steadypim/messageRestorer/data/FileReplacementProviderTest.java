package dev.steadypim.messageRestorer.data;

import dev.steadypim.messageRestorer.dtos.ReplacementDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileReplacementProviderTest {

    @Mock
    ReplacementDTO[] replacements;
    @Test
    void testGetReplacements(){
        //arrange
        String fileName = "replacement.json";
        FileReplacementProvider test = new FileReplacementProvider(fileName);

        //act
        replacements = test.getReplacements();

        //assert
        assertNotNull(replacements);
        //Возможно стоит проверять напрямую, сравнивая текст из replacement.json,
        //но если нужно будет проверять другие замены, то тест будет падать
    }
}
