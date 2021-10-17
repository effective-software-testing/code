package domain;


import adapters.DeliveryCenterRestApi;
import adapters.SAPSoapWebService;
import adapters.SMTPEmailSender;
import adapters.ShoppingCartHibernateDao;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class VeryBadPaidShoppingCartsBatch {

    public void processAll() {

        // we instantiate the db adapter.
        // Bad for testability!
        ShoppingCartHibernateDao db = new ShoppingCartHibernateDao();
        List<ShoppingCart> paidShoppingCarts = db.cartsPaidToday();
        for (ShoppingCart cart : paidShoppingCarts) {

            // notify the delivery system about the delivery
            // but first, we need to instantiate its adapter.
            // Bad for testability!
            DeliveryCenterRestApi deliveryCenter = new DeliveryCenterRestApi();
            LocalDate estimatedDayOfDelivery = deliveryCenter.deliver(cart);

            // mark as ready for delivery and persist
            cart.markAsReadyForDelivery(estimatedDayOfDelivery);
            db.persist(cart);

            // send e-mail using the adapter directly
            // Bad for testability!
            SMTPEmailSender mail = new SMTPEmailSender();
            mail.sendEstimatedDeliveryEmail(cart);

            // notify SAP using the adapter directly
            // Bad for testability!
            SAPSoapWebService sap = new SAPSoapWebService();
            sap.cartReadyForDelivery(cart);
        }
    }
}
