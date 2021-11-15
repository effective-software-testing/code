package ch4;

public class TaxCalculator {
    /**
     * Calculates the tax according to (some
     * explanation here...)
     *
     * @param value the base value for tax calculation. Value has
     *              to be a positive number.
     * @return the calculated tax. The tax is always a positive number.
     */
    public double calculateTax(double value) {
        // pre-condition check
        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;


        // some complex business rule here...
        // final value goes to 'taxValue'

        // post-condition check
        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }

        return taxValue;

    }
}
