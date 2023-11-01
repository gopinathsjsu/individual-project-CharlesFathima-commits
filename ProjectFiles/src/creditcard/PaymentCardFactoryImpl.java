package creditcard;

public class PaymentCardFactoryImpl implements PaymentCardFactory {
    public PaymentCard createPaymentCard(String cardNumber) {
        if (cardNumber.isEmpty() || cardNumber.length() >= 17) {
            return null;
        }
        try {
            long number = Long.parseLong(cardNumber);
            String numStr = Long.toString(number);

            if (numStr.length() <= 16 && numStr.matches("[0-9]+")) {
                switch (numStr.charAt(0)) {
                    case '3':
                        if (numStr.length() == 15) {
                            return new AmericanExpressPaymentCard(cardNumber);
                        }
                        break;
                    case '4':
                        if (numStr.length() == 13 || numStr.length() == 16) {
                            return new VisaPaymentCard(cardNumber);
                        }
                        break;
                    case '5':
                        if (numStr.length() == 16) {
                            return new MasterPaymentCard(cardNumber);
                        }
                        break;
                    case '6':
                        if (numStr.length() == 16) {
                            return new DiscoverPaymentCard(cardNumber);
                        }
                        break;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
}
