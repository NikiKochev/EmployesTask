import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ReportTest {

    public static final String[] NAMES = {"James Smith","Robert Johnson","John Williams",
            "Michael Brown","William Jones","Mary Garcia",
            "Patricia Miller","Jennifer Davis",
            "Linda Rodriguez","Elizabeth Martinez"};

    @Test
    void createCsvTemplate() {
        Seller seller = new Seller("John Smith", 250, 10,0.5);
        Seller seller1 = new Seller("David Prowless", 250,10,0.5);
        ArrayList <Seller> sellers = new ArrayList<>();
        sellers.add(seller);
        sellers.add(seller1);

        ReportDefinition reportDefinition =
                new ReportDefinition(10,true,10);

        List<String[]> expectedResponse= new ArrayList<>();
        String[] s1= {"John Smith","12.5"};
        String[] s2 = {"David Prowless", "12.5"};
        expectedResponse.add(s2);
        expectedResponse.add(s1);

        Report report = new Report(sellers, reportDefinition);
        List<String[]> actualResponse = report.createCsvTemplate();

        assertTrue(sameResponse(actualResponse,expectedResponse));

    }


    @Test
    void createACsvTemplate100Sellers(){
        ArrayList<Seller> sellers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            sellers.add(new Seller(NAMES[new Random().nextInt(NAMES.length)],
                    new Random().nextInt(1000),
                    new Random().nextInt(100) +1,
                    new Random().nextDouble()));

        }
        ReportDefinition reportDefinition =
                new ReportDefinition(10,true,10);

        List<String[]> expectedResult = sellers.stream()
                .map(s->{
                    String name = s.getName();
                    Double score = reportDefinition.isUseExprienceMultiplier() ?
                         s.getTotalSales()/ s.getSalesPeriod()* s.getExperienceMultiplier() :
                            s.getTotalSales()/ s.getSalesPeriod();

                    String[] response = new String[2];
                    response[0] =name;
                    response[1] = score.toString();
                    return response;
                } ).collect(Collectors.toList());

        Report report = new Report(sellers, reportDefinition);

        List<String[]> actualResult = report.createCsvTemplate();

        assertTrue(sameResponse(actualResult,expectedResult));

    }



    private boolean sameResponse(List<String[]> l1, List<String[]> l2){
        boolean isTheSame = false;
        if(l1 == l2){
            return true;
        }
        for (String[] st: l1){
            for(String[] st1: l2){
                isTheSame = false;
                for (int i = 0; i < st.length; i++) {
                    if(st[i].equalsIgnoreCase(st1[i])){
                        isTheSame = true;
                        break;
                    }
                }
                if(isTheSame){
                    break;
                }
            }
            if (!isTheSame){
                return false;
            }
        }
        return true;
    }

}