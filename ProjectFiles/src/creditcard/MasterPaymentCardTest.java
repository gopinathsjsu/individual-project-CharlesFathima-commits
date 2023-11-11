package creditcard;

import org.junit.Test;

import static org.junit.Assert.*;

public class MasterPaymentCardTest {

    @Test
    public void testValidateMasterCardType() {
        MasterPaymentCard mc = new MasterPaymentCard("5.41E+15");
        String result = mc.determineCardType("5.41E+15");
        assertEquals("MasterCard", result);
    }

    @Test
    public void testSecondDigitNotInclusiveOneAndFive(){
        MasterPaymentCard mc = new MasterPaymentCard("5.61E+15");
        String result = mc.determineCardType("5.61E+15");
        assertEquals("Invalid", result);
    }

    @Test
    public void testNoMasterCardNumber(){
        MasterPaymentCard mc = new MasterPaymentCard("");
        String result = mc.determineCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithWhiteSpaces(){
        MasterPaymentCard mc = new MasterPaymentCard("  ");
        String result = mc.determineCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithSpecialCharacters(){
        MasterPaymentCard mc = new MasterPaymentCard("541000000+000000");
        String result = mc.determineCardType("541000000+000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testMasterCardNumberWithCharacter(){
        MasterPaymentCard mc = new MasterPaymentCard("541000000v000000");
        String result = mc.determineCardType("541000000v000000");
        assertEquals("Invalid", result);
    }
}