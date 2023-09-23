package dev.steadypim.messageRestorer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.steadypim.messageRestorer.dtos.ReplacementDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для замены строк в данных
 */
public class StringReplacer {
    public String replaceStrings(String dataJson, ReplacementDTO[] replacementDTOS) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> data = objectMapper.readValue(dataJson, List.class);
            Map<String, String> replacementMap = new HashMap<>();

            // Создание отображения для замены строк
            for (ReplacementDTO replacementDTO : replacementDTOS) {
                replacementMap.put(replacementDTO.getReplacement(), replacementDTO.getSource());
            }

            // Замена строк в данных
            for (int i = 0; i < data.size(); i++) {
                String originalString = data.get(i);
                if (replacementMap.containsKey(originalString)) {
                    String replacementString = replacementMap.get(originalString);
                    if (replacementString == null) {
                        // Если replacementString равно null, удаляем
                        data.remove(i);
                    } else {
                        data.set(i, replacementString);
                    }
                }
            }

            return objectMapper.writeValueAsString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}