package creditcard;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscoverPaymentCardTest {

    @Test
    public void testNoDiscoverCardNumber(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("");
        String result = d.determineCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidateDiscoverCardType() {
        DiscoverPaymentCard d = new DiscoverPaymentCard("6.011E+15");
        String result = d.determineCardType("6.011E+15");
        assertEquals("Discover", result);
    }

    @Test
    public void testFalseDiscoverCardType() {
        DiscoverPaymentCard d = new DiscoverPaymentCard("6.010E+15");
        String result = d.determineCardType("6.010E+15");
        assertEquals("Invalid", result);
    }

    @Test
    public void testInvalidDiscoverCardType(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("6123456789012345");
        String result = d.determineCardType("6123456789012345");
        assertEquals("Invalid", result);
    }

    @Test
    public void testWrongDiscoverCardType(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("1234567890126011");
        String result = d.determineCardType("1234567890126011");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_WhenCharacter_InFirstFourIndex(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("6a11567890123456");
        String result = d.determineCardType("6a11567890123456");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_WhenCharacter_InAnyIndex(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("601156789012_456");
        String result = d.determineCardType("601156789012_456");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnInvalid_With_WhiteSpaces(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("   ");
        String result = d.determineCardType("  ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testValidDiscoverCardType(){
        DiscoverPaymentCard d = new DiscoverPaymentCard("6011582364756127");
        String result = d.determineCardType("6011582364756127");
        assertEquals("Discover", result);
    }
}