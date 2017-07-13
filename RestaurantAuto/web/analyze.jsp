<%-- 
    Document   : analyze
    Created on : 11-Jul-2017, 07:39:00
    Author     : Decen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analyze Page</title>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Analyze</h1>

        <form action="analyzeRevenueController" method="post">
            <input id="from" type="date" name="fromDate" required="true" 
                   <c:if test="${not empty requestScope.FROM}"> value="${requestScope.FROM}" </c:if> value = "2017-05-30"/>
                   <input id="to" type="date" name="toDate" required="true"
                   <c:if test="${not empty requestScope.TO}"> value="${requestScope.TO}" </c:if> value = "2017-06-10"/>
                   <input type="submit" value="Start Analyze"/>
            </form>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#Menu">Menu analyze</a></li>
            <li><a data-toggle="tab" href="#Waiter">Waiter</a></li>
            <li><a data-toggle="tab" href="#Cook">Cook</a></li>
            <li><a data-toggle="tab" href="#Busboy">Busboy</a></li>
            <li><a data-toggle="tab" href="#Host">Host</a></li>
        </ul>
        <div class="tab-content">
            <div id="Menu" class="tab-pane fade in active">
                <c:if test="${not empty requestScope.FOOD}">
                    <h1>Menu item</h1>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>Food</td>
                                <td>Sold Quantity</td>
                                <td>Total Revenue</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.FOOD}" var="dto">
                                <tr>
                                    <c:forEach items="${dto}" var="data">
                                        <td>${data}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id="Waiter" class="tab-pane fade">
                <c:if test="${not empty requestScope.WAITER}">
                    <h1>Waiter</h1>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>Staff Id</td>
                                <td>First name</td>
                                <td>Last name</td>
                                <td>Total order created</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.WAITER}" var="dto">
                                <tr>
                                    <c:forEach items="${dto}" var="data">
                                        <td>${data}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id="Cook" class="tab-pane fade">
                <c:if test="${not empty requestScope.COOK}">
                    <h1>Cook</h1>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>Staff Id</td>
                                <td>First name</td>
                                <td>Last name</td>
                                <td>Total food created</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.COOK}" var="dto">
                                <tr>
                                    <c:forEach items="${dto}" var="data">
                                        <td>${data}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id="Busboy" class="tab-pane fade">
                <c:if test="${not empty requestScope.BUSBOY}">
                    <h1>Busboy</h1>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>Staff Id</td>
                                <td>First name</td>
                                <td>Last name</td>
                                <td>Total table clean</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.BUSBOY}" var="dto">
                                <tr>
                                    <c:forEach items="${dto}" var="data">
                                        <td>${data}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id="Host" class="tab-pane fade">
                <c:if test="${not empty requestScope.HOST}">
                    <h1>Host</h1>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>Staff Id</td>
                                <td>First name</td>
                                <td>Last name</td>
                                <td>Total table clean</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.HOST}" var="dto">
                                <tr>
                                    <c:forEach items="${dto}" var="data">
                                        <td>${data}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
