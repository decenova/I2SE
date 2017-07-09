/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duc Trung
 */
public class MainController extends HttpServlet {

    private final String error = "error.jsp";
    private final String login = "LoginController";
    private final String staffManager = "staffManager.jsp";
    private final String foodManager = "foodManager.jsp";
    private final String tableManager = "tableManager.jsp";
    private final String insertPage = "insert.jsp";
    private final String search = "SearchController";
    private final String update = "UpdateController";
    private final String insert = "InsertController";
    private final String createOrder = "CreateOrderController";
    private final String submitOrder = "SubmitOrderController";

    private final String showOrder = "ShowOrderController";

    private final String logout = "LogoutController";

    
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
        String url = error;
        try {
            String action = request.getParameter("action");
            System.out.println(action);
            if (action.equals("Login")) {
                url = login;
            } else if (action.equals("Update Staff")){ 
                url = staffManager;
            } else if(action.equals("Update Food")){
                url = foodManager;
            } else if(action.equals("Update Table")){
                url = tableManager;
            } else if (action.equals("Search")){
                url = search;
            } else if (action.equals("Update") || action.equals("Edit")){
                url = update;
            } else if (action.equals("Insert")){
                url = insert;
            } else if (action.equals("InsertPage")){
                String flag = request.getParameter("txtFlag");
                request.setAttribute("txtFlag", flag);
                url = insertPage;
            } else if (action.equals("Create order as Waiter")) {
                url = createOrder;
            } else if (action.equals("Add") || action.equals("Submit order") || action.equals("Add order")) {
                url = submitOrder;

            } else if (action.equals("Show Order")) {
                url = showOrder;         

            } else if (action.endsWith("Logout")) {
                url = logout;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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
