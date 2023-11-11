package creditcard;

public class VisaPaymentCard extends PaymentCard {

    public VisaPaymentCard(String cardNumber) {
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
            if ((value.length() == 13 || value.length() == 16) && value.charAt(0) == '4') {
                return "Visa";
            }
        } catch (NumberFormatException e) {
            return "Invalid";
        }
        return "Invalid";
    }
}
