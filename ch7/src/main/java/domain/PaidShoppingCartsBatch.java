package domain;

import ports.DeliveryCenter;
import ports.EmailService;
import ports.SAP;
import ports.ShoppingCartRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class PaidShoppingCartsBatch {

    private ShoppingCartRepository db;
    private DeliveryCenter deliveryCenter;
    private EmailService mail;
    private SAP sap;

    public PaidShoppingCartsBatch(ShoppingCartRepository db, DeliveryCenter deliveryCenter,
                                  EmailService mail, SAP sap) {
        this.db = db;
        this.deliveryCenter = deliveryCenter;
        this.mail = mail;
        this.sap = sap;
    }

    public void processAll() {

        List<ShoppingCart> paidShoppingCarts = db.cartsPaidToday();
        for (ShoppingCart cart : paidShoppingCarts) {

            // notify the delivery system about the delivery
            LocalDate estimatedDayOfDelivery = deliveryCenter.deliver(cart);

            // mark as ready for delivery and persist
            cart.markAsReadyForDelivery(estimatedDayOfDelivery);
            db.persist(cart);

            // send e-mail
            mail.sendEstimatedDeliveryEmail(cart);

            // notify SAP
            sap.cartReadyForDelivery(cart);
        }
    }
}
