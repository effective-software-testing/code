package ch7;

import domain.Installment;
import domain.InstallmentGenerator;
import domain.InstallmentRepository;
import domain.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InstallmentGeneratorTest {

    @Mock
    private InstallmentRepository repository;

    @Test
    void checkInstallments() {

        ShoppingCart cart = new ShoppingCart(100.0);
        InstallmentGenerator generator = new InstallmentGenerator(repository);

        generator.generateInstallments(cart, 10);

        // create a Mockito captor
        ArgumentCaptor<Installment> captor = ArgumentCaptor.forClass(Installment.class);

        // get all the Installments that were passed to the repository
        verify(repository,times(10)).persist(captor.capture());
        List<Installment> allInstallments = captor.getAllValues();

        // now, we assert that the installments are correct
        // all  them should have a value of 10.0
        assertThat(allInstallments)
                .hasSize(10)
                .allMatch(i -> i.getValue() == 10);

        // they should have to be one month apart
        for(int month = 1; month <= 10; month++) {
            final LocalDate dueDate = LocalDate.now().plusMonths(month);
            assertThat(allInstallments)
                    .anyMatch(i -> i.getDate().equals(dueDate));
        }
    }

    @Test
    void checkInstallments2() {

        ShoppingCart cart = new ShoppingCart(100.0);
        InstallmentGenerator generator = new InstallmentGenerator(repository);

        List<Installment> allInstallments = generator.generateInstallments2(cart, 10);

        // now, we assert that the installments are correct
        // all  them should have a value of 10.0
        assertThat(allInstallments)
                .hasSize(10)
                .allMatch(i -> i.getValue() == 10);

        // they should have to be one month apart
        for(int month = 1; month <= 10; month++) {
            final LocalDate dueDate = LocalDate.now().plusMonths(month);
            assertThat(allInstallments)
                    .anyMatch(i -> i.getDate().equals(dueDate));
        }
    }
}
