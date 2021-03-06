/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tung.dao.OrderDAO;
import tung.dto.OrderDTO;

/**
 *
 * @author hoanh
 */
public class LoadFoodsController extends HttpServlet {
    private final String viewFood = "viewFoods.jsp";
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
        response.setContentType("application/json;charset=UTF-8");
         PrintWriter out = response.getWriter();
         String url = "";
        try {
            OrderDAO dao = new OrderDAO();
            List<OrderDTO> listOrder = dao.loadOrders();
            for (int i = 0; i < listOrder.size(); i++) {
                List<OrderDTO> list = dao.showOrderDetail(listOrder.get(i).getSeq(), "null");
                listOrder.get(i).setFoodDetails(list);
                System.out.println(listOrder.get(i).getSeqOD());
            }
            String array = new Gson().toJson(listOrder);
            out.print(array);
//            request.setAttribute("orderList", listOrder);
//           url = viewFood;
        } catch(Exception e) {
            log("ERROR at ChooseFoodController: " + e.getMessage());
        } finally {
//           request.getRequestDispatcher(url).forward(request, response);
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
