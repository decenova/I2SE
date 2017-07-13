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
        <title>Login Page</title>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" href="bootstrap/js/bootstrap.min.js"></script>
        <style>

        </style>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            <c:if test="${not (ERROR eq null)}">
                <div class="col-lg-1"></div>
                <div class="col-lg-10 alert alert-danger">
                    ${ERROR}
                </div>
                <div class="col-lg-1"></div>
            </c:if>
            <div class="col-lg-12">
                <div class="form-group">
                    <label for="txtId">ID:</label>
                    <input  name="txtId"  type="text" class="form-control" id="txtId">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input name="txtPassword" type="password" class="form-control" id="pwd">
                </div>
                <input type="submit" class="btn btn-success" value="Login" name="action"/>
            </div>
        </form>
    </body>
</html>
