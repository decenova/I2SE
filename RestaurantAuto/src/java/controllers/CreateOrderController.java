/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import tung.bean.OrderBean;
import tung.dao.OrderDAO;
import tung.dto.OrderDTO;

/**
 *
 * @author hoanh
 */
public class CreateOrderController extends HttpServlet {

    private final String errorP = "error.jsp";
    private final String orderP = "order.jsp";

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
            String tableID = request.getParameter("tableId");
            TrungBean bean = new TrungBean();
            bean.changeTableStatus(request.getParameter("tableId"), Integer.parseInt(request.getParameter("tableStatusId")), request.getSession().getAttribute("STAFFID").toString());
            session.setAttribute("tableID", tableID);
            String staffId = request.getParameter("staffId");
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
            String now = sdf.format(time);
            session.setAttribute("DATE", now);
            OrderBean orderBean = new OrderBean();
            orderBean.setStaffID(staffId);
            orderBean.setTableID(tableID);
            int seqStaff = orderBean.getSeqStaff();
            int seqTable = orderBean.getSeqTable();
            orderBean.setSeqTable(seqTable);
            orderBean.setSeqWaiter(seqStaff);
            Timestamp date = new Timestamp(time.getTime());
            orderBean.setBeginTime(date);
            if (orderBean.addOrderFirst()) {
                int seqOrder = orderBean.getSEQOrder();
                session.setAttribute("orderSeq", seqOrder);
                url = orderP;
            } else 
                url = errorP;
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
