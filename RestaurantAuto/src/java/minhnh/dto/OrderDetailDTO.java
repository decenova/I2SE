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
public class OrderDetailDTO implements Serializable{
    private String foodName;
    private int quantity;
    private int price;

    public OrderDetailDTO() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderDetailDTO(String foodName, int quantity, int price) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }
}
