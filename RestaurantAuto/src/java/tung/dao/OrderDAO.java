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
}
