/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trung.dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Duc Trung
 */
public class RestaurantDAO {
    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;
    
    private void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String checkLogin(String id, String password) {
        String result = "false";
        try {
            conn = MyConnection.getConnection();
            String sql = "Select StaffRole.StaffRole from Staff INNER JOIN StaffRole ON Staff.SEQ=StaffRole.SEQ Where Staff.Id='" + id + "' and Staff.Password='" + password + "'";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = rs.getString("StaffRole");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }
}
