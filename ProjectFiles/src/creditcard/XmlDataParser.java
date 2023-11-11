package creditcard;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlDataParser implements DataFileParser {
    private NodeList parsedRecords;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document outputDocument;

    public XmlDataParser() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    @Override
    public void retrieveRecords(String inputFile){
        try {
            File file = new File(inputFile);
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            parsedRecords = doc.getElementsByTagName("CARD");
        } catch (Exception e){
            System.out.println("Error while fetching XML records: " + e);
        }
    }

    @Override
    public void processCardRecords(String outputFile) {
        try {
            DataRecordsIterator iterator = new DataRecordsIteratorImpl(parsedRecords);
            outputDocument = dBuilder.newDocument();

            Element root = outputDocument.createElement("CARDS");
            outputDocument.appendChild(root);

            while (!iterator.isDone()) {
                Element cardElement = (Element) iterator.currentNode();
                String cardNumber = getCardNumberFromElement(cardElement);
                PaymentCardFactory cardFactory = new PaymentCardFactoryImpl();
                PaymentCard card = cardFactory.createPaymentCard(cardNumber);

                String cardType = determineCardType(card, cardNumber);
                appendCardDetailsToDocument(root, cardNumber, cardType);
                iterator.next();
            }

            this.writeDataFile(outputFile);
        } catch (Exception e) {
            System.out.println("Error while processing XML records: " + e);
        }
    }

    private String getCardNumberFromElement(Element element) {
        try {
            return element.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
        } catch (Exception e) {
            return "null";
        }
    }

    private String determineCardType(PaymentCard card, String cardNumber) {
        if (card == null) {
            return determineErrorTypeForCardNumber(cardNumber);
        } else {
            String validationOutput = card.determineCardType(cardNumber);
            return validationOutput.equals("Invalid") ? "Not Possible Card Number" : validationOutput;
        }
    }

    private void appendCardDetailsToDocument(Element root, String cardNumber, String cardType) {
        Element cardElement = outputDocument.createElement("CARD");
        root.appendChild(cardElement);

        Element cardNumElement = outputDocument.createElement("CARD_NUMBER");
        cardNumElement.appendChild(outputDocument.createTextNode(cardNumber));
        cardElement.appendChild(cardNumElement);

        Element cardTypeElement = outputDocument.createElement("CARD_TYPE");
        cardTypeElement.appendChild(outputDocument.createTextNode(cardType));
        cardElement.appendChild(cardTypeElement);
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
    public void writeDataFile(String outputFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        outputDocument.setXmlStandalone(true);
        DOMSource domSource = new DOMSource(outputDocument);
        StreamResult streamResult = new StreamResult(new File(outputFile));
        transformer.transform(domSource, streamResult);
    }
}
