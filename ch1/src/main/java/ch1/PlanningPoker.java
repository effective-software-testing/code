package ch1;

import java.util.Arrays;
import java.util.List;

public class PlanningPoker {

    public List<String> identifyExtremes(List<Estimate> estimates) {

        assert estimates != null;
        assert estimates.size() > 1;

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
