package dev.steadypim.messageRestorer.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDataSaverTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testSaveData() throws IOException {
        //arrange
        String json = "Blablabla i'm a some json";
        String fileName = "result.json";
        Path filePath = temporaryFolder.getRoot().toPath().resolve(fileName); // временный файл во временном каталоге
        FileDataSaver dataSaver = new FileDataSaver();

        //act
        dataSaver.saveData(json, filePath.toString());

        //assert
        assertTrue(Files.exists(filePath));
        String savedJson = Files.readString(filePath);
        assertEquals(json, savedJson);
    }
}
