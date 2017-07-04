/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author hoanh
 */
public class OrderDTO implements Serializable {

    private String foodID;
    private String foodName;
    private int quantity;
    private int seq, seqWaiter, seqTable;
    private String tableID, waiterID;
    Timestamp beginTime;

    public OrderDTO() {
    }

    public OrderDTO(int seq, String tableID, String waiterID, Timestamp beginTime) {
        this.seq = seq;
        this.tableID = tableID;
        this.waiterID = waiterID;
        this.beginTime = beginTime;
    }

    public OrderDTO(int seq, String tableID) {
        this.seq = seq;
        this.tableID = tableID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
