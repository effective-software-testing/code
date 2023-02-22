package domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

public class Installment {

    private final LocalDate date;
    private final double value;

    public Installment(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Installment that = (Installment) o;

        if (Double.compare(that.value, value) != 0) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
}
