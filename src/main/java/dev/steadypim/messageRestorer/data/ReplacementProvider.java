package dev.steadypim.messageRestorer.data;

import dev.steadypim.messageRestorer.dtos.ReplacementDTO;

/**
 * Интерфейс для загрузки замен из файла
 */
public interface ReplacementProvider {
    ReplacementDTO[] getReplacements();
}
