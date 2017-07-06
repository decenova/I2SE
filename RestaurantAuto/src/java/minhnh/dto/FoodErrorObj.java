/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnh.dto;

/**
 *
 * @author kubin
 */
public class FoodErrorObj {
    private String foodIdErr;
    private String foodNameErr;
    private String foodTypeErr;
    private String costErr;
    private String availableErr;
    private String duplicateErr;

    public FoodErrorObj() {
    }

    public String getFoodIdErr() {
        return foodIdErr;
    }

    public void setFoodIdErr(String foodIdErr) {
        this.foodIdErr = foodIdErr;
    }

    public String getFoodNameErr() {
        return foodNameErr;
    }

    public void setFoodNameErr(String foodNameErr) {
        this.foodNameErr = foodNameErr;
    }

    public String getFoodTypeErr() {
        return foodTypeErr;
    }

    public void setFoodTypeErr(String foodTypeErr) {
        this.foodTypeErr = foodTypeErr;
    }

    public String getCostErr() {
        return costErr;
    }

    public void setCostErr(String costErr) {
        this.costErr = costErr;
    }

    public String getAvailableErr() {
        return availableErr;
    }

    public void setAvailableErr(String availableErr) {
        this.availableErr = availableErr;
    }

    public String getDuplicateErr() {
        return duplicateErr;
    }

    public void setDuplicateErr(String duplicateErr) {
        this.duplicateErr = duplicateErr;
    }

    
}
