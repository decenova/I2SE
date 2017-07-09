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
        <p>Order No: ${orderSeq}</p>
        <p>Date: ${DATE}</p>
        <div style="width:50%; float: left">
            <c:if test="${(ACTION eq 'Create order as Waiter') or (ACTION eq 'Add') or (not ACTION eq 'Submit order')}">
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
                        <form action="MainController" method="POST"  accept-charset="ISO-8859-1">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.foodID}</td>
                                <td>${dto.foodName}</td>
                                <td>
                                    <input type="number" name="txtQuantity" value="1" min="1" required />
                                </td>
                                <td>
                                    <input type="hidden" name="txtFoodID" value="${dto.foodID}" />
                                    <input type="hidden" name="txtFoodName" value="${dto.foodName}" />
                                    <input type="submit" value="Add" name="action"  />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
            <div style="width:50%; float: left">
                <form action="MainController" method="POST" accept-charset="ISO-8859-1">
                    <c:if test="${not empty ORDER}">
                        <table border="1">
                            <caption>Order Submit</caption>
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Food ID</th>
                                    <th>Food Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ORDER}" var="dto" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.foodID}</td>
                                        <td>${dto.foodName}</td>
                                        <td>${dto.quantity}</td>
                                        <td>
                                            <a href="SubmitOrderController?action=Remove&FoodNo=${counter.count}">Remove</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>                   
                        </table>
                        <input type="submit" name="action" value="Submit order" />                   
                    </c:if>
                </form>
            </div>
        </c:if>
        <c:if test="${(ACTION eq 'Submit order') or (ACTION eq 'Show Order')}">
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
                    <input type="hidden" name="txtSEQOrder" value="${orderSeq}" />
                    <c:if test="${ACTION eq 'Submit order'}">
                    <input type="submit" value="Add order" name="action" />
                    </c:if>
                </c:if>
            </c:if>
        </form>
    </body>
</html>
