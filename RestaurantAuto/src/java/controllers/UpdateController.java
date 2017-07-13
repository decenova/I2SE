/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhnh.dao.MinhRestaurantDAO;
import minhnh.dto.FoodDTO;
import minhnh.dto.StaffDTO;
import minhnh.dto.TableDTO;

/**
 *
 * @author kubin
 */
public class UpdateController extends HttpServlet {

    private final static String staffManager = "staffManager.jsp";
    private final static String foodManager = "foodManager.jsp";
    private final static String tableManager = "tableManager.jsp";
    private final static String searchController = "SearchController";

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
            String action = request.getParameter("action");
            String searchValue = request.getParameter("lastSearchValue");
            request.setAttribute("lastSearchValue", searchValue);
            System.out.println(searchValue);
            if (action.equals("Update")) {
                String flag = request.getParameter("flagUpdate");
                if (flag.equals("txtStaff")) {
                    String staffId = request.getParameter("txtStaffID");
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    StaffDTO dto = dao.findStaffById(staffId);
                    request.setAttribute("STAFFVALUE", dto);
                    url = staffManager;
                } else if (flag.equals("txtFood")) {
                    String foodId = request.getParameter("txtFoodID");
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    FoodDTO dto = dao.findFoodById(foodId);
                    request.setAttribute("FOODVALUE", dto);
                    url = foodManager;
                } else if (flag.equals("txtTable")) {
                    String tableId = request.getParameter("txtTableID");
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    TableDTO dto = dao.findTableById(tableId);
                    request.setAttribute("TABLEVALUE", dto);
                    url = tableManager;
                }
            } else if (action.equals("Edit")) {
                String flag = request.getParameter("txtFlag");
                if (flag.equals("Staff")) {
                    String staffId = request.getParameter("txtStaffId");
                    String password = request.getParameter("txtPassword");
                    String firstName = request.getParameter("txtFirstName");
                    String lastName = request.getParameter("txtLastName");
                    String dtDOB = request.getParameter("dateDOB");
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dtDOB);
                    Timestamp DOB = new Timestamp(date.getTime());
                    String tmp = request.getParameter("ckGender");
                    String gender;
                    if (tmp.equals("MALE")) {
                        gender = "M";
                    } else {
                        gender = "F";
                    }
                    String address = request.getParameter("txtAddress");
                    String salaryTmp = request.getParameter("txtSalary");
                    int salary = Integer.parseInt(salaryTmp);
                    String role = request.getParameter("roleList");
                    int roleId = Integer.parseInt(role);
                    String avai = request.getParameter("ckAvailable");
                    boolean available;
                    if (avai == null) {
                        available = false;
                    } else {
                        available = true;
                    }
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    boolean result = dao.updateStaff(staffId, password, firstName,
                            lastName, DOB, address, gender, salary, available, roleId);
                    if (result) {
                        request.setAttribute("SUCCESS", "Update successful");

                    } else {
                        request.setAttribute("FAIL", "Update fail");
                    }
                    url = searchController;
                } else if (flag.equals("Food")) {
                    String foodId = request.getParameter("txtFoodId");
                    String foodName = request.getParameter("txtFoodName");
                    String tmp = request.getParameter("txtCost");
                    int cost = Integer.parseInt(tmp);
                    String type = request.getParameter("typeList");
                    int typeId = Integer.parseInt(type);
                    String avai = request.getParameter("ckAvailable");
                    boolean available;
                    if (avai == null){
                        available = false;
                    } else {
                        available = true;
                    }
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    boolean result = dao.updateFood(foodId, foodName, cost, available, typeId);
                    if (result) {
                        request.setAttribute("SUCCESS", "Update successful");
                    } else {
                        request.setAttribute("FAIL", "Update fail");
                    }
                    url = searchController;
                } else if (flag.equals("Table")){
                    String tableId = request.getParameter("txtTableId");
                    String status = request.getParameter("statusList");
                    int statusId = Integer.parseInt(status);
                    MinhRestaurantDAO dao = new MinhRestaurantDAO();
                    boolean result = dao.updateTable(tableId, statusId);
                    if (result) {
                        request.setAttribute("SUCCESS", "Update successful");
                    } else {
                        request.setAttribute("FAIL", "Update fail");
                    }
                    url = searchController;
                }
            }
        } catch (Exception e) {
            log("ERROR at UpdateController" + e.getMessage());
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
