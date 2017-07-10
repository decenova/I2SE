<%-- 
    Document   : insert
    Created on : Jul 5, 2017, 11:20:44 PM
    Author     : kubin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <h1>Insert</h1>
        <div class="col-md-2 col-lg-3"></div>
        <form class="col-xs-12 col-md-8 col-lg-6" method="POST" action="MainController" accept-charset="ISO-8859-1">
            <minhnh:if test="${txtFlag eq 'Staff'}">
                <div class="form-group">
                    <label for="StaffID">StaffID</label>
                    <input type="text" name="txtStaffId" class="form-control" id="StaffID">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" name="txtPassword" class="form-control" id="pwd">
                </div>
                <div class="form-group">
                    <label for="FirstName">First Name</label>
                    <input type="text" name="txtFirstName" class="form-control" id="FirstName">
                </div>
                <div class="form-group">
                    <label for="LastName">Last Name</label>
                    <input type="text" name="txtLastName" class="form-control" id="LastName">
                </div>
                <div class="form-group">
                    <label for="Date">Date Of Birth</label>
                    <input type="date" name="dateDOB" class="form-control" id="Date">
                </div>
                <div class="form-group">
                    <label for="Address">Address</label>
                    <input type="text" name="txtAddress" class="form-control" id="Address">
                </div>
                <div class="form-group">
                    <label class="radio-inline"><input type="radio" name="cbSex" value="M" checked>Male</label>
                    <label class="radio-inline"><input type="radio" name="cbSex" value="F">Female</label>
                </div>
                <div class="form-group">
                    <label for="Salary">Salary</label>
                    <input type="text" name="txtSalary" class="form-control" id="Salary">
                </div>
                <div class="form-group">
                    <label for="roleList">Role</label>
                    <select class="form-control" name="roleList" id="roleList">
                        <option value="1">Manager</option>
                        <option value="2">Host</option>
                        <option value="3">Waiter</option>
                        <option value="4">Busboy</option>
                        <option value="5">Cook</option>
                        <option value="6">Casher</option>
                    </select>
                </div>
                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                <input type="submit" name="action" value="Insert"  class="btn btn-default"/> 
            </minhnh:if>
            <minhnh:if test="${txtFlag eq 'Food'}">
                <div class="form-group">
                    <label for="FoodID">FoodID</label>
                    <input type="text" name="txtFoodId" class="form-control" id="FoodID">
                    <font color="red">${ERROR.foodIdErr}</font>
                    <font color="red">${ERROR.duplicateErr}</font>
                </div>
                <div class="form-group">
                    <label for="Name">Name</label>
                    <input type="text" name="txtFoodName" class="form-control" id="Name">
                    <font color="red">${ERROR.foodNameErr}</font>
                </div>
                <div class="form-group">
                    <label for="Cost">Cost</label>
                    <input type="number" name="txtCost" class="form-control" id="Cost">
                    <font color="red">${ERROR.costErr}</font>
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="ckAvailable" checked/>Available</label>
                </div>
                <div class="form-group">
                    <label for="typeList">Type</label>
                    <select class="form-control" name="typeList" id="typeList">
                        <option value="1">Khai vị</option>
                        <option value="2">Chính</option>
                        <option value="3">Tráng miệng</option>
                        <option value="4">Nước giải khát</option>
                        <option value="5">Rượu</option>
                        <option value="6">Bia</option>
                    </select>
                </div>
                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                <input type="submit" name="action" value="Insert"  class="btn btn-default"/> 
            </minhnh:if>
            <minhnh:if test="${txtFlag eq 'Table'}">
                <div class="form-group">
                    <label for="TableID">TableID</label>
                    <input type="text" name="txtTableId" class="form-control" id="TableID">
                    <font color="red">${ERROR.tableId}</font>
                    <font color="red">${ERROR.duplicate}</font>
                </div>
                <div class="form-group">
                    <label for="statusList">Status</label>
                    <select class="form-control" name="statusList" id="statusList">
                        <option value="1">Clean</option>
                        <option value="2">Waiting</option>
                        <option value="3">Eating</option>
                        <option value="4">Dirty</option>
                        <option value="5">Disable</option>
                    </select>
                </div>
                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                <input type="submit" name="action" value="Insert"  class="btn btn-default"/> 
            </minhnh:if>
        </form>
    </body>
</html>
