package adapters;

import domain.ShoppingCart;
import ports.CustomerNotifier;

public class SMTPCustomerNotifier implements CustomerNotifier {
    @Override
    public void sendEstimatedDeliveryNotification(ShoppingCart cart) {
        // all the required code to
        // send an email via SMTP
    }
}
