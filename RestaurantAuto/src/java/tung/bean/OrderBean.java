/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.bean;

import java.sql.Timestamp;
import java.util.List;
import tung.dao.OrderDAO;
import tung.dto.OrderDTO;

/**
 *
 * @author hoanh
 */
public class OrderBean {

    private int seqTable, seqWaiter, seqOrder, seqFood;
    private Timestamp beginTime;
    private String tableID, staffID, foodID;
    int quantity;

    public OrderBean() {
    }

    public int getSeqTable() {
        OrderDAO dao = new OrderDAO();
        return dao.getSEQTableById(tableID);
    }

    public int getSeqStaff() {
        OrderDAO dao = new OrderDAO();
        return dao.getSEQStaffById(staffID);
    }

    public int getSEQOrder() {
        OrderDAO dao = new OrderDAO();
        return dao.getMaxSEQOrder();
    }

    public int getSEQFood() {
        OrderDAO dao = new OrderDAO();
        return dao.getSEQFoodById(foodID);
    }

    public boolean addOrderDetail() {
        OrderDAO dao = new OrderDAO();
        return dao.addOrderDetailById(seqOrder, seqFood, quantity);
    }

    public boolean addOrderFirst() {
        OrderDAO dao = new OrderDAO();
        return dao.addOrderFirst(seqTable, seqWaiter, beginTime);
    }

    public List<OrderDTO> loadOrders() {
        OrderDAO dao = new OrderDAO();
        return dao.loadOrders();
    }

    public int getSeqOrder() {
        return seqOrder;
    }

    public void setSeqOrder(int seqOrder) {
        this.seqOrder = seqOrder;
    }

    public int getSeqFood() {
        return seqFood;
    }

    public void setSeqFood(int seqFood) {
        this.seqFood = seqFood;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setSeqTable(int seqTable) {
        this.seqTable = seqTable;
    }

    public int getSeqWaiter() {
        return seqWaiter;
    }

    public void setSeqWaiter(int seqWaiter) {
        this.seqWaiter = seqWaiter;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

}
