package creditcard;

public class AmericanExpressPaymentCard extends PaymentCard {

    public AmericanExpressPaymentCard(String cardNumber) {
        super(cardNumber);
    }

    @Override
    String determineCardType(String cardNumber) {
        if (cardNumber.equals("")) {
            return "Invalid";
        }
        try {
            long number = Long.parseLong(cardNumber);
            String value = Long.toString(number);
            if ((value.charAt(0) == '3') && (value.charAt(1) == '4' || value.charAt(1) == '7')) {
                return "AmericanExpress";
            }
        } catch (NumberFormatException e) {
            return "Invalid";
        }
        return "Invalid";
    }
}
