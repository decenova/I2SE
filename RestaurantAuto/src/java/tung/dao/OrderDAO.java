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
import java.time.LocalDateTime;
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
    
    public String getIdTableBySEQ(int seq) {
        String id = "";
        try {
            String sql = "select Id from [Table] where SEQ = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seq);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return id;
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


    public boolean addOrderDetailById(int seqOrder, int seqFood, int quantity) {
        boolean check = false;
        try {
            String sql = "insert into OrderDetail (OrderID, FoodID, Status, Quantity, Delivered) values (?, ?, ?, ?, ?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            preStm.setInt(2, seqFood);
            preStm.setBoolean(3, false);
            preStm.setInt(4, quantity);
            preStm.setBoolean(5, false);
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

    public List<OrderDTO> loadOrders() { //lay tat ca order dang hoat dong
        List<OrderDTO> result = null;
        try {
            String sql = "select SEQ, TableID, BeginEatTime from [Order] where EndTime is null";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<OrderDTO>();
            String seq;
            String tableID;
            Timestamp beginEatTime;
            while (rs.next()) {
                seq = rs.getString("SEQ");
                tableID = rs.getString("TableID");
                beginEatTime = rs.getTimestamp("BeginEatTime");
                result.add(new OrderDTO(Integer.parseInt(seq), tableID, beginEatTime));
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

    public List<OrderDTO> showOrderDetail(int seqOrder, String isCookID) { //show order detail cho moi order
        List<OrderDTO> result = null;
        try {
            String sql = "select f.ID, f.Name, o.Quantity"
                    + " from OrderDetail o, Food f"
                    + " where o.FoodID = f.SEQ and o.OrderID = ? and o.CookID is " + isCookID + " and o.Status = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            preStm.setBoolean(2, false);
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

    public List<OrderDTO> showChooseFood(int seqOrder, int seqStaff) { //thang chef chon mon nau
        List<OrderDTO> result = null;
        try {
            String sql = "select f.ID, f.Name, o.Quantity"
                    + " from OrderDetail o, Food f"
                    + " where o.FoodID = f.SEQ and o.OrderID = ? and o.CookID = ? and o.Status = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            preStm.setInt(2, seqStaff);
            preStm.setBoolean(3, false);
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

    public boolean checkFoodDone(int seqOrder, String foodID) { //thang chef chon mon nau xong
        boolean check = false;
        try {
            String sql = "update OrderDetail set Status = ?"
                    + " where OrderID = ? and FoodID = (select SEQ from Food where Id = ?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setInt(2, seqOrder);
            preStm.setString(3, foodID);
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

    public List<OrderDTO> loadWaitingFood(int seqOrder) { //bang mon an cho don len cho khach
        List<OrderDTO> result = null;
        try {
            String sql = "select f.ID, f.Name, o.Quantity"
                    + " from OrderDetail o, Food f"
                    + " where o.FoodID = f.SEQ and o.OrderID = ? and o.Status = ? and o.CookID is not null and o.Delivered = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            preStm.setBoolean(2, true);
            preStm.setBoolean(3, false);
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
    
    public boolean insertDelivered(int seqOrder, String foodID) {
         boolean check = false;
        try {
            String sql = "update OrderDetail set Delivered = ?"
                    + " where OrderID = ? and FoodID = (select SEQ from Food where Id = ?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setInt(2, seqOrder);
            preStm.setString(3, foodID);
            if (preStm.executeUpdate() > 0) {
                check = true;
            }
            
            sql = "Select BeginEatTime From [Order] Where OrderID = ?";
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
        
    }
    
    public int checkTableUsing(int seqTable) {
        int seqOrder = 0;
        try {
            String sql = "select SEQ from [Order] where tableID = ? and EndTime is null";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqTable);
            rs = preStm.executeQuery();
            if (rs.next())
                seqOrder = rs.getInt("SEQ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return seqOrder;
    }
    
    public boolean deleteOrderDetail(int seqOrder) {
        boolean check = false;
        try {
            String sql = "delete from OrderDetail where OrderID = ? and CookID is null";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, seqOrder);
            if (preStm.executeUpdate() > 0)
                check = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public void setBeginEatTime(int orderSEQ) {
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE [Order] SET BeginEatTime = ? Where SEQ = ?";
            preStm = conn.prepareStatement(sql);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            preStm.setTimestamp(1, ts);
            preStm.setInt(2, orderSEQ);
            preStm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
