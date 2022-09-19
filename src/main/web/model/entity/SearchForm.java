package main.web.model.entity;

public class SearchForm {
    private final int bedrooms;
    private final int beds;
    private final int bathrooms;

    public SearchForm(int bedrooms, int beds, int bathrooms) {
        this.bedrooms = bedrooms;
        this.beds = beds;
        this.bathrooms = bathrooms;
    }

    public static SearchForm createForm(int bedrooms, int beds, int bathrooms) {
        return new SearchForm(bedrooms, beds, bathrooms);
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBeds() {
        return beds;
    }

    public int getBathrooms() {
        return bathrooms;
    }
}
