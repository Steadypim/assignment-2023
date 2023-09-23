package dev.steadypim.messageRestorer.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.steadypim.messageRestorer.dtos.ReplacementDTO;

import java.io.File;
import java.io.IOException;

public class FileReplacementProvider implements ReplacementProvider {
    private final String fileName;

    public FileReplacementProvider(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ReplacementDTO[] getReplacements() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(fileName), ReplacementDTO[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ReplacementDTO[0];
    }
}
