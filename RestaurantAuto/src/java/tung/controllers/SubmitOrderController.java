/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tung.bean.OrderBean;
import tung.dao.OrderDAO;
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
    private static final String showTableStatus = "tableStatus.jsp";

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
                    request.setAttribute("ACTION", action);
                    url = orderP;
                }
            } else if (action.equals("Remove")) {
                String foodNo = request.getParameter("FoodNo");
                System.out.println(foodNo);
                result.remove(Integer.parseInt(foodNo) - 1);
                session.setAttribute("ORDER", result);
                url = orderP;
            } else if (action.equals("Submit order")) {
                OrderDAO dao = new OrderDAO();
                OrderBean bean = new OrderBean();
                String txtSeqOrder = request.getParameter("txtSEQOrder");
                int seqOrder = Integer.parseInt(txtSeqOrder);
                dao.deleteOrderDetail(seqOrder);
                bean.setSeqOrder(seqOrder);
                for (int i = 0; i < result.size(); i++) {
                    bean.setFoodID(result.get(i).getFoodID());
                    int seqFood = bean.getSEQFood();
                    bean.setSeqFood(seqFood);
                    bean.setQuantity(result.get(i).getQuantity());
                    bean.addOrderDetail();
                }
                session.removeAttribute("ORDER");
                session.removeAttribute("orderSeq");
                session.removeAttribute("DATE");
                session.removeAttribute("tableID");
                url = showTableStatus;
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
