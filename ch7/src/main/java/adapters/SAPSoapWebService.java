package adapters;

import domain.ShoppingCart;
import ports.SAP;

public class SAPSoapWebService implements SAP {
    @Override
    public void cartReadyForDelivery(ShoppingCart cart) {
        // all the code required to send the
        // cart to SAP's SOAP web service
    }
}
