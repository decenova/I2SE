<%-- 
    Document   : manager
    Created on : Jun 22, 2017, 7:16:54 PM
    Author     : kubin
--%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manager Page!</h1>
        <form action="MainController" method="POST" accept-charset="ISO-8859-1">
            <table border="2">
                <thead>
                    <tr>
                        <th>Staff Manager</th>
                        <th>Table Manager</th>
                        <th>Food Manager</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <minhnh:if test="${not empty requestScope.STAFF}">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Role</th>
                                        <th>Available</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <minhnh:forEach var="dto" items="${requestScope.STAFF}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.firstName}</td>
                                        <td>${dto.lastName}</td>
                                        <td>${dto.role}</td>
                                        <td>Working</td>
                                    </tr>
                                    </minhnh:forEach>
                                </tbody>
                            </table>
                            </minhnh:if>
                        </td>
                        <td>
                            <minhnh:if test="${not empty requestScope.FOOD}">
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Food Name</th>
                                            <th>Food Type</th>
                                            <th>Quantity Of Order</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <minhnh:forEach var="food" items="${requestScope.FOOD}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${food.foodName}</td>
                                            <td>${food.foodType}</td>
                                            <td>${food.quantity}</td>
                                        </tr>
                                        </minhnh:forEach>
                                    </tbody>
                                </table>

                            </minhnh:if>
                        </td>
                        <td>
                            <minhnh:if test="${not empty requestScope.TABLE}">
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>No.</th>
                                            <th>Table ID</th>
                                            <th>Table Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <minhnh:forEach var="table" items="${requestScope.TABLE}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${table.id}</td>
                                            <td>${table.tableStatus}</td>
                                        </tr>
                                        </minhnh:forEach>
                                    </tbody>
                                </table>

                            </minhnh:if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Update Staff" name="action"/>
                        </td>
                        <td>
                            <input type="submit" value="Update Food" name="action"/>
                        </td>
                        <td>
                            <input type="submit" value="Update Table" name="action"/>
                        </td>
                    </tr>
                </tbody>
            </table>

        </form>
    </body>
</html>
