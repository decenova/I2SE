<%-- 
    Document   : tableStatus
    Created on : Jun 21, 2017, 11:01:31 AM
    Author     : Duc Trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Table status</h1>
        <table>
            <thead>
            <th>Table</th>
            <th>Status</th>
            <th>Change Status</th>
        </thead>
        <c:forEach var="table" items="${TABLES}">
            <tr>
                <td>${table.id}</td>
                <td>${table.tableStatus}</td>
                <td></td>
            </tr>
        </c:forEach>


    </table>
</body>
</html>
