package creditcard;

import java.io.*;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonDataParser implements DataFileParser {
    private JSONArray parsedRecords;
    private JSONArray formattedRecords;

    public JsonDataParser() {
        parsedRecords = new JSONArray();
        formattedRecords = new JSONArray();
    }

    @Override
    public void retrieveRecords(String inputFile) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(inputFile)) {
            JSONArray jsonObject = (JSONArray) jsonParser.parse(reader);
            parsedRecords = jsonObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processCardRecords(String outputFile) {
        DataRecordsIterator recordIterator = new DataRecordsIteratorImpl(parsedRecords, parsedRecords.size());
        JSONObject output;

        while (!recordIterator.isDone()) {
            JSONObject record = recordIterator.currentObject();
            output = this.processSingleRecord(record);
            formattedRecords.add(output);
            recordIterator.next();
        }
        this.writeDataFile(outputFile);
    }

    public JSONObject processSingleRecord(JSONObject record) {
        String cardNumber;
        JSONObject row = new JSONObject();

        try {
            cardNumber = record.get("cardNumber").toString();
        } catch (Exception e) {
            row.put("cardNumber", "Invalid");
            row.put("cardType", "Error parsing card number");
            return row;
        }

        PaymentCardFactory cardFactory = new PaymentCardFactoryImpl();
        PaymentCard card = cardFactory.createPaymentCard(cardNumber);

        if (card == null) {
            String cardType = determineErrorTypeForCardNumber(cardNumber);
            row.put("cardNumber", cardNumber);
            row.put("cardType", cardType);
            return row;
        }

        String validationOutput = card.determineCardType(cardNumber);
        row.put("cardNumber", cardNumber);
        row.put("cardType", validationOutput.equals("Invalid") ? "Not Possible Card Number" : validationOutput);
        return row;
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

    @Override
    public void writeDataFile(String outputFile) {
        try (FileWriter file = new FileWriter(outputFile)) {
            file.write(formattedRecords.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
