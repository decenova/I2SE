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
        <form action="MainController" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Table ID</th>
                        <th>Order ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="" var="dto" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td></td>
                        <td></td>
                        <td>
                            <input type="submit" value="Show" name="action" />
                        </td>
                    </tr>
                </tbody>
                </c:forEach>
            </table>

        </form>
        
    </body>
</html>
