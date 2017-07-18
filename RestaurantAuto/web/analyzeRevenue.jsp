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

        <a href="ManagerController">Back to manager page</a>
        <form action="analyzeRevenueController" method="post">
            <input id="from" type="date" name="fromDate" required="true" 
                   <c:if test="${not empty requestScope.FROM}"> value="${requestScope.FROM}" </c:if> value = "2017-05-30"/>
                   <input id="to" type="date" name="toDate" required="true"
                   <c:if test="${not empty requestScope.TO}"> value="${requestScope.TO}" </c:if> value = "2017-06-10"/>
                   <input type="submit" value="Start Analyze"/>
            </form>
            <h3>Total Revenue: ${requestScope.REVENUE}</h3>
        <h3>Average turnaround time: ${requestScope.SPEND}</h3>
        <h3>Average preparation time: ${requestScope.SERVER}</h3>
        <c:if test="${not empty requestScope.REVENUELIST}">
            <canvas id="myCanvas" width="800" height="600"></canvas>
            <script>
                var canvas = document.getElementById("myCanvas");
                var ctx = canvas.getContext("2d");
                ctx.fillStyle = "#8888ff";
                ctx.beginPath();
                ctx.moveTo(90, 500);
                ctx.lineTo(90, 100);
                ctx.moveTo(90, 500);
                ctx.lineTo(700, 500);
                ctx.stroke();
                var max = ${requestScope.MAX};
                var columnwidth = 600 / ${requestScope.RANK};
                var distan = columnwidth / (${requestScope.RANK} - 1);
                var bonus = Math.pow(10, Math.floor(getBaseLog(10, max)));
                ctx.font = '20px sans-serif';
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
        <c:if test="${not empty requestScope.FOOD}">
            <canvas id="myFoodCanvas" width="800" height="600"></canvas>
            <script>
                var myCanvas = document.getElementById("myFoodCanvas");
                function drawPieSlice(ctx, centerX, centerY, radius, startAngle, endAngle, color) {
                    ctx.fillStyle = color;
                    ctx.beginPath();
                    ctx.moveTo(centerX, centerY);
                    ctx.arc(centerX, centerY, radius, startAngle, endAngle);
                    ctx.closePath();
                    ctx.fill();
                }
                function drawInfo(ctx, x, y, categ, color) {
                    ctx.fillStyle = color;
                    ctx.font = '20px sans-serif';
                    ctx.fillStyle = "#000000";
                    ctx.fillText(categ, x, y + 8);
                    ctx.fillStyle = color;
                    x -= 10;
                    ctx.fillRect(x - 5, y - 5, 10, 10);
                }
                var myVinyls = {
                <c:forEach items="${requestScope.FOOD}" var="dto" varStatus="counter">
                    "${dto.get(0)}": ${dto.get(2)},
                </c:forEach>
                };
                var checkDraw = true;
                var Piechart = function (options) {
                    this.options = options;
                    this.canvas = options.canvas;
                    this.ctx = this.canvas.getContext("2d");
                    this.colors = options.colors;
                    this.draw = function () {
                        var total_value = 0;
                        var color_index = 0;
                        for (var categ in this.options.data) {
                            var val = this.options.data[categ];
                            total_value += val;
                        }

                        var start_angle = 0;
                        var pos = 0;
                        for (categ in this.options.data) {
                            val = this.options.data[categ];
                            var slice_angle = 2 * Math.PI * val / total_value;
                            drawPieSlice(
                                    this.ctx,
                                    this.canvas.width / 2 + 50,
                                    this.canvas.height / 2,
                                    0.8 * Math.min(this.canvas.width / 2, this.canvas.height / 2),
                                    start_angle,
                                    start_angle + slice_angle,
                                    this.colors[color_index % this.colors.length]
                                    );
                            start_angle += slice_angle;
                            if (color_index <= 4) {
                                drawInfo(this.ctx, 20, 25 + pos * 20, categ, this.colors[color_index % this.colors.length]);
                                color_index++;
                                pos++;
                            } else if (checkDraw) {
                                checkDraw = false;
                                drawInfo(this.ctx, 20, 25 + pos * 20, "Other", this.colors[color_index % this.colors.length]);
                            }
                        }
                        drawPieSlice(
                                this.ctx,
                                this.canvas.width / 2 + 50,
                                this.canvas.height / 2,
                                0.4 * Math.min(this.canvas.width / 2, this.canvas.height / 2),
                                0,
                                2 * Math.PI,
                                "#ffffff"
                                );

                    };
                };
                var myPiechart = new Piechart(
                        {
                            canvas: myCanvas,
                            data: myVinyls,
                            colors: ["#fde23e", "#f16e23", "#57d9ff", "#937e88", "#8888ff", "#333388"]
                        }
                );
                myPiechart.draw();
            </script>
        </c:if>
    </body>
</html>
