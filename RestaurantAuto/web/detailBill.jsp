<%-- 
    Document   : detailBill
    Created on : Jul 11, 2017, 5:08:59 PM
    Author     : kubin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minh" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <h1>Order Detail</h1>


        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <minh:forEach var="dto" items="${requestScope.DetaiBill}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.foodName}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price}</td>
                    </tr>
                </minh:forEach>

            </tbody>
        </table>
        <h4>Total: ${requestScope.Total}$</h4>
        <form action="MainController" method="POST">
            <input type="hidden" value="${requestScope.OrderId}" name="pk"/>
            <input type="hidden" value="${requestScope.TableId}" name="tableId"/>
            <input type="hidden" value="${requestScope.Total}" name="total"/>
            <input type="hidden" value="${OrderId}" name="seqOrder"/>

            <input type="submit" value="Print Bill" name="action"/>
            <input type="submit" value="Back" name="action"/>
        </form>
    </body>
</html>
