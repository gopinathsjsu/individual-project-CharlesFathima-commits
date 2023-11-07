package creditcard;

import java.io.File;

public class FileProcessingContext {
    private DataFileParser fileParser;

    public void parse(String inputFilename, String outputFilename) {
        String inputFileExtension = inputFilename.substring(inputFilename.lastIndexOf(".") + 1).toLowerCase();
        String outputFileExtension = outputFilename.substring(outputFilename.lastIndexOf(".") + 1).toLowerCase();

        if (!inputFileExtension.equals(outputFileExtension)) {
            System.out.println("Please enter the same type of files.");
            return;
        }
        try {
            if (inputFilename.endsWith(".csv")) {
                fileParser = new CsvDataParser();
            } else if (inputFilename.endsWith(".json")) {
                fileParser = new JsonDataParser();
            } else if (inputFilename.endsWith(".xml")) {
                fileParser = new XmlDataParser();
            } else {
                System.out.print(inputFileExtension + " is not a supported file type. ");
                System.out.println("Please Enter a Valid file type.");
                return;
            }

            fileParser.retrieveRecords(inputFilename);

            File file = new File(outputFilename);
            file.createNewFile();

            fileParser.processCardRecords(outputFilename);
        } catch (Exception e) {
            System.out.print("Error while Parsing File: " + e);
        }
    }
}
