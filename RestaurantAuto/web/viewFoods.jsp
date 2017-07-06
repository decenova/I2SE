<%-- 
    Document   : viewFoods
    Created on : Jul 5, 2017, 6:24:26 PM
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
        <h4>Hi, ${STAFFID}</h4>
        <h1>View Food</h1>
        <a href="ViewCookFoodController?staffID=${STAFFID}">View Cook Food</a>
        <form action="ChooseFoodController" method="POST">
            <input type="hidden" name="staffID" value="${STAFFID}" />
            <input type="submit" value="Submit Food" /> <br/> <br/>
        <c:forEach items="${orderList}" var="dto">
            <table border=1 style="width:25%; float: left">
                <caption>Table No: ${dto.tableID} Order: ${dto.seq}</caption>
                <thead>
                    <tr>
                        <th>Food No</th>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Choice</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dto.foodDetails}" var="food" varStatus="counter">
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

            </table>
        </c:forEach>
            </form>
    </body>
</html>
