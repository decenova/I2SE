/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thang.dao.AnalyzeDAO;

/**
 *
 * @author Decen
 */
public class analyzeRevenueController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final long DAYTIME = 3600000 * 24;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from = new Date(sdf.parse(request.getParameter("fromDate")).getTime());
            Date to = new Date(sdf.parse(request.getParameter("toDate")).getTime());
            AnalyzeDAO dao = new AnalyzeDAO();
            int revenue = dao.revenue(from, to);
            request.setAttribute("REVENUE", revenue);
            request.setAttribute("FROM", from);
            request.setAttribute("TO", to);
            
            long fromValue = from.getTime();
            long toValue = to.getTime();
            long subTime = (toValue - fromValue);
            ArrayList<Integer> revenueList = new ArrayList<Integer>();
            int rank;
            int max = 5;
            int cur = 0;
            if (subTime > DAYTIME * 366) {
                rank = (int) Math.floor(subTime * 1.0 / (DAYTIME * 366));
                for (int i = 1; i <= rank; i++) {
                    cur = dao.revenue(fromValue, fromValue + DAYTIME * 366 - 1);
                    revenueList.add(cur);
                    if (cur > max) {
                        max = cur;
                    }
                    fromValue += DAYTIME * 366;
                }
                revenueList.add(dao.revenue(fromValue, toValue));
            } else if (subTime > DAYTIME) {
                rank = (int) Math.floor(subTime * 1.0 / DAYTIME);
                for (int i = 1; i <= rank; i++) {
                    cur = (dao.revenue(fromValue, fromValue + DAYTIME - 1));
                    revenueList.add(cur);
                    if (cur > max) {
                        max = cur;
                    }
                    fromValue += DAYTIME;
                }
                revenueList.add(dao.revenue(fromValue, toValue));
            } else {
                rank = (int) Math.floor(subTime * 1.0 / (DAYTIME / 24));
                for (int i = 1; i <= rank; i++) {
                    cur = (dao.revenue(fromValue, fromValue + DAYTIME / 24 - 1));
                    revenueList.add(cur);
                    if (cur > max) {
                        max = cur;
                    }
                    fromValue += DAYTIME / 24;
                }
                revenueList.add(dao.revenue(fromValue, toValue));
            }
            request.setAttribute("REVENUELIST", revenueList);
            request.setAttribute("MAX", max + 10);
            request.setAttribute("RANK", rank + 1);
            ArrayList<ArrayList> listFood = dao.foodAnalyze(from, to);
            int sum = 0;
            for (ArrayList arrayList : listFood) {
                sum +=(Double) arrayList.get(2);
            }
            request.setAttribute("SUM", sum);
            request.setAttribute("FOOD", listFood);
            
            ArrayList<ArrayList<Timestamp>> list = dao.timeAnalyze(from.getTime(), to.getTime());
            long averageServerLong = 0;
            long averageSpendLong = 0;
            for (ArrayList<Timestamp> arrayList : list) {
                averageServerLong += arrayList.get(1).getTime() -  arrayList.get(0).getTime();
                averageSpendLong += arrayList.get(2).getTime() -  arrayList.get(0).getTime();
            }
            Time averageServerTime = new Time(averageServerLong / list.size() - 7 * 3600000);
            Time averageSpendTime = new Time(averageSpendLong / list.size() - 7 * 3600000);
            request.setAttribute("SERVER", averageServerTime);
            request.setAttribute("SPEND", averageSpendTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("analyzeRevenue.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
