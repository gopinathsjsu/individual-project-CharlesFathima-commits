package creditcard;

abstract class PaymentCard {

    private String number;

    public PaymentCard(String number){
        this.number = number;
    }

    abstract String determineCardType(String number);

}
