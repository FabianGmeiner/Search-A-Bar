package model;

import org.json.simple.JSONObject;

/**
 * Created by Fabian on 18.05.15.
 */

public class Bar extends dataElement{
    private long mId;               // unique ID, systems timestamp
    private String mName;           // the name of the bar
    private String mDescription;    // short description
    private int mCategory;           // the category of the bar
    private double mGpsLongitude;    // GPS-Longitude
    private double mGpsLatitude;     // GPS-Latitude
    private double mPrice;           // entry-fee in euro
    private int mAgeRestriction;     // the minimal required age
    private int mAverageAge;         // average age of visitors
    private int mRating;             // rating ranging from 0 to 5, with -1 as 'not rated yet'

    public Bar (String name, String description, int category, double longitude,
                double latitude, double price, int ageRestriction, int averageAge, int rating){

        mId = System.currentTimeMillis();
        mName = name;
        mDescription = description;
        mCategory = category;
        mGpsLongitude = longitude;
        mGpsLatitude = latitude;
        mPrice = price;
        mAgeRestriction = ageRestriction;
        mAverageAge = averageAge;
        mRating = rating;
    }

    // all the getter methods
    protected long getmId() {return mId;}
    protected String getmName() {return mName;}
    protected String getmDescription() {return mDescription;}
    protected int getmCategory() {return mCategory;}
    protected double getmGpsLongitude() {return mGpsLongitude;}
    protected double getmGpsLatitude() {return mGpsLatitude;}
    protected double getmPrice() {return mPrice;}
    protected int getmAgeRestriction() {return mAgeRestriction;}
    protected int getmAverageAge() {return mAverageAge;}
    protected int getmRating() {return mRating;}
    // all the setter methods
    protected void setmPrice(double mPrice) {this.mPrice = mPrice;}
    protected void setmAgeRestriction(int mAgeRestriction) {this.mAgeRestriction = mAgeRestriction;}
    protected void setmAverageAge(int mAverageAge) {this.mAverageAge = mAverageAge;}
    protected void setmRating(int mRating) {this.mRating = mRating;}

    @Override
    public String toString(){
       return mName;
    }
    // Method to export the Bar into a JSON Object
    public JSONObject toJSONObject (){
        JSONObject obj = new JSONObject();
        obj.put("id", mId);
        obj.put("name", mName);
        obj.put("description", mDescription);
        obj.put("category", mCategory);
        obj.put("longitude", mGpsLongitude);
        obj.put("latitude", mGpsLatitude);
        obj.put("price", mPrice);
        obj.put("ageRestriction", mAgeRestriction);
        obj.put("averageAge", mAverageAge);
        obj.put("rating", mRating);
        return obj;
    }
}
