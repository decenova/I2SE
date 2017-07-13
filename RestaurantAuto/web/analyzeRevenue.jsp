<%-- 
    Document   : analyzeRevenue
    Created on : 13-Jul-2017, 00:36:45
    Author     : Decen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analyze Page</title>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            function getBaseLog(x, y) {
                return Math.log(y) / Math.log(x);
            }
        </script>
    </head>
    <script>

    </script>
    <body>
        <h1>Analyze</h1>

        <form action="analyzeRevenueController" method="post">
            <input id="from" type="date" name="fromDate" required="true" 
                   <c:if test="${not empty requestScope.FROM}"> value="${requestScope.FROM}" </c:if> value = "2017-05-30"/>
                   <input id="to" type="date" name="toDate" required="true"
                   <c:if test="${not empty requestScope.TO}"> value="${requestScope.TO}" </c:if> value = "2017-06-10"/>
                   <input type="submit" value="Start Analyze"/>
            </form>
                       <h1>Total Revenue: ${requestScope.REVENUE}</h1>
        <c:if test="${not empty requestScope.REVENUELIST}">
            <canvas id="myCanvas" width="800" height="600"></canvas>
            <script>
                var canvas = document.getElementById("myCanvas");
                var ctx = canvas.getContext("2d");
                ctx.fillStyle = "#00AAFF";
                ctx.beginPath();
                ctx.moveTo(90, 500);
                ctx.lineTo(90, 100);
                ctx.moveTo(90, 500);
                ctx.lineTo(700, 500);
                ctx.stroke();
                var max = ${requestScope.MAX};
                var columnwidth = 600 / ${requestScope.RANK};
                var distan = columnwidth / (${requestScope.RANK} - 1);
                console.log(columnwidth);
                console.log(distan);
                var bonus = Math.pow(10, Math.floor(getBaseLog(10, max)));
                ctx.font = '20px sasn-serif';
                for (var i = 0; i <= max; i += bonus) {
                    ctx.beginPath();
                    ctx.moveTo(90, 500 - i / max * 400);
                    ctx.lineTo(85, 500 - i / max * 400);
                    ctx.stroke();
                    ctx.fillText(i, 60, 500 - i / max * 400 - 10);
                }
                ctx.fillText("${requestScope.FROM}", 50, 500 + 30);
                ctx.fillText("${requestScope.TO}", 650, 500 + 30);
                <c:forEach items="${requestScope.REVENUELIST}" var="dto" varStatus="counter">
                ctx.fillRect(100 + ${counter.count} * (columnwidth + distan), 500, columnwidth, ${-dto} / max * 400);
                </c:forEach>
            </script>
        </c:if>
    </body>
</html>
