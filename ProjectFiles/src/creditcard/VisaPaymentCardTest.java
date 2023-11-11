package creditcard;

import org.junit.Test;

import static org.junit.Assert.*;

public class VisaPaymentCardTest {

    @Test
    public void testNoVisaCardNumber(){
        VisaPaymentCard v = new VisaPaymentCard("");
        String result = v.determineCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidateVisaCardType() {
        VisaPaymentCard v = new VisaPaymentCard("4.12E+12");
        String result = v.determineCardType("4.12E+12");
        assertEquals("Visa", result);
    }

    @Test
    public void testVisaCardWhiteSpaces() {
        VisaPaymentCard v = new VisaPaymentCard("  ");
        String result = v.determineCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testVisaCardWith14Digits() {
        VisaPaymentCard v = new VisaPaymentCard("45678901234567");
        String result = v.determineCardType("45678901234567");
        assertEquals("Invalid", result);
    }

    @Test
    public void testVisaCardWithCharacters() {
        VisaPaymentCard v = new VisaPaymentCard("45678a1234567");
        String result = v.determineCardType("45678a1234567");
        assertEquals("Invalid", result);
    }

    @Test
    public void testWrongVisaNumber() {
        VisaPaymentCard v = new VisaPaymentCard("5567891234567765");
        String result = v.determineCardType("5567891234567765");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValid16DigitVisaCard() {
        VisaPaymentCard v = new VisaPaymentCard("4567891234567765");
        String result = v.determineCardType("4567891234567765");
        assertEquals("Visa", result);
    }

    @Test
    public void test_Invalid_SpecialCharacter_In_VisaCard() {
        VisaPaymentCard v = new VisaPaymentCard("45678912_4567765");
        String result = v.determineCardType("45678912_4567765");
        assertEquals("Invalid", result);
    }
}