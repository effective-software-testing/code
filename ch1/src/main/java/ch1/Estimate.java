package ch1;

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
}
