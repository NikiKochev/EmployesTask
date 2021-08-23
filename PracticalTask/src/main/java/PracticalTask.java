import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PracticalTask {
    

    public static final String DATA_MESSAGE = """
            Make sure your file has the following structure :
             [\s
             {\s
             "name" : " some name"\s
             " totalSales" : Some Integer Number
             " salesPeriod" : Some Integer Number
             " experienceMultiplier" : Some Double Number
             }\s
             .\s
             .\s
             .\s
             .\s
             .\s
             ]""";

    public static final String DEFINITION_MESSAGE = """
            Make sure your file has the following structure :
             { \s
             " topPerformersThreshold" : Some Integer Number
             " useExprienceMultiplier" : true/false
             " periodLimit" : Some Integer Number
             }\s""";



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter an absolute path to the JSON data file");
        String dataFileAbsolutePath = sc.nextLine();
        System.out.println("enter an absolute path to the JSON report definition file ");
        String reportDefinitionFileAbsolutePath = sc.nextLine();


        try {
            List<Seller>  data = createADataFromJson(dataFileAbsolutePath);

            ReportDefinition reportDefinition = createReportFromJson(reportDefinitionFileAbsolutePath);

            createResponse(data,reportDefinition);

        } catch ( NotExistingFileException | JsonParseException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void createResponse(List<Seller> sellers, ReportDefinition reportDefinition) {
        Report report = new Report(sellers, reportDefinition);
        List<String[]> reportSCV = report.createCsvTemplate();

        File file = new File("test.csv");


        try(ICSVWriter writer = new CSVWriter(new FileWriter(file))){
            writer.writeAll(reportSCV);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ReportDefinition createReportFromJson(String reportDefinitionFileAbsolutePath) {

        try {

            String fileContent = new String(Files.readAllBytes(Paths.get(reportDefinitionFileAbsolutePath)));
            Gson gson = new Gson();


            return gson.fromJson(
                    fileContent,
                    ReportDefinition.class
            );
        }catch (IOException e){
            throw new NotExistingFileException("report definition file - not existing");
        }catch (JsonParseException e){
            throw  new MyJsonParseException(DEFINITION_MESSAGE);
        }
    }

    public static List<Seller> createADataFromJson(String dataFileAbsolutePath)  {

        try {

            String fileContent = new String(Files.readAllBytes(Paths.get(dataFileAbsolutePath)));
            Gson gson = new Gson();

            Seller[] s = gson.fromJson(
                    fileContent,
                    Seller[].class);

            return Arrays.asList(s);
        }catch (IOException e){
            throw new NotExistingFileException("data file - not existing");
        }catch (JsonParseException e){
            throw  new MyJsonParseException(DATA_MESSAGE);
        }
    }
}
