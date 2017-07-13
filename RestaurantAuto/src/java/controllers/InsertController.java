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
import minhnh.dto.FoodErrorObj;
import minhnh.dto.TableDTO;
import minhnh.dto.TableErrorObj;

/**
 *
 * @author kubin
 */
public class InsertController extends HttpServlet {

    private final String staffSearchPage = "staffManager.jsp";
    private final String foodSearchPage = "foodManager.jsp";
    private final String tableSearchPage = "tableManager.jsp";
    private final String insertPage = "insert.jsp";

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
            String flag = request.getParameter("txtFlag");
            if (flag.equals("Staff")) {
                String staffId = request.getParameter("txtStaffId");
                String password = request.getParameter("txtPassword");
                String firstName = request.getParameter("txtFirstName");
                String lastName = request.getParameter("txtLastName");
                String dtDOB = request.getParameter("dateDOB");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dtDOB);
                Timestamp DOB = new Timestamp(date.getTime());
                String tmp = request.getParameter("cbSex");
                String gender;
                if (tmp.equals("M")) {
                    gender = "M";
                } else {
                    gender = "F";
                }
                String address = request.getParameter("txtAddress");
                String salaryTmp = request.getParameter("txtSalary");
                int salary = Integer.parseInt(salaryTmp);
                String role = request.getParameter("roleList");
                int roleId = Integer.parseInt(role);
                MinhRestaurantDAO dao = new MinhRestaurantDAO();
                boolean result = dao.insertStaff(staffId, password, firstName,
                        lastName, DOB, address, gender, salary, true, roleId);
                if (result) {
                    request.setAttribute("SUCCESS", "Insert successful");
                    url = staffSearchPage;
                }
            } else if (flag.equals("Food")) { //Food Insert
                FoodErrorObj error = new FoodErrorObj();
                boolean flagError = true;
                String foodId = request.getParameter("txtFoodId").toUpperCase();
//                boolean foodIdErr = foodId.matches("[A-Z]{2}\\d{3}");
//                if (!foodIdErr) {
//                    error.setFoodIdErr("FoodId must matched ([A-Z]{2}001)");
//                    flagError = false;
//                }
                MinhRestaurantDAO dao = new MinhRestaurantDAO();
//                FoodDTO dto = dao.findFoodById(foodId);
//                if (dto != null) {
//                    error.setDuplicateErr("Food ID is already existes!");
//                    flagError = false;
//                }
                String foodName = request.getParameter("txtFoodName");
//                if (foodName.isEmpty()){
//                    error.setFoodNameErr("Food Name can't be blank!");
//                    flagError = false;
//                }
                String tmp = request.getParameter("txtCost");
//                if (tmp.isEmpty()){
//                    error.setCostErr("Cost can't be blank!");
//                    flagError = false;
//                }
//                boolean foodCostErr = tmp.matches("\\d+");
//                if (!foodCostErr){
//                    error.setCostErr("Cost must a number!");
//                    flagError = false;
//                }
                int cost = Integer.parseInt(tmp);
                String type = request.getParameter("typeList");
                int typeId = Integer.parseInt(type);
                String avai = request.getParameter("ckAvailable");
                boolean available;
                if (avai == null) {
                    available = false;
                } else {
                    available = true;
                }
                if (flagError) {
                    dao = new MinhRestaurantDAO();
                    boolean result = dao.insertFood(foodId, foodName, cost, available, typeId);
                    if (result) {
                        request.setAttribute("SUCCESS", "Insert successful");
                        url = foodSearchPage;
                    }
                } else {
                    request.setAttribute("txtFlag", flag);
                    request.setAttribute("ERROR", error);
                    url = insertPage;
                }

            } else if (flag.equals("Table")) {
                TableErrorObj error = new TableErrorObj();
                boolean flagError = true;
                String tableId = request.getParameter("txtTableId").toUpperCase();
                boolean idError = tableId.matches("TB\\d{3}");
                if (!idError) {
                    error.setTableId("Table Id must matched (TB001)");
                    flagError = false;
                }
                MinhRestaurantDAO dao = new MinhRestaurantDAO();
                TableDTO dto = dao.findTableById(tableId);
                if (dto != null) {
                    error.setDuplicate("Table Id is already existed!");
                    flagError = false;
                }
                String status = request.getParameter("statusList");
                int statusId = Integer.parseInt(status);
                if (flagError) {

                    dao = new MinhRestaurantDAO();
                    boolean result = dao.insertTable(tableId, statusId);
                    if (result) {
                        request.setAttribute("SUCCESS", "Insert successful");
                        url = tableSearchPage;
                    }
                } else {
                    request.setAttribute("txtFlag", flag);
                    request.setAttribute("ERROR", error);
                    url = insertPage;
                }
            }
        } catch (Exception e) {
            log("ERROR at InsertController" + e.getMessage());
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
