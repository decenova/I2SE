/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import connection.MyConnection;
import java.sql.Timestamp;
import tung.dto.OrderDTO;

/**
 *
 * @author hoanh
 */
public class OrderDAO {

    Connection conn;
    PreparedStatement preStm;
    ResultSet rs;

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrderDTO> loadMenu() {
        List<OrderDTO> result = null;
        try {
            String sql = "select id, name from Food where available = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            rs = preStm.executeQuery();
            result = new ArrayList<OrderDTO>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                OrderDTO dto = new OrderDTO(id, name);
                result.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getSEQTableById(String id) {
        int seq = 0;
        try {
            String sql = "select SEQ from [Table] where Id = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                seq = rs.getInt("SEQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return seq;
    }

    public int getSEQStaffById(String id) {
        int seq = 0;
        try {
            String sql = "select SEQ from Staff where Id = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                seq = rs.getInt("SEQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return seq;
    }

    public int getSEQFoodById(String id) {
        int seq = 0;
        try {
            String sql = "select SEQ from Food where Id = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                seq = rs.getInt("SEQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return seq;
    }

    public int getMaxSEQOrder() {
        int seq = 0;
        try {
            String sql = "select max(SEQ) as 'MaxSeq' from [Order]";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                seq = rs.getInt("MaxSeq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return seq;
    }

    public boolean addOrderFirst(int seqTable, int seqWaiter, Timestamp begin) {
        boolean check = false;
        try {
            String sql = "insert into [Order] (TableID, WaiterID, BeginTime) values (?, ?, ?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqTable);
            preStm.setInt(2, seqWaiter);
            preStm.setTimestamp(3, begin);
            if (preStm.executeUpdate() > 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean addOrderSecond(int seqOrder, String id,
            String cashierID, double cost, Timestamp beginEat, Timestamp end) {
        return false;
    }

    public boolean addOrderDetailById(int seqOrder, int seqFood, int quantity) {
        boolean check = false;
        try {
            String sql = "insert into OrderDetail (OrderID, FoodID, Status, Quantity) values (?, ?, ?, ?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            preStm.setInt(2, seqFood);
            preStm.setBoolean(3, true);
            preStm.setInt(4, quantity);
            if (preStm.executeUpdate() > 0) {
                check = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public List<OrderDTO> loadOrders() {
        List<OrderDTO> result = null;
        try {
            String sql = "select SEQ, TableID from [Order] where EndTime is null";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<OrderDTO>();
            while (rs.next()) {
                String seq = rs.getString("SEQ");
                String tableID = rs.getString("TableID");
                result.add(new OrderDTO(Integer.parseInt(seq), tableID));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
    public OrderDTO loadOrderInfo(int seq) {    
        OrderDTO dto = null;
        try {
            String sql = "select s.Id as 'WaiterID', t.Id as 'TableID', o.BeginTime"
                    + " from [Order] o, [Table] t, Staff s"
                    + " where o.TableID = t.SEQ and o.WaiterID = s.SEQ and o.SEQ = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seq);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String seqTable = rs.getString("TableID");
                String seqWaiter = rs.getString("WaiterID");
                Timestamp beginTime = rs.getTimestamp("BeginTime");
                dto = new OrderDTO(seq, seqTable, seqWaiter, beginTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }
        public List<OrderDTO> showOrderDetail(int seqOrder) {
        List<OrderDTO> result = null;
        try {
            String sql = "select f.ID, f.Name, o.Quantity"
                    + " from OrderDetail o, Food f"
                    + " where o.FoodID = f.SEQ and o.OrderID = ? and o.CookID is null";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            rs = preStm.executeQuery();
            result = new ArrayList<OrderDTO>();
            while (rs.next()) {
                String foodID = rs.getString("ID");
                String foodName = rs.getString("Name");
                int quantity = rs.getInt("Quantity");
                result.add(new OrderDTO(foodID, foodName, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
        
        public boolean insertChefID(int orderSEQ, String foodID, int chefID) {
            boolean check = false;
            try {
                String sql = "update OrderDetail set cookID = ?"
                        + " where OrderID = ? and FoodID = (select SEQ from Food where Id = ?)";
                conn = MyConnection.getConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, chefID);
                preStm.setInt(2, orderSEQ);
                preStm.setString(3, foodID);
                if (preStm.executeUpdate() > 0)
                    check = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
            return check;
        }


}
