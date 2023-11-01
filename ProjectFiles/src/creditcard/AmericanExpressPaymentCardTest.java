package creditcard;

import org.junit.Test;

import static org.junit.Assert.*;

public class AmericanExpressPaymentCardTest {


    @Test
    public void testNoAmericanExpNumber(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("");
        String result = ax.determineCardType("");
        assertEquals("Invalid", result);
    }

    @Test
    public void testSecondDigitAmericanExpNumber(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("351000000000000");
        String result = ax.determineCardType("351000000000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void Should_ReturnValid_When_SecondDigitIs7(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("371000000000000");
        String result = ax.determineCardType("371000000000000");
        assertEquals("AmericanExpress", result);
    }

    @Test
    public void testAmericanExpWithSpecialCharacters(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("37100000,000000");
        String result = ax.determineCardType("37100000,000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithWhiteSpacesInBetween(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("37100000  000000");
        String result = ax.determineCardType("37100000  000000");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithOnlyWhiteSpaces(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("   ");
        String result = ax.determineCardType("   ");
        assertEquals("Invalid", result);
    }

    @Test
    public void testAmericanExpWithFirstDigit(){
        AmericanExpressPaymentCard ax = new AmericanExpressPaymentCard("471000000000000");
        String result = ax.determineCardType("471000000000000");
        assertEquals("Invalid", result);
    }

}