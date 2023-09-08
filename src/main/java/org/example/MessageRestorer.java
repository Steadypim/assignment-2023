package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRestorer {
    public static void main(String[] args) {
        try {
            // Чтение данных из файлов replacement.json
            List<Replacement> replacements = readReplacementsFromFile("replacement.json");

            // Получение данных из URL
            String dataUrl = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json";
            String jsonData = fetchDataFromUrl(dataUrl);


            // Чтение данных JSON
            Gson gson = new Gson();
            Type messageListType = new TypeToken<List<String>>() {}.getType();
            List<String> messages = gson.fromJson(jsonData, messageListType);

            // Создание отображения для хранения замен
            Map<String, String> replacementMap = new HashMap<>();

            // Обработка данных из файла replacement.json
            for (Replacement replacement : replacements) {
                    // Добавление значения замены в отображение
                    replacementMap.put(replacement.getReplacement(), replacement.getSource());
            }


            // Восстановление исходных сообщений
            // Удаление строк из data json
            List<String> updatedMessages = new ArrayList<>();
            for (String message : messages) {
                boolean shouldRemove = false;
                for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
                    String replacement = entry.getKey();
                    String replacementValue = entry.getValue();
                    if (replacementValue == null && message.equals(replacement)) {
                        shouldRemove = true;
                        message = message.replace(replacement, ""); // Удаление строки из сообщения
                        break;
                    } else if (replacementValue != null && message.equals(replacement)) {
                        message = message.replace(replacement, replacementValue); // Применение замен в сообщение
                    }
                }
                if (!shouldRemove) {
                    updatedMessages.add(message);
                }
            }

            // Запись восстановленных сообщений в файл result.json
            writeMessagesToFile(updatedMessages, "result.json");

            System.out.println("Сообщения успешно восстановлены и записаны в файл result.json");
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }

    private static List<Replacement> readReplacementsFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(filePath);
        Type replacementListType = new TypeToken<List<Replacement>>() {}.getType();
        List<Replacement> replacements = gson.fromJson(fileReader, replacementListType);
        fileReader.close();

        // Создание отображения для хранения последних замен
        Map<String, Replacement> uniqueReplacements  = new HashMap<>();
        for (int i = replacements.size() - 1; i >= 0; i--) {
            Replacement replacement = replacements.get(i);
            String replacementValue = replacement.getReplacement();

            // Добавляем объект в uniqueReplacements только если его значение replacement
            // еще не присутствует в отображении
            uniqueReplacements.putIfAbsent(replacementValue, replacement);
        }

        // Создаем новый список, содержащий только уникальные объекты replacement
        return new ArrayList<>(uniqueReplacements.values());
    }


    private static void writeMessagesToFile(List<String> messages, String filePath) throws IOException {
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(messages, fileWriter);
        fileWriter.close();
    }

    private static String fetchDataFromUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}