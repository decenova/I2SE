/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.controllers;

import beans.TrungBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                    foodID = listOrder.get(i).getFoodWaiting().get(j).getFoodID();
                    String choice = orderSEQ + foodID;
                    System.out.println(choice);
                    System.out.println(listOrder.get(i).getFoodWaiting().size());
                    try {
                        if (request.getParameter(choice) != null) {
                            dao.insertDelivered(orderSEQ, foodID); //set true nghia la gui r
                            
                            System.out.println("id: " + listOrder.get(i));
                            System.out.println("value: " + request.getParameter(listOrder.get(i).getSeq()+""));
                            System.out.println("seq: " + listOrder.get(i).getSeq());
                            if (request.getParameter(listOrder.get(i).getSeq()+"").length() == 0) {
                                dao.setBeginEatTime(listOrder.get(i).getSeq());
                                TrungBean bean = new TrungBean();
                                int tableId = Integer.parseInt(request.getParameter(listOrder.get(i).getSeq() + "tableId"));
                                HttpSession session = request.getSession();
                                
                                System.out.println("table SEQ: " + tableId);
//                                System.out.println("staff id: " + (String)session.getAttribute("STAFFID"));
                                System.out.println("------Chuan bi change--------");
                                
                                System.out.println("table id: " + dao.getIdTableBySEQ(tableId));
                                System.out.println("staff id: " + (String)session.getAttribute("STAFFID"));
                                
                                bean.changeTableStatus(dao.getIdTableBySEQ(tableId), 2, (String)session.getAttribute("STAFFID"));
                            }
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
