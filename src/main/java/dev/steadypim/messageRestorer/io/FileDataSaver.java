package dev.steadypim.messageRestorer.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSaver implements DataSaver {
    @Override
    public void saveData(String json, String fileName) {
        try {
            Files.writeString(Paths.get(fileName), json);
            System.out.println("Восстановленные данные сохранены в файл result.json\n༼╯°□°༼▀̿Ĺ̯̿▀̿༽｡◕‿‿◕｡͆༽ﾉ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
