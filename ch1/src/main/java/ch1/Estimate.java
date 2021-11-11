package ch1;

import java.util.Objects;

public class Estimate {

    private final String developer;
    private final int estimate;

    public Estimate(String developer, int estimate) {
        this.developer = developer;
        this.estimate = estimate;
    }

    public String getDeveloper() {
        return developer;
    }

    public int getEstimate() {
        return estimate;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "developer='" + developer + '\'' +
                ", estimate=" + estimate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estimate estimate1 = (Estimate) o;
        return estimate == estimate1.estimate && Objects.equals(developer, estimate1.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developer, estimate);
    }
}
