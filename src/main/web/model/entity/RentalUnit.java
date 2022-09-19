package main.web.model.entity;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class RentalUnit {
    @BsonProperty("_id")
    private ObjectId id;

    private String name;
    private Double latitude;
    private Double longitude;
    private Integer bathrooms;
    private Integer bedrooms;
    private Integer beds;
    private String picture_url;
    private String[] amenities;
    private String description;

    public RentalUnit() {

    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public Integer getBeds() {
        return beds;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public String getDescription() {
        return description;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format(
                "Property %s (lat=%f; long=%f) has %d bedrooms, %d beds and %d bathrooms",
                String.valueOf(id), latitude, longitude,
                bedrooms, beds, bathrooms);
    }
}