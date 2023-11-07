package creditcard;

public class DiscoverPaymentCard extends PaymentCard {

    public DiscoverPaymentCard(String cardNumber) {
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
            if (value.startsWith("6011")) {
                return "Discover";
            }
        } catch (NumberFormatException e) {
            return "Invalid";
        }
        return "Invalid";
    }
}
