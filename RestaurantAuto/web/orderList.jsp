<%-- 
    Document   : orderList
    Created on : Jun 21, 2017, 3:06:08 PM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View order</title>
    </head>
    <body>
        <h1>View order</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Order ID</th>
                    <th>Table ID</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ORDERS}" var="dto" varStatus="counter">
                <form action="MainController" method="POST">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.seq}</td>
                        <td>${dto.tableID}</td>
                        <td>
                            <input type="hidden" name="seqOrder" value="${dto.seq}"/>
                            <input type="submit" value="Show Order" name="action" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </c:forEach>
    </table>



</body>
</html>
