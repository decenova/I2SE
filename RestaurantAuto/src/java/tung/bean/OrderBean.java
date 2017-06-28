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

    public OrderBean() {
    }
    
    public boolean addOrder() {
        OrderDAO dao = new OrderDAO();
        return dao.addOrderFirst(seqTable, seqWaiter, beginTime);
    }
    
}
