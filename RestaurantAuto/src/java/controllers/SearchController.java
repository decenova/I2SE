/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhnh.dao.MinhRestaurantDAO;
import minhnh.dto.FoodDTO;
import minhnh.dto.StaffDTO;
import trung.dto.TableDTO;

/**
 *
 * @author kubin
 */
public class SearchController extends HttpServlet {
    private final static String staffManager = "staffManager.jsp";
    private final static String foodManager = "foodManager.jsp";
    private final static String tableManager = "tableManager.jsp";
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
            String searchValue = request.getParameter("txtSearch");
            String flag = request.getParameter("txtFlag");
            MinhRestaurantDAO dao = new MinhRestaurantDAO();
            if (flag.equals("Staff")){
                List<StaffDTO> dto = dao.findStaffByName(searchValue);
                request.setAttribute("STAFFINFO", dto);
                request.setAttribute("lastSearchValue", searchValue);
                url = staffManager;
            } else if (flag.equals("Food")){
                List<FoodDTO> dto = dao.findFoodByName(searchValue);
                request.setAttribute("FOODINFO", dto);
                request.setAttribute("lastSearchValue", searchValue);
                url = foodManager;
            } else if (flag.equals("Table")){
                List<TableDTO> dto = dao.findTableByStatus(searchValue);
                request.setAttribute("TABLEINFO", dto);
                request.setAttribute("lastSearchValue", searchValue);
                url = tableManager;
            }
        } catch (Exception e) {
            log("ERROR at SearchController" + e.getMessage());
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
