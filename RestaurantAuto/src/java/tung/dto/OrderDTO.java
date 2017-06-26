/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.dto;

import java.io.Serializable;

/**
 *
 * @author hoanh
 */
public class OrderDTO implements Serializable {
    private String foodID;
    private String foodName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDTO() {
    }

    public OrderDTO(String foodID, String foodName) {
        this.foodID = foodID;
        this.foodName = foodName;
    }

    public OrderDTO(String foodID, String foodName, int quantity) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
    }
    

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

   
}
