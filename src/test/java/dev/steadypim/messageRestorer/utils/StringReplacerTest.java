package dev.steadypim.messageRestorer.utils;

import dev.steadypim.messageRestorer.dtos.ReplacementDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
class StringReplacerTest {

    @Mock
    ReplacementDTO[] replacementDTOS = new ReplacementDTO[]{
            new ReplacementDTO("world", "universe"),
            new ReplacementDTO("Goodbye", null)
    };

    @Test
    public void testReplaceStrings(){
        //arrange
        String dataJson = "[\"Hello, world!\", \"Goodbye, world!\"]";
        String expectedOutput = "[\"Hello, universe!\"]";
        StringReplacer stringReplacer = new StringReplacer();

        //act
        String actualOutput = stringReplacer.replaceStrings(dataJson, replacementDTOS);

        //arrange
        assertEquals(expectedOutput, actualOutput);

    }
}
