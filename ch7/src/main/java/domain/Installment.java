package domain;

import java.time.LocalDate;
import java.util.Calendar;

public class Installment {

    private final LocalDate date;
    private final double value;

    public Installment(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }
}
