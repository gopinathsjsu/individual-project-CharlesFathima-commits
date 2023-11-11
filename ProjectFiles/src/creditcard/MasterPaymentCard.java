package creditcard;

public class MasterPaymentCard extends PaymentCard {

    public MasterPaymentCard(String cardNumber) {
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
            if (value.length() == 16 && (value.charAt(1) >= '1' && value.charAt(1) <= '5')) {
                return "MasterCard";
            }
        } catch (NumberFormatException e) {
            return "Invalid";
        }
        return "Invalid";
    }
}
