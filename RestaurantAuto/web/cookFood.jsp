<%-- 
    Document   : cookFood
    Created on : Jul 6, 2017, 12:35:34 AM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width"/>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Cooking Foods</h1>
        <a href="viewFoods.jsp">Back to Foods by Order</a>
        <h4>Hi, ${STAFFID}</h4>
        <form action="SubmitCookingFoodController" method="POST"">
            <input type="hidden" name="staffID" value="${STAFFID}" />
            <input type="submit" value="Submit Food" /> <br/> <br/>
            <c:forEach items="${listChooseFood}" var="dto">
                <c:if test="${not empty dto.foodChoice}">
                    <table class="table table-hover">
                        <caption>Table No: ${dto.tableID} Order: ${dto.seq}</caption>

                        <thead>
                            <tr>
                                <th>Done</th>
                                <th>Food No</th>
                                <th>Food Name</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dto.foodChoice}" var="food" varStatus="counter">

                                <tr>
                                    <td>
                                        <input type="checkbox" name="${dto.seq}${food.foodID}" value="" />
                                    </td>
                                    <td>${counter.count}</td>
                                    <td>${food.foodName}</td>
                                    <td>${food.quantity}</td>
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
