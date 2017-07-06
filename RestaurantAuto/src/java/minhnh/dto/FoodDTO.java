/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnh.dto;

import java.io.Serializable;

/**
 *
 * @author kubin
 */
public class FoodDTO implements Serializable{
    private String foodId;
    private String foodName;
    private String foodType;
    private int cost;
    private boolean available;
    private int quantity;

    public FoodDTO() {
    }

    public FoodDTO(String foodId, String foodName, String foodType, int cost, boolean available) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.cost = cost;
        this.available = available;
    }
    

    public FoodDTO(String foodName, String foodType, int quantity) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.quantity = quantity;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
