package minhnh.dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import minhnh.dto.FoodDTO;
import minhnh.dto.OrderDTO;
import minhnh.dto.OrderDetailDTO;
import minhnh.dto.StaffDTO;
import minhnh.dto.TableDTO;

public class MinhRestaurantDAO {

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
    
    public boolean printBill(Timestamp endTime,int orderId){
        try {
            conn = MyConnection.getConnection();
            String sql = "update [Order] set EndTime=? where SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setTimestamp(1, endTime);
            pre.setInt(2, orderId);
            int n = pre.executeUpdate();
            if (n > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public List<OrderDetailDTO> viewDetailBill(int orderId){
        List<OrderDetailDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Food.[Name], Quantity, Food.Cost " 
                    + "from OrderDetail JOIN Food on Food.SEQ = OrderDetail.FoodId " 
                    + "where OrderDetail.OrderID = (select SEQ from [Order] where [Order].SEQ = ?)";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            rs = pre.executeQuery();
            result = new ArrayList<OrderDetailDTO>();
            while (rs.next()){
                String name = rs.getString("Name");
                int quan = rs.getInt("Quantity");
                int cost = rs.getInt("Cost");
                OrderDetailDTO dto = new OrderDetailDTO(name, quan, cost);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<OrderDTO> viewAllBill(){
        List<OrderDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Order].SEQ, TableId, Staff.LastName, Cost " 
                    + "from [Order] join Staff on Staff.SEQ = [Order].WaiterID " 
                    + "where EndTime IS NULL";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<OrderDTO>();
            while (rs.next()){
                String id = rs.getString("SEQ");
                int tableId = rs.getInt("TableId");
                String waiName = rs.getString("LastName");
                int cost = rs.getInt("Cost");
                OrderDTO dto = new OrderDTO(id, tableId, waiName, cost);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    } 

    public String findNameByPk(String pk){
        String name = "";
        try {
            conn = MyConnection.getConnection();
            String sql = "select LastName from Staff where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, pk);
            rs = pre.executeQuery();
            if (rs.next()){
                name = rs.getString("LastName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return name;
    }
    
    //Table 
    public boolean insertTable(String tableID,int statusId) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into [Table](Id,TableStatusID) "
                    + "values(?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, tableID);
            pre.setInt(2, statusId);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateTable(String tableId, int statusId) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update [Table] set TableStatusID=? where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, statusId);
            pre.setString(2, tableId);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public TableDTO findTableById(String tableId) {
        TableDTO result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Table].Id as TableID, TableStatus.TableStatus as Status from [Table] "
                    + "JOIN TableStatus ON [Table].TableStatusID=TableStatus.SEQ "
                    + "where id = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, tableId);
            rs = pre.executeQuery();
            if (rs.next()) {
                String id = rs.getString("TableID");
                String status = rs.getString("Status");
                result = new TableDTO(id, status);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<TableDTO> findTableByStatus(String search) {
        List<TableDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Table].Id as TableID, TableStatus.TableStatus as Status from [Table] "
                    + "JOIN TableStatus ON [Table].TableStatusID=TableStatus.SEQ "
                    + "where TableStatusID = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, search);
            rs = pre.executeQuery();
            result = new ArrayList<TableDTO>();
            while (rs.next()) {
                String id = rs.getString("TableID");
                String status = rs.getString("Status");
                TableDTO dto = new TableDTO(id, status);
                result.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<TableDTO> getTableList() {
        List<TableDTO> result = new ArrayList<TableDTO>();
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Table].Id as TableID, TableStatus.TableStatus as Status from [Table] "
                    + "JOIN TableStatus ON [Table].TableStatusID=TableStatus.SEQ";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String tableID = rs.getString("TableID");
                String tableStatus = rs.getString("Status");
                TableDTO dto = new TableDTO(tableID, tableStatus);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    //Food
    public boolean insertFood(String foodID, String foodName, int cost,
            boolean avai, int typeId) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into Food(Id,Name,FoodTypeId,Cost,Available) "
                    + "values(?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, foodID);
            pre.setString(2, foodName);
            pre.setInt(3, typeId);
            pre.setInt(4, cost);
            pre.setBoolean(5, avai);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateFood(String foodID, String foodName, int cost,
            boolean available, int type) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update Food set [Name]=?, FoodTypeID=?, Cost=?, Available=? where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, foodName);
            pre.setInt(2, type);
            pre.setInt(3, cost);
            pre.setBoolean(4, available);
            pre.setString(5, foodID);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public FoodDTO findFoodById(String foodId) {
        FoodDTO result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Name], Cost, Available, FoodType.FoodType"
                    + " as Type from Food join FoodType "
                    + "on FoodType.SEQ=Food.SEQ where Id = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, foodId);
            rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                int cost = rs.getInt("Cost");
                boolean available = rs.getBoolean("Available");
                String type = rs.getString("Type");
                result = new FoodDTO(foodId, name, type, cost, available);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> findFoodByName(String search) {
        List<FoodDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Id, [Name], Cost, Available, FoodType.FoodType"
                    + " as Type from Food join FoodType "
                    + "on FoodType.SEQ=Food.SEQ where Name LIKE ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + search + "%");
            rs = pre.executeQuery();
            result = new ArrayList<FoodDTO>();
            while (rs.next()) {
                String id = rs.getString("Id");
                String name = rs.getString("Name");
                int cost = rs.getInt("Cost");
                boolean available = rs.getBoolean("Available");
                String type = rs.getString("Type");
                FoodDTO dto = new FoodDTO(id, name, type, cost, available);
                result.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> getMostEatFood() {
        List<FoodDTO> result = new ArrayList<FoodDTO>();
        try {
            conn = MyConnection.getConnection();
            String sql = "select top 10 Food.Name as Name,FoodType.FoodType as Type,SUM(OrderDetail.Quantity) as 'TotalQuantity' "
                    + "from OrderDetail,Food JOIN FoodType on Food.FoodTypeID=FoodType.SEQ "
                    + "where OrderDetail.FoodId = Food.SEQ "
                    + "group by FoodId, Food.Name,FoodType.FoodType "
                    + "order by SUM(OrderDetail.Quantity) desc";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String foodName = rs.getString("Name");
                String foofType = rs.getString("Type");
                int quantity = rs.getInt("TotalQuantity");
                FoodDTO dto = new FoodDTO(foodName, foofType, quantity);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    //Staff
    public boolean insertStaff(String staffID, String password, String firstName,
            String lastName, Timestamp DOB, String addr, String sex, int Salary,
            boolean avai, int role) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Insert into Staff(Id,[Password],FristName,LastName,DOB,"
                    + "Addr,Sex,Salary,Available,StaffRoleID) "
                    + "values(?,?,?,?,?,?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, staffID);
            pre.setString(2, password);
            pre.setString(3, firstName);
            pre.setString(4, lastName);
            pre.setTimestamp(5, DOB);
            pre.setString(6, addr);
            pre.setString(7, sex);
            pre.setInt(8, Salary);
            pre.setBoolean(9, avai);
            pre.setInt(10, role);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

//    public boolean deleteStaff(String staffID){
//        boolean result = false;
//        try {
//            conn = MyConnection.getConnection();
//            String sql = "delete from Staff where Id=?";
//            pre = conn.prepareStatement(sql);
//            pre.setString(1, staffID);
//            int n = pre.executeUpdate();
//            if (n > 0){
//                result = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            closeConnection();
//        }
//        return result;
//    }
    public boolean updateStaff(String staffID, String password, String firstName,
            String lastName, Timestamp DOB, String addr, String sex, int Salary,
            boolean avai, int role) {
        boolean result = false;
        try {
            conn = MyConnection.getConnection();
            String sql = "Update Staff set [Password]=?, FristName=?, LastName=?, "
                    + "DOB=?, Addr=?, Sex=?, Salary=?, Available=?, "
                    + "StaffRoleID=? where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, password);
            pre.setString(2, firstName);
            pre.setString(3, lastName);
            pre.setTimestamp(4, DOB);
            pre.setString(5, addr);
            pre.setString(6, sex);
            pre.setInt(7, Salary);
            pre.setBoolean(8, avai);
            pre.setInt(9, role);
            pre.setString(10, staffID);
            int n = pre.executeUpdate();
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public StaffDTO findStaffById(String staffId) {
        StaffDTO result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Password],FristName,LastName,DOB,Addr,Sex,Salary,"
                    + "StaffRole.StaffRole as Role from Staff join StaffRole "
                    + "on Staff.StaffRoleID=StaffRole.SEQ "
                    + "where Available = 'true' and Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, staffId);
            rs = pre.executeQuery();
            if (rs.next()) {
                String password = rs.getString("Password");
                String firstName = rs.getString("FristName");
                String lastName = rs.getString("LastName");
                Timestamp DOB = rs.getTimestamp("DOB");
                String addr = rs.getString("Addr");
                String sex = rs.getString("Sex");
                int salary = rs.getInt("Salary");
                String role = rs.getString("Role");
                result = new StaffDTO(staffId, password, firstName, lastName, DOB, sex, addr, salary, role, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<StaffDTO> findStaffByName(String search) {
        List<StaffDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Id,FristName,LastName,Addr,Sex,Salary,StaffRole.StaffRole as Role "
                    + "from Staff join StaffRole on Staff.StaffRoleID=StaffRole.SEQ "
                    + "where Available = 'true' and FristName LIKE ? or LastName LIKE ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + search + "%");
            pre.setString(2, "%" + search + "%");
            rs = pre.executeQuery();
            result = new ArrayList<StaffDTO>();
            while (rs.next()) {
                String staffId = rs.getString("Id");
                String firstName = rs.getString("FristName");
                String lastName = rs.getString("LastName");
                String addr = rs.getString("Addr");
                String sex = rs.getString("Sex");
                int salary = rs.getInt("Salary");
                String role = rs.getString("Role");
                StaffDTO dto = new StaffDTO(staffId, firstName, lastName, sex, addr, salary, role, true);
                result.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<StaffDTO> getStaffAvailable() {
        List<StaffDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select FristName,LastName,StaffRole.StaffRole as Role "
                    + "from Staff join StaffRole on Staff.StaffRoleID=StaffRole.SEQ "
                    + "where Available = 'true'";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<StaffDTO>();
            while (rs.next()) {
                String firstName = rs.getString("FristName");
                String lastName = rs.getString("LastName");
                String role = rs.getString("Role");
                StaffDTO dto = new StaffDTO(firstName, lastName, role, true);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

}
