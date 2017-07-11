/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.controllers;

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
public class FoodDeliveredController extends HttpServlet {

    private final String viewWaitingFood = "viewWaitingFood.jsp";

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
        String url = "";
        try {
            System.out.println("kjashdkjashdjk");
            int orderSEQ = 0;
            String foodID = "";
            OrderDAO dao = new OrderDAO();
            List<OrderDTO> listOrder = dao.loadOrders();
            for (int i = 0; i < listOrder.size(); i++) {
                List<OrderDTO> list = dao.loadWaitingFood(listOrder.get(i).getSeq());
                listOrder.get(i).setFoodWaiting(list);
            }
            for (int i = 0; i < listOrder.size(); i++) {
                for (int j = 0; j < listOrder.get(i).getFoodWaiting().size(); j++) {
                    orderSEQ = listOrder.get(i).getSeq();
                    System.out.println("aaaaaaaa" + orderSEQ);
                    foodID = listOrder.get(i).getFoodWaiting().get(j).getFoodID();
                    System.out.println("bbbbbbb" + foodID);
                    String choice = orderSEQ + foodID;
                    System.out.println(choice);
                    System.out.println(listOrder.get(i).getFoodWaiting().size());
                    try {
                        if (!request.getParameter(choice).equals(null)) {
                            dao.insertDelivered(orderSEQ, foodID);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            for (int i = 0; i < listOrder.size(); i++) {
                List<OrderDTO> list = dao.loadWaitingFood(listOrder.get(i).getSeq());
                listOrder.get(i).setFoodWaiting(list);
            }
//            listOrder = dao.loadOrders();
            request.setAttribute("foodWaitingList", listOrder);
            url = viewWaitingFood;

        } catch (Exception e) {
            log("ERROR at FoodDeliveredController: " + e.getMessage());
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
