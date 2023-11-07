package creditcard;

import javax.xml.transform.TransformerException;

public interface DataFileParser {
    void processCardRecords(String outputFile);

    void retrieveRecords(String inputFile);

    void writeDataFile(String outputFile) throws TransformerException;
}
