/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tung.dto.OrderDTO;

/**
 *
 * @author hoanh
 */
public class SubmitOrderController extends HttpServlet {

    private static final String menuP = "menu.jsp";
    private static final String errorP = "error.jsp";
    private static final String orderP = "order.jsp";
    private static final String orderListP = "orderList.jsp";

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
        String url = errorP;
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            List<OrderDTO> result = (List<OrderDTO>) session.getAttribute("ORDER");
            String tableID = request.getParameter("tableID");
            if (action.equals("Add")) {
                String id = request.getParameter("txtFoodID");
                String name = request.getParameter("txtFoodName");
                String txtQuantity = request.getParameter("txtQuantity");
                int quantity = 0;
                if (result == null) {
                    result = new ArrayList<OrderDTO>();
                }
                int tmp = 0;
                if (txtQuantity.length() != 0) {
                    quantity = Integer.parseInt(txtQuantity);
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getFoodID().equalsIgnoreCase(id)) {
                            result.get(i).setQuantity(result.get(i).getQuantity() + quantity);
                            tmp++;
                        }
                    }
                    if (tmp == 0) {
                        OrderDTO dto = new OrderDTO(id, name, quantity);
                        result.add(dto);
                    }
                    session.setAttribute("ORDER", result);
                    url = menuP;
                }

            } else if (action.equals("Submit order")) {
                url = orderP;
            } else if (action.equals("Add order")) {
                url = orderListP;
            }
        } catch (Exception e) {
            log("ERROR at SubmitOrderController: " + e.getMessage());
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
