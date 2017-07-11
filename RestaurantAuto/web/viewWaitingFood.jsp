<%-- 
    Document   : viewWaitingFood
    Created on : Jul 6, 2017, 9:46:19 PM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h4>Hi, ${STAFFID} [<a href="LogoutController">Logout</a>]</h4>
        <h1>Foods need to bring to customers</h1>
        <form action="FoodDeliveredController" method="POST">
            <input type="submit" value="Submit" />
            <c:forEach items="${foodWaitingList}" var="dto">
                <c:if test="${not empty dto.foodWaiting}">
                    <table class="table table-hover">
                        <caption>Table No: ${dto.tableID} Order: ${dto.seq}</caption>

                        <thead>
                            <tr>
                                <th>Food No</th>
                                <th>Food Name</th>
                                <th>Quantity</th>
                                <th>Done</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dto.foodWaiting}" var="food" varStatus="counter">

                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${food.foodName}</td>
                                    <td>${food.quantity}</td>
                                    <td>
                                        <input type="checkbox" name="${dto.seq}${food.foodID}" value="" />
                                    </td>
                                </tr>    

                            </c:forEach>
                        </tbody>
                        <tbody>
                    </table>
                </c:if>
            </c:forEach>
        </form>
    </body>
</html>
