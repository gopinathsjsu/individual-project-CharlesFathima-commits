package creditcard;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvDataParser implements DataFileParser {
    private List<String> parsedRecords;
    private List<String> formattedRecords;

    public CsvDataParser() {
        parsedRecords = new ArrayList<>();
        formattedRecords = new ArrayList<>();
    }

    @Override
    public void retrieveRecords(String inputFile) {
        try {
            parsedRecords.addAll(Files.readAllLines(Paths.get(inputFile)));
            if (parsedRecords.get(0).toLowerCase().startsWith("card")) {
                parsedRecords.remove(0); // Remove header if exists
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV file.");
            e.printStackTrace();
        }
    }

    @Override
    public void processCardRecords(String outputFile) {
        try {
            DataRecordsIterator recordIterator = new DataRecordsIteratorImpl(parsedRecords);

            formattedRecords.add("CardNumber,CardType");

            String currentRecord;
            String processedOutput;

            while (!recordIterator.isDone()) {
                currentRecord = recordIterator.currentString();

                if (currentRecord.isEmpty()) {
                    formattedRecords.add("null,Invalid,LineIsBlank");
                    recordIterator.next();
                    continue;
                }

                processedOutput = this.processSingleRecord(currentRecord);

                formattedRecords.add(processedOutput);
                recordIterator.next();
            }

            this.writeDataFile(outputFile);
        } catch (Exception e) {
            System.out.println("Error while processing records: " + e);
        }
    }

    @Override
    public void writeDataFile(String outputFile) {
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            for (String record : formattedRecords) {
                fileWriter.write(record + System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Error while writing to file: " + e);
        }
    }

    public String processSingleRecord(String record) {
        String cardNumber = record.split(",")[0];
        String cardType = "Invalid";
        PaymentCardFactory cardFactory = new PaymentCardFactoryImpl();
        PaymentCard card = cardFactory.createPaymentCard(cardNumber);

        if (card == null) {
            cardType = determineErrorTypeForCardNumber(cardNumber);
            return cardNumber + "," + cardType;
        }

        String validationOutput = card.determineCardType(cardNumber);
        cardType = validationOutput.equals("Invalid") ? "Not Possible Card Number" : validationOutput;

        return cardNumber + "," + cardType;
    }

    private String determineErrorTypeForCardNumber(String cardNumber) {
        if (cardNumber.isEmpty()) {
            return "Invalid: empty/null card number";
        } else if (cardNumber.length() >= 17) {
            return "Invalid: more than 19 digits";
        } else if (!cardNumber.matches("[0-9]+")) {
            return "Invalid: non numeric characters";
        } else {
            return "Invalid: not a possible card number";
        }
    }
}
