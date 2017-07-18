/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tung.controllers;

import beans.TrungBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CreateOrderController extends HttpServlet {

    private final String errorP = "error.jsp";
    private final String orderP = "order.jsp";
    private final String order = "OrderController";

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
            System.out.println("vao create r nha %%%%%%%");
            
            HttpSession session = request.getSession();
            OrderDAO dao = new OrderDAO();
            String action = request.getParameter("action");
            String tableID = request.getParameter("tableId");
            String staffId = request.getParameter("staffId");
            List<OrderDTO> dto = dao.loadMenu();
            session.setAttribute("MENU", dto);
            int seqStaff = dao.getSEQStaffById(staffId);
            int seqTable = dao.getSEQTableById(tableID);
            int seqOrder = dao.checkTableUsing(seqTable);
            if (seqOrder != 0) {
                OrderDTO info = dao.loadOrderInfo(seqOrder);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
                String now = sdf.format(info.getBeginTime());
                session.setAttribute("orderSeq", seqOrder);
                session.setAttribute("staffID", info.getWaiterID());
                session.setAttribute("tableID", info.getTableID());
                session.setAttribute("DATE", now);
                List<OrderDTO> listFood = dao.showOrderDetail(seqOrder, "null");
                List<OrderDTO> listCookingFood = dao.showOrderDetail(seqOrder, "not null");
                session.setAttribute("ORDER", listFood);
                session.setAttribute("FoodCooking", listCookingFood);
                url = orderP;
            } else {
                session.setAttribute("tableID", tableID);
                Date time = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
                String now = sdf.format(time);
                session.setAttribute("DATE", now);
                Timestamp date = new Timestamp(time.getTime());
                boolean check = dao.addOrderFirst(seqTable, seqStaff, date);
                if (check) {
                    request.setAttribute("ACTION", action);
                    seqOrder = dao.getMaxSEQOrder();
                    session.setAttribute("orderSeq", seqOrder);
                    session.removeAttribute("ORDER");
                    url = orderP;
                } else {
                    url = errorP;
                }
            }

        } catch (Exception e) {
            log("ERROR at CreateOrderController: " + e.getMessage());
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
