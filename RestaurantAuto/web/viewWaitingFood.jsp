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
    </head>
    <body>
        <h1>Foods need to bring to customers</h1>
        <c:forEach items="${foodWaitingList}" var="dto">
                <c:if test="${not empty dto.foodWaiting}">
                    <table border=1 style="width:25%; float: left">
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
    </body>
</html>
