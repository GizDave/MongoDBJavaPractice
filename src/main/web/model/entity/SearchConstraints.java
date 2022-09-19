package main.web.model.entity;

public class SearchConstraints {
    class ValueRange {
        private final int minVal;
        private final int maxVal;

        public ValueRange(int minVal, int maxVal){
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

        public boolean inRange(int val){
            return val >= this.minVal && val <= this.maxVal;
        }

        public int getMinVal() {
            return minVal;
        }

        public int getMaxVal() {
            return maxVal;
        }
    }
    private final ValueRange bedrooms;
    private final ValueRange beds;
    private final ValueRange bathrooms;

    public SearchConstraints(int minBedrooms, int maxBedrooms, int minBeds, int maxBeds, int minBathrooms, int maxBathrooms) {
        this.bedrooms = new ValueRange(minBedrooms, maxBedrooms);
        this.beds = new ValueRange(minBeds, maxBeds);
        this.bathrooms = new ValueRange(minBathrooms, maxBathrooms);
    }

    public boolean isValidInput(SearchForm userRequest) {
        return bedrooms.inRange(userRequest.getBedrooms())
                && beds.inRange(userRequest.getBeds())
                && bathrooms.inRange(userRequest.getBathrooms());
    }

    public boolean isBedroomValid(int bedrooms) {
        return this.bedrooms.inRange(bedrooms);
    }

    public boolean isBedValid(int beds) {
        return this.beds.inRange(beds);
    }

    public boolean isBathroomValid(int bathrooms) {
        return this.bathrooms.inRange(bathrooms);
    }

    public String toString() {
        return String.format(
                "#bedrooms: [%d, %d], #beds: [%d, %d], #bathrooms: [%d, %d]",
                bedrooms.getMinVal(), bedrooms.getMaxVal(),
                beds.getMinVal(), beds.getMaxVal(),
                bathrooms.getMinVal(), bathrooms.getMaxVal()
        );
    }
}
