import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PracticalTaskCreateReportFromJsonTest {

    @Test
    void createReportFromJsonTestNotExistingFile() {

        String filePath = "notExistFile";

        Exception exception = assertThrows(NotExistingFileException.class,
                () -> PracticalTask.createReportFromJson(filePath));

        String expectedMessage = "report definition file - not existing";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createADataFromJsonTestDifferentSignature() {

        String filePath = "testKeyDifferentSignature.json";

        Exception exception = assertThrows(MyJsonParseException.class,
                () -> PracticalTask.createReportFromJson(filePath));

        String expectedMessage = PracticalTask.DEFINITION_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createADataFromJsonTest() {

        String filePath = "testKey.json";

        ReportDefinition expectedResult =
                new ReportDefinition(10, false,10);

        ReportDefinition actualResult = PracticalTask.createReportFromJson(filePath);

        assertEquals(expectedResult, actualResult);

    }
}