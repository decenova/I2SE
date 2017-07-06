/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ChooseFoodController extends HttpServlet {

    private final String viewFood = "viewFoods.jsp";
    private final String cookFood = "cookFood.jsp";
    private final String viewCookFood = "ViewCookFoodController";

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
        String url = "";
        try {
            String chefID = request.getParameter("staffID");
            OrderDAO dao = new OrderDAO();
            List<OrderDTO> listOrder = dao.loadOrders();
            int orderSEQ = 0;
            String foodID = "";
            for (int i = 0; i < listOrder.size(); i++) {
                List<OrderDTO> list = dao.showOrderDetail(listOrder.get(i).getSeq());
                listOrder.get(i).setFoodDetails(list);
            }
            int seqStaff = 0;
            for (int i = 0; i < listOrder.size(); i++) {
                for (int j = 0; j < listOrder.get(i).getFoodDetails().size(); j++) {
                    orderSEQ = listOrder.get(i).getSeq();
                    foodID = listOrder.get(i).getFoodDetails().get(j).getFoodID();
                    String choice = orderSEQ + foodID;
                    try {
                        if (!request.getParameter(choice).equals(null)) {
                            seqStaff = dao.getSEQStaffById(chefID);
                            System.out.println("tung: " + seqStaff);
                            if (dao.insertChefID(orderSEQ, foodID, seqStaff)) {
                                System.out.println("success");
                            } else {
                                System.out.println("false");
                            }
                        }
                            
                    } catch (Exception e) {
                    }
                }
            }     
            url = viewCookFood;
        } catch (Exception e) {
            log("ERROR at ChooseFoodController: " + e.getMessage());
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
