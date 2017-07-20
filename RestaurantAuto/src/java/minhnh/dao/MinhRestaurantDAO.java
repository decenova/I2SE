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
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCasherIDByPk(String pk) {
        int staffId = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "select SEQ from Staff where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, pk);
            rs = pre.executeQuery();
            if (rs.next()) {
                staffId = rs.getInt("SEQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return staffId;
    }
    
    public String getTableIDByPk(int pk) {
        String tableId = "";
        try {
            conn = MyConnection.getConnection();
            String sql = "select Id from [Table] where SEQ=?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, pk);
            rs = pre.executeQuery();
            if (rs.next()) {
                tableId = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return tableId;
    }
    
    public int getTableSEQById(String id) {
        int tableSEQ = 0;
        try {
            conn = MyConnection.getConnection();
            String sql = "select SEQ from [Table] where Id = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                tableSEQ = rs.getInt("SEQ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return tableSEQ;
    }

    public boolean printBill(Timestamp endTime, int orderId, int casherID) {
        try {
            conn = MyConnection.getConnection();
            String sql = "update [Order] set EndTime=?, CashierID=? where SEQ = ?";
            pre = conn.prepareStatement(sql);
            pre.setTimestamp(1, endTime);
            pre.setInt(2, casherID);
            pre.setInt(3, orderId);
            int n = pre.executeUpdate();
            if (n > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<OrderDetailDTO> viewDetailBill(int orderId) {
        List<OrderDetailDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select Food.[Name], Quantity, (Food.Cost * Quantity) as Price "
                    + "from OrderDetail JOIN Food on Food.SEQ = OrderDetail.FoodId "
                    + "where OrderDetail.OrderID = (select SEQ from [Order] where [Order].SEQ = ?)";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            rs = pre.executeQuery();
            result = new ArrayList<OrderDetailDTO>();
            String name = "";
            int quan = 0;
            int price = 0;
            while (rs.next()) {
                name = rs.getString("Name");
                quan = rs.getInt("Quantity");
                price = rs.getInt("Price");
                OrderDetailDTO dto = new OrderDetailDTO(name, quan, price);
                result.add(dto);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    public void insertTotal(int cost, int seqOrder) {
        try {
            String sql = "update [Order] set cost = ? where SEQ = ?";
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cost);
            pre.setInt(2, seqOrder);
            pre.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public List<OrderDTO> viewAllBill() {
        List<OrderDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "select [Order].SEQ, TableId, Staff.LastName, Cost "
                    + "from [Order] join Staff on Staff.SEQ = [Order].WaiterID "
                    + "where EndTime IS NULL";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<OrderDTO>();
            while (rs.next()) {
                String id = rs.getString("SEQ");
                int tableId = rs.getInt("TableId");
                String waiName = rs.getString("LastName");
                int cost = rs.getInt("Cost");
                OrderDTO dto = new OrderDTO(id, tableId, waiName, cost);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public String findNameByPk(String pk) {
        String name = "";
        try {
            conn = MyConnection.getConnection();
            String sql = "select LastName from Staff where Id=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, pk);
            rs = pre.executeQuery();
            if (rs.next()) {
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
    public boolean insertTable(String tableID, int statusId) {
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
            String id;
            String status;
            if (rs.next()) {
                id = rs.getString("TableID");
                status = rs.getString("Status");
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
            String id;
            String status;
            while (rs.next()) {
                id = rs.getString("TableID");
                status = rs.getString("Status");
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
            String tableID;
            String tableStatus;
            while (rs.next()) {
                tableID = rs.getString("TableID");
                tableStatus = rs.getString("Status");
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
            String name;
            int cost;
            boolean available;
            String type;
            if (rs.next()) {
                name = rs.getString("Name");
                cost = rs.getInt("Cost");
                available = rs.getBoolean("Available");
                type = rs.getString("Type");
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
            String id;
            String name;
            int cost;
            boolean available;
            String type;
            result = new ArrayList<FoodDTO>();
            while (rs.next()) {
                id = rs.getString("Id");
                name = rs.getString("Name");
                cost = rs.getInt("Cost");
                available = rs.getBoolean("Available");
                type = rs.getString("Type");
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
            String foodName;
            String foofType;
            int quantity;
            while (rs.next()) {
                foodName = rs.getString("Name");
                foofType = rs.getString("Type");
                quantity = rs.getInt("TotalQuantity");
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
            String password;
            String firstName;
            String lastName;
            Timestamp DOB;
            String addr;
            String sex;
            int salary;
            String role;
            if (rs.next()) {
                password = rs.getString("Password");
                firstName = rs.getString("FristName");
                lastName = rs.getString("LastName");
                DOB = rs.getTimestamp("DOB");
                addr = rs.getString("Addr");
                sex = rs.getString("Sex");
                salary = rs.getInt("Salary");
                role = rs.getString("Role");
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
            String staffId;
            String firstName;
            String lastName;
            String addr;
            String sex;
            int salary;
            String role;
            result = new ArrayList<StaffDTO>();
            while (rs.next()) {
                staffId = rs.getString("Id");
                firstName = rs.getString("FristName");
                lastName = rs.getString("LastName");
                addr = rs.getString("Addr");
                sex = rs.getString("Sex");
                salary = rs.getInt("Salary");
                role = rs.getString("Role");
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
            String firstName;
            String lastName;
            String role;
            result = new ArrayList<StaffDTO>();
            while (rs.next()) {
                firstName = rs.getString("FristName");
                lastName = rs.getString("LastName");
                role = rs.getString("Role");
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
    
    public boolean isTableDeliveredAll(String tableId) {
        boolean result = true;
        int tableSEQ = getTableSEQById(tableId);
        System.out.println("table bill seq: " + tableSEQ);
        
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT DISTINCT [Order].TableID FROM OrderDetail INNER JOIN [Order] ON OrderDetail.OrderID = [Order].SEQ WHERE OrderDetail.Delivered = 0 AND [Order].TableID = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, tableSEQ);
            rs = pre.executeQuery();
            if (rs.next()) {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { 
            closeConnection();
        }
        
        return result;
    }

}
