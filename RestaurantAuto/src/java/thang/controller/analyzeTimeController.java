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
public class analyzeTimeController extends HttpServlet {

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
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date from = new Date(sdf.parse(request.getParameter("fromDate")).getTime());
            Date to = new Date(sdf.parse(request.getParameter("toDate")).getTime());
            AnalyzeDAO dao = new AnalyzeDAO();
            ArrayList<ArrayList<Timestamp>> list = dao.timeAnalyze(from.getTime(), to.getTime());
            long averageServerLong = 0;
            long averageSpendLong = 0;
            for (ArrayList<Timestamp> arrayList : list) {
                averageServerLong += arrayList.get(1).getTime() -  arrayList.get(0).getTime();
                averageSpendLong += arrayList.get(2).getTime() -  arrayList.get(0).getTime();
            }
            Time averageServerTime = new Time(averageServerLong / list.size() - 7 * 3600000);
            Time averageSpendTime = new Time(averageSpendLong / list.size() - 7 * 3600000);
            request.setAttribute("FROM", from);
            request.setAttribute("TO", to);
            request.setAttribute("SERVER", averageServerTime);
            request.setAttribute("SPEND", averageSpendTime);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("analyzeTime.jsp").forward(request, response);
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
