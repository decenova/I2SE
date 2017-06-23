<%-- 
    Document   : index
    Created on : 16-Jun-2017, 09:23:13
    Author     : Decen
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
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            ID: <input type="text" name="txtId" value="HO001" required/><br/>
            Password: <input type="password" name="txtPassword" value="staff" required/><br/>
            <font color="red">${ERROR}</font><br/>
            <input type="submit" value="Login" name="action"/>
            
        </form>
    </body>
</html>
