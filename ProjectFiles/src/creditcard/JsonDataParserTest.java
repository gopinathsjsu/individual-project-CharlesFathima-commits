package creditcard;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class JsonDataParserTest {

    private JsonDataParser jsonDataParserUnderTest;

    @Before
    public void setUp() {
        jsonDataParserUnderTest = new JsonDataParser();
    }

    @Test
    public void testProcessEachValidRecord() {
        final JSONObject record = new JSONObject(new HashMap<>());
        final JSONObject expectedResult = new JSONObject(new HashMap<>());
        record.put("cardNumber","5410000000000000");
        record.put("ExpirationDate","3/20/2030");
        record.put("NameOfCardholder","Alice");
        expectedResult.put("cardNumber","5410000000000000");
        expectedResult.put("cardType","MasterCard");
        final JSONObject result = jsonDataParserUnderTest.processSingleRecord(record);
        assertEquals(expectedResult, result);
    }
}
