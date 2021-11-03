package ch1;

import java.util.Arrays;
import java.util.List;

public class PlanningPoker {

    public List<String> identifyExtremes(List<Estimate> estimates) {

        // these checks could have been assertions.
        // we will discuss more about it in the design-by-contract chapter
        if(estimates == null) {
            throw new IllegalArgumentException("Estimates can't be null");
        }
        if(estimates.size() <= 1) {
            throw new IllegalArgumentException("There has to be more than 1 estimate in the list");
        }

        Estimate lowestEstimate = null;
        Estimate highestEstimate = null;

        for(Estimate estimate: estimates) {
            if(highestEstimate == null ||
                    estimate.getEstimate() > highestEstimate.getEstimate()) {
                highestEstimate = estimate;
            }
            if(lowestEstimate == null ||
                    estimate.getEstimate() < lowestEstimate.getEstimate()) {
                lowestEstimate = estimate;
            }
        }

        return Arrays.asList(
                lowestEstimate.getDeveloper(),
                highestEstimate.getDeveloper()
        );
    }
}
