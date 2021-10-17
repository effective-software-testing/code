package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentGenerator {

    private InstallmentRepository repository;

    public InstallmentGenerator(InstallmentRepository repository) {
        this.repository = repository;
    }

    public void generateInstallments(ShoppingCart cart, int numberOfInstallments) {
        // create a variable that will store the last installment date
        LocalDate nextInstallmentDueDate = LocalDate.now();

        // calculate the amount per installment
        double amountPerInstallment = cart.getValue() / numberOfInstallments;

        // create a sequence of installments, one month apart from each other
        for(int i = 1; i <= numberOfInstallments; i++) {
            // +1 to the month
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1);

            // create and persist the installment
            Installment newInstallment = new Installment(nextInstallmentDueDate, amountPerInstallment);
            repository.persist(newInstallment);
        }
    }

    public List<Installment> generateInstallments2(ShoppingCart cart, int numberOfInstallments) {

        List<Installment> generatedInstallments = new ArrayList<Installment>();

        // create a variable that will store the last installment date
        LocalDate nextInstallmentDueDate = LocalDate.now();

        // calculate the amount per installment
        double amountPerInstallment = cart.getValue() / numberOfInstallments;

        // create a sequence of installments, one month apart from each other
        for(int i = 1; i <= numberOfInstallments; i++) {
            // +1 to the month
            nextInstallmentDueDate = nextInstallmentDueDate.plusMonths(1);

            // create and persist the installment
            Installment newInstallment = new Installment(nextInstallmentDueDate, amountPerInstallment);
            generatedInstallments.add(newInstallment);
            repository.persist(newInstallment);
        }

        return generatedInstallments;
    }
}
