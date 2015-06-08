//Created by Fabian on 18.05.15.
package model;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Bar implements Serializable {
    private String mName;           // the name of the bar
    private String mDescription;    // short description
    private String mUrl;
    private String mAdress;
    private int mCategory;           // the category of the bar
    private double mGpsLongitude;    // GPS-Longitude
    private double mGpsLatitude;     // GPS-Latitude
    private double mPrice;           // entry-fee in euro
    private int mAgeRestriction;     // the minimal required age
    private int mAverageAge;         // average age of visitors
    private int mPopularity = 0;

    public Bar(String name, String description, String url, String adress, int category,
               double latitude, double longitude, double price, int ageRestriction, int averageAge) {

        mName = name;
        mDescription = description;
        mUrl = url;
        mAdress = adress;
        mCategory = category;
        mGpsLongitude = longitude;
        mGpsLatitude = latitude;
        mPrice = price;
        mAgeRestriction = ageRestriction;
        mAverageAge = averageAge;
    }

    // all the getter methods
    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmAdress() {
        return mAdress;
    }

    public int getmCategory() {
        return mCategory;
    }

    public double getmGpsLongitude() {
        return mGpsLongitude;
    }

    public double getmGpsLatitude() {
        return mGpsLatitude;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmAgeRestriction() {
        return mAgeRestriction;
    }

    public void setmAgeRestriction(int mAgeRestriction) {
        this.mAgeRestriction = mAgeRestriction;
    }

    public int getmAverageAge() {
        return mAverageAge;
    }

    public void setmAverageAge(int mAverageAge) {
        this.mAverageAge = mAverageAge;
    }

    // all the setter methods
    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setAdress(String adress) {
        mAdress = adress;
    }

    public void setCategory(int category) {
        mCategory = category;
    }

    public void setGpsLongitude(double gpsLongitude) {
        mGpsLongitude = gpsLongitude;
    }

    public void setGpsLatitude(double gpsLatitude) {
        mGpsLatitude = gpsLatitude;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public void setAgeRestriction(int ageRestriction) {
        mAgeRestriction = ageRestriction;
    }

    public void setAverageAge(int averageAge) {
        mAverageAge = averageAge;
    }

    public void increasePopularity() {
        mPopularity++;
    }

    public int getPopularity() {
        return mPopularity;
    }

    @Override
    public String toString() {
        return mName;
    }
}
