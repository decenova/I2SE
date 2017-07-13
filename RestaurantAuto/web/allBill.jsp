<%-- 
    Document   : allBill
    Created on : Jul 11, 2017, 3:14:11 PM
    Author     : kubin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minh" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Casher Page!</h1>
        <minh:if test="${not empty requestScope.ViewBill}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Table Id</th>
                        <th>Order Id</th>
                        <th>Waiter serve</th>
                        <th>View detail</th>
                    </tr>
                </thead>
                <tbody>

                <minh:forEach var="dto" items="${requestScope.ViewBill}">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${dto.tableId}</td>
                            <td>${dto.orderId}</td>
                            <td>${dto.waiterName}</td>
                            <td>
                                <input type="hidden" name="orderId" value="${dto.orderId}"/>
                                <input type="hidden" name="tableId" value="${dto.tableId}"/>
                                <input type="submit" value="View Detail" name="action"/>
                            </td>
                        </tr>
                    </form>
                </minh:forEach>

            </tbody>
        </table>
    </minh:if>
        <minh:if test="${empty requestScope.ViewBill}">
            There are no order!
        </minh:if>
</body>
</html>

