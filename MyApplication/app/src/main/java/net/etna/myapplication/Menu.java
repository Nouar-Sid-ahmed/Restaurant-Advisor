package net.etna.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("id")
    @Expose
    private Integer menuId;
    @SerializedName("name")
    @Expose
    private String menuName;
    @SerializedName("description")
    @Expose
    private String menuDescription;
    @SerializedName("restaurant_id")
    @Expose
    private Integer restaurant_id;
    @SerializedName("price")
    @Expose
    private Float price;

    public Float getPrice(){return price;}
    public void setPrice(Float price){this.price = price;}
    public Integer getMenuId(){return menuId;}
    public void setMenuId(Integer menuId){this.menuId = menuId;}
    public String  getMenuName(){return menuName;}
    public void setMenuName(String menuName){this.menuName = menuName;}
    public String  getMenuDescription(){return menuDescription;}
    public void setMenuDescription(String menuDescription){this.menuDescription = menuDescription;}
    public Integer  getRestaurant_id(){return restaurant_id;}
    public void setRestaurant_id(Integer restaurant_id){this.restaurant_id = restaurant_id;}
}
