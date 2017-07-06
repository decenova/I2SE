/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import minhnh.dao.MinhRestaurantDAO;
import minhnh.dto.FoodDTO;

/**
 *
 * @author kubin
 */
public class FoodBean implements Serializable{
     private String foodName;
    private String foodType;
    private int quantity;

    public FoodBean() {
    }
    
    public List<FoodDTO> getMostEatFood(){
        MinhRestaurantDAO dao = new MinhRestaurantDAO();
        return dao.getMostEatFood();
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
