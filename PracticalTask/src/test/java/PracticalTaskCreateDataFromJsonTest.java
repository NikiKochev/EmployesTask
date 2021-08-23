import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PracticalTaskCreateDataFromJsonTest {

    @Test
    void createADataFromJsonTestNotExistingFile() {

        String filePath = "notExistFile";

        Exception exception = assertThrows(NotExistingFileException.class,
                () -> PracticalTask.createADataFromJson(filePath));

        String expectedMessage = "data file - not existing";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createADataFromJsonTestDifferentSignature() {

        String filePath = "testDifferentSignature.json";

        Exception exception = assertThrows(MyJsonParseException.class,
                () -> PracticalTask.createADataFromJson(filePath));

        String expectedMessage = PracticalTask.DATA_MESSAGE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createADataFromJsonTest() {

        String filePath = "test.json";

        Seller s  = new Seller("John Smith", 250, 10, 0.5);
        Seller s1 = new Seller("David Prowless", 230, 10,0.5);

        List<Seller> expectedResult = new ArrayList<>();
        expectedResult.add(s);
        expectedResult.add(s1);

        List<Seller> actualResult = PracticalTask.createADataFromJson(filePath);

        assertTrue(sameResponse(actualResult, expectedResult));

    }

    private boolean sameResponse(List<Seller> l1, List<Seller> l2){
        boolean isTheSame ;
        if(l1 == l2){
            return true;
        }
        for(Seller s : l1 ){
            isTheSame = false;
            for (Seller s1 : l2){
                if(s.equals(s1)){
                    isTheSame = true;
                    break;
                }
            }
            if(!isTheSame){
                return false;
            }
        }
        return true;
    }

}