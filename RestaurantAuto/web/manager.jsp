<%-- 
    Document   : manager
    Created on : Jun 22, 2017, 7:16:54 PM
    Author     : kubin
--%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
        <meta name="viewport" content="width=device-width"/>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h4>Hi, ${MANAME} [<a href="LogoutController">Logout</a>]</h4>
        <h1>Manager Page!</h1>
        <a href="analyze.jsp">Analyze Efficiency</a><br>
        <a href="analyzeRevenue.jsp">Analyze Revenue</a><br>
        <form action="MainController" method="POST" accept-charset="ISO-8859-1">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#Staff">Staff</a></li>
                <li><a data-toggle="tab" href="#Table">Table</a></li>
                <li><a data-toggle="tab" href="#Food">Food</a></li>
            </ul>
            <div class="tab-content">
                <div id="Staff" class="tab-pane fade in active">
                    <input type="submit" value="Update Staff" name="action"/>
                    <minhnh:if test="${not empty requestScope.STAFF}">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Role</th>
                                    <th>Available</th>
                                </tr>
                            </thead>
                            <tbody>
                                <minhnh:forEach var="dto" items="${requestScope.STAFF}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.firstName}</td>
                                        <td>${dto.lastName}</td>
                                        <td>${dto.role}</td>
                                        <td>Working</td>
                                    </tr>
                                </minhnh:forEach>
                            </tbody>
                        </table>
                    </minhnh:if>
                </div>
                <div id="Table" class="tab-pane fade">
                    <input type="submit" value="Update Table" name="action"/>
                    <minhnh:if test="${not empty requestScope.TABLE}">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Table ID</th>
                                    <th>Table Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <minhnh:forEach var="table" items="${requestScope.TABLE}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${table.id}</td>
                                        <td>${table.tableStatus}</td>
                                    </tr>
                                </minhnh:forEach>
                            </tbody>
                        </table>
                    </minhnh:if>
                </div>
                <div id="Food" class="tab-pane fade">
                    <input type="submit" value="Update Food" name="action"/>
                    <minhnh:if test="${not empty requestScope.FOOD}">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Food Name</th>
                                    <th>Food Type</th>
                                    <th>Quantity Of Order</th>
                                </tr>
                            </thead>
                            <tbody>
                                <minhnh:forEach var="food" items="${requestScope.FOOD}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${food.foodName}</td>
                                        <td>${food.foodType}</td>
                                        <td>${food.quantity}</td>
                                    </tr>
                                </minhnh:forEach>
                            </tbody>
                        </table>
                    </minhnh:if>
                </div>
            </div>
        </form>
    </body>
</html>
