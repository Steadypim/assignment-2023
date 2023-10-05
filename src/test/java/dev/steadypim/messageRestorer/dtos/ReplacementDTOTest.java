package dev.steadypim.messageRestorer.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplacementDTOTest {
    @Test
    void testGetters(){
        //arrange
        String replacement = "replacement";
        String source = "source";
        ReplacementDTO dto = new ReplacementDTO(replacement, source);

        //act
        String dtoReplacement = dto.replacement();
        String dtoSource = dto.source();

        //assert
        assertEquals(replacement, dtoReplacement);
        assertEquals(source, dtoSource);
    }

}
