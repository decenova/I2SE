/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhnh.dao.MinhRestaurantDAO;
import trung.dao.RestaurantDAO;

/**
 *
 * @author kubin
 */
public class PrintBillController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String url = "error.jsp";
        try {
            
            
            MinhRestaurantDAO dao = new MinhRestaurantDAO();
            Date date = new Date();
            Timestamp endDate = new Timestamp(date.getTime());
            int total = Integer.parseInt(request.getParameter("total"));
            int seqOrder = Integer.parseInt(request.getParameter("seqOrder"));
      
            
            
            dao.insertTotal(total, seqOrder);
            
            int id = Integer.parseInt(request.getParameter("pk"));
            
            int tableSEQ = Integer.parseInt(request.getParameter("tableId")); 
            
            HttpSession session = request.getSession();
            String staffId = (String)session.getAttribute("STAFFID");
            int casherID = dao.getCasherIDByPk(staffId);
            boolean result = dao.printBill(endDate, id, casherID);

            
            
            RestaurantDAO change = new RestaurantDAO();
            
            String tableId = dao.getTableIDByPk(tableSEQ);
            change.changeTableStatus(tableId, 3, staffId);
            if (result){
                url = "allBill.jsp";
            }
        } catch (Exception e) {
            log("ERROR at ManagerController" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
