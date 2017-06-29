<%-- 
    Document   : error
    Created on : Jun 26, 2017, 10:37:33 AM
    Author     : Duc Trung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <h2 style="color: red">${requestScope.ERROR}</h2>
    </body>
</html>
