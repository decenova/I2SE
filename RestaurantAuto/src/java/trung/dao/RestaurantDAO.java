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
import java.sql.Timestamp;
import java.util.ArrayList;
import trung.dto.TableDTO;

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
            String sql = "Select StaffRole.StaffRole from Staff INNER JOIN StaffRole ON Staff.StaffRoleID=StaffRole.SEQ Where Staff.Id='" + id + "' and Staff.Password='" + password + "'";
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
    
    public ArrayList<TableDTO> getTablesStatus() {
        ArrayList<TableDTO> tableDTO = new ArrayList<TableDTO>();
        
        try {
            conn = MyConnection.getConnection();
            String sql = "Select Id,TableStatus from [Table] INNER JOIN TableStatus ON [Table].TableStatusID=TableStatus.SEQ";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String status = rs.getString("TableStatus");
                tableDTO.add(new TableDTO(id, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return tableDTO;
    }
    
//    public void changeTableStatus(String tableId, int tableStatusId, String staffId) {
//        try {
//            conn = MyConnection.getConnection();
//            tableStatusId++;
//            if (tableStatusId > 4) tableStatusId = 1;
//            String sql = "UPDATE [Table] SET TableStatusID='" + tableStatusId + "' WHERE Id='" + tableId + "'";
//            pre = conn.prepareStatement(sql);
//            pre.execute();
//            
//            Timestamp time = new Timestamp(System.currentTimeMillis());
//            sql = "INSERT INTO ChangeStatusTable (TableID, StaffID, ChangeTime, TableStatusID) VALUES ((SELECT SEQ FROM [table] where Id = '" + tableId + "'), (SELECT SEQ FROM Staff where Id = '" + staffId + "'), '" + time + "', " + tableStatusId + ")";
//            pre = conn.prepareStatement(sql);
//            pre.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//    }
    
    public void changeTableStatus(String tableId, int tableStatusId, String staffId) {
        try {
//            System.out.println("zoooooooooooooooooo");
            String oldTableId = "";
            int oldTableStatusID = -1;
            
            conn = MyConnection.getConnection();
            tableStatusId++;
            if (tableStatusId > 4) tableStatusId = 1;
            
            //kiem tra lieu nguoi dung co load lai trang va tra ve request cu
            String sql = "Select Id,TableStatusID from [Table] WHERE Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, tableId);
            rs = pre.executeQuery();
            if (rs.next()) {
                oldTableId = rs.getString("Id");
                oldTableStatusID = Integer.parseInt(rs.getString("TableStatusID"));
            }
            
            //neu tra ve request cu thi khong update
//            System.out.println("oldId: " + oldTableId);
//            System.out.println("   Id: " + tableId);
//            System.out.println("oldST: " + oldTableStatusID);
//            System.out.println("   ST: " + tableStatusId);
            if (!tableId.equals(oldTableId) || oldTableStatusID != tableStatusId) {
//                System.out.println("Update du lieu");
                //update du lieu
                sql = "UPDATE [Table] SET TableStatusID=? WHERE Id=?";
                pre = conn.prepareStatement(sql);
                pre.setString(1, "" + tableStatusId);
                pre.setString(2, tableId);
                pre.execute();

                //them vao changeTableStatus
                Timestamp time = new Timestamp(System.currentTimeMillis());
                sql = "INSERT INTO ChangeStatusTable (TableID, StaffID, ChangeTime, TableStatusID) VALUES ((SELECT SEQ FROM [table] where Id = '" + tableId + "'), (SELECT SEQ FROM Staff where Id = '" + staffId + "'), '" + time + "', " + tableStatusId + ")";
                pre = conn.prepareStatement(sql);
                pre.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
