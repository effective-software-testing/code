package ch10;

public class InvoiceBuilder {

    private String country = "NL";
    private CustomerType customerType = CustomerType.PERSON;
    private double value = 500;

    public InvoiceBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public InvoiceBuilder asCompany() {
        this.customerType = CustomerType.COMPANY;
        return this;
    }

    public InvoiceBuilder withAValueOf(double value) {
        this.value = value;
        return this;
    }

    public Invoice build() {
        return new Invoice(value, country, customerType);
    }
}