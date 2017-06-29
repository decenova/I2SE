/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.bean;

import java.sql.Timestamp;
import tung.dao.OrderDAO;

/**
 *
 * @author hoanh
 */
public class OrderBean {

    private int seqTable, seqWaiter;
    private Timestamp beginTime;
    private String tableID, staffID;

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

    public boolean addOrderFirst() {
        OrderDAO dao = new OrderDAO();
        return dao.addOrderFirst(seqTable, seqWaiter, beginTime);
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
