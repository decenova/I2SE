<%-- 
    Document   : mainManager
    Created on : Jun 26, 2017, 9:10:46 AM
    Author     : kubin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>      
        <h4>Hi, ${MANAME} [<a href="LogoutController">Logout</a>]</h4>
        <a href="ManagerController">Manager Page</a>
        <h1>Update Staff Page!</h1>
        <font color="green"><h2>${SUCCESS}</h2></font> 
        <font color="red"><h2>${FAIL}</h2></font>
            <minhnh:url var="insert" value="MainController">
                <minhnh:param name="txtFlag" value="Staff"/>
                <minhnh:param name="action" value="InsertPage"/>
            </minhnh:url>
        <h4><a href="${insert}">Insert new Staff</a></h4>
        <form action="MainController" method="POST" class="form-inline">
            <input type="hidden" name="txtFlag" value="Staff"/>
            <div class="form-group">            
                <label for="txtSearch">Search</label> 
                <input type="text" id="txtSearch" name="txtSearch" value="${lastSearchValue}"/>
            </div>
            <input type="submit" name="action" value="Search"  class="btn btn-success"/>
        </form>
        <minhnh:if test="${not empty requestScope.STAFFINFO}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Staff ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Salary</th>
                        <th>Staff Role</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    <minhnh:forEach var="dto" items="${requestScope.STAFFINFO}" varStatus="counter">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.staffID}</td>
                            <td>${dto.firstName}</td>
                            <td>${dto.lastName}</td>
                            <td>${dto.gender}</td>
                            <td>${dto.address}</td>
                            <td>${dto.salary}$</td>
                            <td>${dto.role}</td>
                            <td>
                                <input type="hidden" value="${lastSearchValue}" name="lastSearchValue"/>
                                <input type="hidden" value="txtStaff" name="flagUpdate"/>
                                <input type="hidden" value="${dto.staffID}" name="txtStaffID"/>
                                <input type="submit" value="Update" name="action"/>
                            </td>
                        </tr>
                    </form>
                </minhnh:forEach>
            </tbody>
        </table>
    </minhnh:if>
    <minhnh:if test="${not empty STAFFVALUE}">
        <div class="col-md-2 col-lg-3"></div>
        <form class="col-xs-12 col-md-8 col-lg-6" method="POST" action="MainController" accept-charset="ISO-8859-1">
            <div class="form-group">
                <label for="StaffID">StaffID</label>
                <input type="text" name="txtStaffId" value="${STAFFVALUE.staffID}" readonly class="form-control" id="StaffID">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" name="txtPassword" value="${STAFFVALUE.password}" class="form-control" id="pwd">
            </div>
            <div class="form-group">
                <label for="FirstName">First Name</label>
                <input type="text" name="txtFirstName" value="${STAFFVALUE.firstName}" class="form-control" id="FirstName">
            </div>
            <div class="form-group">
                <label for="LastName">Last Name</label>
                <input type="text" name="txtLastName" value="${STAFFVALUE.lastName}" class="form-control" id="LastName">
            </div>
            <div class="form-group">
                <fmt:formatDate value="${STAFFVALUE.DOB}" type="date" var="dob" pattern="yyyy-MM-dd"/>
                <label for="Date">Date Of Birth</label>
                <input type="date" name="dateDOB" value="${dob}" class="form-control" id="Date">
            </div>
            <div class="form-group">
                <label for="Address">Address</label>
                <input type="text" name="txtAddress" value="${STAFFVALUE.address}" class="form-control" id="Address">
            </div>
            <div class="form-group">
                <label class="radio-inline"><input type="radio" name="cbSex" value="M" <minhnh:if test="${STAFFVALUE.gender eq 'M'}">checked</minhnh:if> >Male</label>
                <label class="radio-inline"><input type="radio" name="cbSex" value="F" <minhnh:if test="${STAFFVALUE.gender eq 'F'}">checked</minhnh:if>>Female</label>
                </div>
                <div class="form-group">
                    <label for="Salary">Salary</label>
                    <input type="text" name="txtSalary" value="${STAFFVALUE.salary}" class="form-control" id="Salary">
            </div>
            <div class="form-group">
                <label for="roleList">Role</label>
                <select class="form-control" name="roleList" id="roleList">
                    <option value="1" <minhnh:if test="${STAFFVALUE.role == 'Manager'}">selected</minhnh:if>>Manager</option>
                    <option value="2" <minhnh:if test="${STAFFVALUE.role == 'Host'}">selected</minhnh:if>>Host</option>
                    <option value="3" <minhnh:if test="${STAFFVALUE.role == 'Waiter'}">selected</minhnh:if>>Waiter</option>
                    <option value="4" <minhnh:if test="${STAFFVALUE.role == 'Busboy'}">selected</minhnh:if>>Busboy</option>
                    <option value="5" <minhnh:if test="${STAFFVALUE.role == 'Cook'}">selected</minhnh:if>>Cook</option>
                    <option value="6" <minhnh:if test="${STAFFVALUE.role == 'Casher'}">selected</minhnh:if>>Casher</option>
                    </select>
                </div>
                <input type="hidden" value="Staff" name="txtFlag"/>
                <input type="hidden" value="${lastSearchValue}" name="txtSearch"/>
            <input type="submit" value="Edit" name="action" class="btn btn-success"/> 
        </form>
    </minhnh:if>
</body>
</html>
