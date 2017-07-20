/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.dao;

import connection.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Decen
 */
public class AnalyzeDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    private void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int revenue(Date from, Date to) {
        int res = 0;
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select sum(Cost) AS  'Total Cost' from [Order] "
                    + "where BeginTime between ? and ?");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt("Total Cost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public int revenue(long from, long to) {
        int res = 0;
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select sum(Cost) AS  'Total Cost' from [Order] "
                    + "where BeginTime between ? and ?");
            ps.setTimestamp(1, new Timestamp(from));
            ps.setTimestamp(2, new Timestamp(to));
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getInt("Total Cost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public ArrayList<ArrayList<Timestamp>> timeAnalyze(long from, long to) {
        ArrayList<ArrayList<Timestamp>> list = new ArrayList<ArrayList<Timestamp>>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select BeginTime, BeginEatTime, EndTime from [Order] "
                    + "where(BeginTime between ? and ?) and (BeginEatTime is not null) and (EndTime is not null)");
            ps.setTimestamp(1, new Timestamp(from));
            ps.setTimestamp(2, new Timestamp(to));
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<Timestamp> tmp = new ArrayList<Timestamp>();
                tmp.add(rs.getTimestamp(1));
                tmp.add(rs.getTimestamp(2));
                tmp.add(rs.getTimestamp(3));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ArrayList> foodAnalyze(Date from, Date to) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity', SUM(Food.Cost) as 'Total Cost' "
                    + "from [Order],OrderDetail,Food "
                    + "where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ and ([Order].BeginTime between ? and ?) "
                    + "group by FoodId, Food.Name "
                    + "order by SUM(Food.Cost) desc, SUM(OrderDetail.Quantity) desc;");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getString(1));
                tmp.add(rs.getInt(2));
                tmp.add(rs.getDouble(3));
                list.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<ArrayList> waiterAnalyze(Date from, Date to) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select st.Id, st.FristName, st.LastName, COUNT([Order].SEQ) AS 'Number Of Order'\n"
                    + "from [Order], Staff st\n"
                    + "where [Order].WaiterID = st.SEQ and ([Order].BeginTime between ? and ?)\n"
                    + "group by st.Id, st.FristName, st.LastName");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getString(1));
                tmp.add(rs.getString(2));
                tmp.add(rs.getString(3));
                tmp.add(rs.getInt(4));
                list.add(tmp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<ArrayList> cookAnalyze(Date from, Date to) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select s.Id, s.FristName, s.LastName, count(o.SEQ)\n"
                    + "from [Order],OrderDetail o, Staff s\n"
                    + "where [Order].SEQ = o.OrderID and o.CookID = s.SEQ\n"
                    + "	and ([Order].BeginTime between ? and ?)\n"
                    + "group by s.Id, s.FristName, s.LastName");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getString(1));
                tmp.add(rs.getString(2));
                tmp.add(rs.getString(3));
                tmp.add(rs.getInt(4));
                list.add(tmp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<ArrayList> busboyAnalyze(Date from, Date to) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select st.SEQ, st.FristName, st.LastName, count(tb.SEQ)\n"
                    + "from ChangeStatusTable tb,Staff st\n"
                    + "where tb.StaffID = st.SEQ and (tb.ChangeTime between ? and ? )\n"
                    + "		and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')\n"
                    + "group by st.SEQ, FristName, LastName");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getString(1));
                tmp.add(rs.getString(2));
                tmp.add(rs.getString(3));
                tmp.add(rs.getInt(4));
                list.add(tmp);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<ArrayList> hostAnalyze(Date from, Date to) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            con = MyConnection.getConnection();
            ps = con.prepareStatement("select st.SEQ, st.FristName, st.LastName, count(tb.SEQ)\n"
                    + "from ChangeStatusTable tb,Staff st\n"
                    + "where tb.StaffID = st.SEQ and (tb.ChangeTime between ? and ? )\n"
                    + "		and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Waiting')\n"
                    + "group by st.SEQ, FristName, LastName");
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getString(1));
                tmp.add(rs.getString(2));
                tmp.add(rs.getString(3));
                tmp.add(rs.getInt(4));
                list.add(tmp);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
