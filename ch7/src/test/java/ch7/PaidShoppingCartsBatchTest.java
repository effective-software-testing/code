package ch7;

import domain.PaidShoppingCartsBatch;
import domain.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ports.DeliveryCenter;
import ports.CustomerNotifier;
import ports.SAP;
import ports.ShoppingCartRepository;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaidShoppingCartsBatchTest {

    @Mock
    ShoppingCartRepository db;
    @Mock private DeliveryCenter deliveryCenter;
    @Mock private CustomerNotifier notifier;
    @Mock private SAP sap;

    @Test
    void happyPath() {
        PaidShoppingCartsBatch batch = new PaidShoppingCartsBatch(db, deliveryCenter, notifier, sap);

        ShoppingCart someCart = new ShoppingCart(100);
        assertThat(someCart.isReadyForDelivery()).isFalse();

        LocalDate someDate = LocalDate.now();
        when(db.cartsPaidToday()).thenReturn(Arrays.asList(someCart));
        when(deliveryCenter.deliver(someCart)).thenReturn(someDate);

        batch.processAll();

        verify(deliveryCenter).deliver(someCart);
        verify(notifier).sendEstimatedDeliveryNotification(someCart);
        verify(db).persist(someCart);
        verify(sap).cartReadyForDelivery(someCart);
        assertThat(someCart.isReadyForDelivery()).isTrue();
    }
}
