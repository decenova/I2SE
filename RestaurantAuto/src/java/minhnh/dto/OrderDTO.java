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
public class OrderDTO implements Serializable{
    private String orderId;
    private int tableId;
    private String waiterName;
    private int cost;

    public OrderDTO() {
    }

    public OrderDTO(String Id, int tableId, String waiterName, int cost) {
        this.orderId = Id;
        this.tableId = tableId;
        this.waiterName = waiterName;
        this.cost = cost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
}
