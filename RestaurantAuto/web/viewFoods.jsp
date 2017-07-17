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
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h4>Hi, ${STAFFID} [<a href="LogoutController">Logout</a>]</h4>
        <h1>View Food</h1>
        <a href="ViewCookFoodController?staffID=${STAFFID}">View Cook Food</a>
        <form action="ChooseFoodController" method="POST">
            <input type="hidden" name="staffID" value="${STAFFID}" />
            <input type="submit" value="Submit Food" /> <br/> <br/>
        <c:forEach items="${orderList}" var="dto">
            <c:if test="${not empty dto.foodDetails}">
                <table class="table table-hover col-md-8 col-lg-6">
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
                </c:if>
        </c:forEach>
            </form>
    </body>
    
</html>
