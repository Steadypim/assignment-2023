package dev.steadypim.messageRestorer;


import dev.steadypim.messageRestorer.config.ConfigLoader;
import dev.steadypim.messageRestorer.data.DataProvider;
import dev.steadypim.messageRestorer.data.FileReplacementProvider;
import dev.steadypim.messageRestorer.data.ReplacementProvider;
import dev.steadypim.messageRestorer.data.UrlDataProvider;
import dev.steadypim.messageRestorer.dtos.ReplacementDTO;
import dev.steadypim.messageRestorer.io.DataSaver;
import dev.steadypim.messageRestorer.io.FileDataSaver;
import dev.steadypim.messageRestorer.utils.StringReplacer;

public class MessageRestorer {
    public void restoreMessages(){
        // Создание провайдера данных
        ConfigLoader configLoader = new ConfigLoader();
        String dataUrl = configLoader.getDataUrl();
        DataProvider dataProvider = new UrlDataProvider(dataUrl);
        String data = dataProvider.getData();

        // Создание провайдера замен
        ReplacementProvider replacementProvider = new FileReplacementProvider("replacement.json");
        ReplacementDTO[] replacements = replacementProvider.getReplacements();

        // Создание объекта для замены строк
        StringReplacer stringReplacer = new StringReplacer();
        String modifiedData = stringReplacer.replaceStrings(data, replacements);

        // Создание объекта для сохранения данных
        DataSaver dataSaver = new FileDataSaver();
        dataSaver.saveData(modifiedData, "result.json");
    }
}
