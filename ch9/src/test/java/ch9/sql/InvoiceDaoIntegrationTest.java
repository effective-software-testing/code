package ch9.sql;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceDaoIntegrationTest extends SqlIntegrationTestBase {


    @Test
    void save() {
        Invoice inv1 = new Invoice("Mauricio", 10);
        Invoice inv2 = new Invoice("Frank", 11);

        dao.save(inv1);

        List<Invoice> afterSaving = dao.all();
        assertThat(afterSaving).containsExactlyInAnyOrder(inv1);

        dao.save(inv2);
        List<Invoice> afterSavingAgain = dao.all();
        assertThat(afterSavingAgain).containsExactlyInAnyOrder(inv1, inv2);
    }

    @Test
    void atLeast() {
        int value = 50;

        /**
         * Explore the boundary: value >= x
         * On point = x
         * Off point = x-1
         * In point = x + 1 (not really necessary, but it's cheap, and makes the
         *   test strategy easier to comprehend)
         */
        Invoice inv1 = new Invoice("Mauricio", value - 1);
        Invoice inv2 = new Invoice("Arie", value);
        Invoice inv3 = new Invoice("Frank", value + 1);

        dao.save(inv1);
        dao.save(inv2);
        dao.save(inv3);

        List<Invoice> afterSaving = dao.allWithAtLeast(value);
        assertThat(afterSaving).containsExactlyInAnyOrder(inv2, inv3);
    }
}
