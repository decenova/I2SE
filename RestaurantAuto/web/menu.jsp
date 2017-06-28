<%-- 
    Document   : menu
    Created on : Jun 19, 2017, 12:14:09 PM
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
        <h1>Menu</h1>
        <div style="width:50%; float: left">
            <c:if test="${not empty MENU}">

                <table border="1">
                    <caption>Menu</caption>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Food Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${MENU}" var="dto" varStatus="counter">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.foodID}</td>
                                <td>${dto.foodName}</td>
                                <td>
                                    <input type="number" name="txtQuantity" value="1" min="0" />
                                </td>
                                <td>
                                    <input type="hidden" name="txtFoodID" value="${dto.foodID}" />
                                    <input type="hidden" name="txtFoodName" value="${dto.foodName}" />
                                    <input type="submit" value="Add" name="action" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>

            </c:if>
        </div>

        <div style="width:50%; float: left">
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
                            <c:forEach items="${ORDER}" var="dto" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.foodID}</td>
                                    <td>${dto.foodName}</td>
                                    <td>${dto.quantity}</td>
                                </tr>
                            </c:forEach>
                        </tbody>                   
                    </table>
                    <input type="submit" name="action" value="Submit order" />                   
                </c:if>
            </form>
        </div>
    </body>
</html>
