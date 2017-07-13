<%-- 
    Document   : analyzeTime
    Created on : 13-Jul-2017, 10:45:56
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
        <h1>Time</h1>
        
        <a href="ManagerController">Back to manager page</a>
        <form action="analyzeTimeController" method="post">
            <input id="from" type="date" name="fromDate" required="true" 
                   <c:if test="${not empty requestScope.FROM}"> value="${requestScope.FROM}" </c:if> value = "2017-05-30"/>
                   <input id="to" type="date" name="toDate" required="true"
                   <c:if test="${not empty requestScope.TO}"> value="${requestScope.TO}" </c:if> value = "2017-06-10"/>
                   <input type="submit" value="Start Analyze"/>
            </form>
                   <h1>Average turnaround time: ${requestScope.SPEND}</h1>
                   <h1>Average preparation time: ${requestScope.SERVER}</h1>
    </body>
</html>
