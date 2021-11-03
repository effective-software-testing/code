package domain;


import adapters.DeliveryCenterRestApi;
import adapters.SAPSoapWebService;
import adapters.SMTPCustomerNotifier;
import adapters.ShoppingCartHibernateDao;

import java.time.LocalDate;
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

            // send notification using the adapter directly
            // Bad for testability!
            SMTPCustomerNotifier notifier = new SMTPCustomerNotifier();
            notifier.sendEstimatedDeliveryNotification(cart);

            // notify SAP using the adapter directly
            // Bad for testability!
            SAPSoapWebService sap = new SAPSoapWebService();
            sap.cartReadyForDelivery(cart);
        }
    }
}
