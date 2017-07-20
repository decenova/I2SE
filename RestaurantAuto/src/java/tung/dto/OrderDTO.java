/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author hoanh
 */
public class OrderDTO implements Serializable {

    private String foodID;
    private String foodName;
    private int quantity, cost;
    private int seq, seqWaiter, seqTable, seqOD;
    private String tableID, waiterID;
    Timestamp beginTime;
    Timestamp beginEatTime;
    private List<OrderDTO> foodDetails;
    private List<OrderDTO> foodChoice;
    private List<OrderDTO> foodWaiting;
    public OrderDTO() {
    }

    public OrderDTO(int seq, String tableID, String waiterID, Timestamp beginTime) {
        this.seq = seq;
        this.tableID = tableID;
        this.waiterID = waiterID;
        this.beginTime = beginTime;
    }
    

    public OrderDTO(int seq, String tableID, Timestamp beginEatTime) {
        this.seq = seq;
        this.tableID = tableID;
        this.beginEatTime = beginEatTime;
    }

        
    public OrderDTO(String foodID, String foodName, int quantity) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
    }
        
    public OrderDTO(String foodID, String foodName, int quantity, int cost) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
        

    public OrderDTO(String foodID, String foodName) {
        this.foodID = foodID;
        this.foodName = foodName;
    }

    public OrderDTO(int seqOD, String foodID, String foodName, int quantity) {
        this.seqOD = seqOD;
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
    }
        public OrderDTO(int seqOD, String foodID, String foodName, int quantity, int cost) {
        this.seqOD = seqOD;
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
        this.quantity = quantity;
        this.cost = cost;
    }
   
    
    public Timestamp getBeginEatTime() {
        return beginEatTime;
    }

    public void setBeginEatTime(Timestamp beginEatTime) {
        this.beginEatTime = beginEatTime;
    }

    public List<OrderDTO> getFoodWaiting() {
        return foodWaiting;
    }

    public void setFoodWaiting(List<OrderDTO> foodWaiting) {
        this.foodWaiting = foodWaiting;
    }
    

    public List<OrderDTO> getFoodDetails() {
        return foodDetails;
    }

    public void setFoodDetails(List<OrderDTO> foodDetails) {
        this.foodDetails = foodDetails;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSeqOD() {
        return seqOD;
    }

    public void setSeqOD(int seqOD) {
        this.seqOD = seqOD;
    }
    

    public int getSeqWaiter() {
        return seqWaiter;
    }

    public void setSeqWaiter(int seqWaiter) {
        this.seqWaiter = seqWaiter;
    }

    public int getSeqTable() {
        return seqTable;
    }

    public void setSeqTable(int seqTable) {
        this.seqTable = seqTable;
    }

    public String getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(String waiterID) {
        this.waiterID = waiterID;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public List<OrderDTO> getFoodChoice() {
        return foodChoice;
    }

    public void setFoodChoice(List<OrderDTO> foodChoice) {
        this.foodChoice = foodChoice;
    }
    

}
