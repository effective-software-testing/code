package ch6.exception;

import java.util.Objects;

public class SapInvoice {
    private final String customer;
    private final int value;
    private final String id;

    public SapInvoice(String customer, int value, String id) {
        assert customer!=null;
        assert id!=null;

        this.customer = customer;
        this.value = value;
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public int getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SapInvoice that = (SapInvoice) o;
        return value == that.value && customer.equals(that.customer) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, value, id);
    }

    @Override
    public String toString() {
        return "SapInvoice{" +
                "customer='" + customer + '\'' +
                ", value=" + value +
                ", id='" + id + '\'' +
                '}';
    }
}
