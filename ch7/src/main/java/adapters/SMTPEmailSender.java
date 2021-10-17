package adapters;

import domain.ShoppingCart;
import ports.EmailService;

public class SMTPEmailSender implements EmailService {
    @Override
    public void sendEstimatedDeliveryEmail(ShoppingCart cart) {
        // all the required code to
        // send an email via SMTP
    }
}
