package net.etna.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurants {
    @SerializedName("id")
    @Expose
    private Integer restaurantId;
    @SerializedName("name")
    @Expose
    private String restaurantName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("localization")
    @Expose
    private String localization;
    @SerializedName("grade")
    @Expose
    private Float grade;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("hours")
    @Expose
    private String hours;

    public Integer getRestaurantId(){return restaurantId;}
    public void setRestaurantId(Integer restaurantId){this.restaurantId = restaurantId;}
    public String  getRestaurantName(){return restaurantName;}
    public void setRestaurantName(String restaurantName){this.restaurantName = restaurantName;}
    public String  getHours(){return hours;}
    public void setHours(String hours){this.hours = hours;}
    public String  getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public String  getLocalization(){return localization;}
    public void setLocalization(String localization){this.localization = localization;}
    public Float   getGrade(){return grade;}
    public void setGrade(Float grade){this.grade = grade;}
    public String  getPhone_number(){return phone_number;}
    public void setPhone_number(String phone_number){this.phone_number = phone_number;}
    public String  getWebsite(){return website;}
    public void setWebsite(String website){this.website = website;}

}

