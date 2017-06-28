<%-- 
    Document   : order
    Created on : Jun 18, 2017, 5:05:39 PM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>Order</h1>
        <p>Table No: ${tableID}</p>
        <p>Waiter ID: ${STAFFID}</p>
        <p>Date: ${DATE}</p>
        <c:if test="${empty ORDER}">
            <a href="OrderController?action=Show menu">Show menu</a>
        </c:if>
        <form action="MainController" method="POST">
            <c:if test="${not empty ORDER}">
                <table border="1">
                    <caption>Order Submit</caption>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Food ID</th>
                            <th>Food Name</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${ORDER}" var="o" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${o.foodID}</td>
                                <td>${o.foodName}</td>
                                <td>${o.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>                   
                </table>
                <input type="submit" value="Add order" name="action" />
            </c:if>
        </form>
    </body>
</html>
