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
        <title>Table Status Page</title>
        <meta name="viewport" content="width=device-width"/>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" href="bootstrap/js/bootstrap.min.js"></script>
        <style>
            .card-deck {
                padding: 0px;
            }
            .card-deck div.card {
                border: 1px solid #000;
                height: 120px;
                border-radius: 10px; 
                margin: 1% 4%;
                position: relative;
                overflow: hidden;
            }
            .card-deck form {
                z-index: 10;
            }
            .backgroundDiv {
                position: absolute;
                top: 0%;
                left: 0%;
                right: 0%;
                bottom: 0%;
                background-color: rgba(0,0,0,0.1);
                border-radius: 10px;
                z-index: -1;
            }
            .backgroundDiv-clean{
                background-color: rgba(0,255,0,0.5);
            }
            .backgroundDiv-dirty{
                background-color: rgba(255,100,100,0.5);
            }
            .backgroundDiv-wating{
                background-color: rgba(100,100,255,0.5);
            }
            .backgroundDiv-eating{
                background-color: rgba(255,255,0,0.5);
            }
        </style>
    </head>
    <body>
        <h1>Table status</h1>
        <p>Role: ${ROLE}</p>
        <div class="card">
            <div class="card-block">
                <h4 class="card-title">Staff ID: ${STAFFID}</h4>
                <p class="card-text">
                    Role: ${ROLE}
                    <a href="LoadWaitingFoodController">View waiting food</a>
                    <c:if test="${ROLE eq 'Waiter'}">
                        <a href="LoadWaitingFoodController">View waiting food</a>
                    </c:if>
                    
                <form action="MainController" method="GET">
                    <input class="btn btn-default" type="submit" name="action" value="Logout"/>
                </form>
                </p>
            </div>
        </div>
        <div class="card-deck col-lg-12">
            <c:forEach var="table" items="${TABLES}">
                <div class="card text-center col-xs-11 col-sm-5 col-md-3 col-lg-2">
                    <div class="card-block">
                        <h4 class="card-title">${table.id}</h4>
                        <p class="card-text">${table.tableStatus}</p>
                        <c:choose>
                            <c:when test="${table.tableStatus eq 'Clean'}">
                                <div class="backgroundDiv backgroundDiv-clean"></div>
                            </c:when>
                            <c:when test="${table.tableStatus eq 'Dirty'}">
                                <div class="backgroundDiv backgroundDiv-dirty"></div>
                            </c:when>
                            <c:when test="${table.tableStatus eq 'Waiting'}">
                                <div class="backgroundDiv backgroundDiv-wating"></div>
                            </c:when>
                            <c:when test="${table.tableStatus eq 'Eating'}">
                                <div class="backgroundDiv backgroundDiv-eating"></div>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${ROLE eq 'Host' && table.tableStatus eq 'Clean'}">
                                <form action="ChangeTableStatusController" method="post">                               
                                    <input type="hidden" name="tableId" value="${table.id}"/>
                                    <input type="hidden" name="tableStatusId" value="1"/>
                                    <input class="btn btn-success" type="submit" name="action" value="Change as Host"/>
                                </form>
                            </c:when>
                            <c:when test="${ROLE eq 'Busboy' && table.tableStatus eq 'Dirty'}">
                                <form action="ChangeTableStatusController" method="post">
                                    <input type="hidden" name="tableId" value="${table.id}"/>
                                    <input type="hidden" name="tableStatusId" value="4"/>
                                    <input class="btn btn-success"  type="submit" name="action" value="Change as Busboy"/>
                                </form>
                            </c:when>
                            <c:when test="${ROLE eq 'Waiter' && table.tableStatus eq 'Waiting'}">
                                <form action="MainController" method="post">
                                    <input type="hidden" name="staffId" value="${STAFFID}"/>
                                    <input type="hidden" name="tableId" value="${table.id}"/>
                                    <input type="hidden" name="tableStatusId" value="2"/>
                                    <input class="btn btn-success"  type="submit" name="action" value="Create order as Waiter"/>
                                </form>
                            </c:when>
                            <c:when test="${ROLE eq 'Waiter' && table.tableStatus eq 'Eating'}">
                                <form action="ChangeTableStatusController" method="post">
                                    <input type="hidden" name="tableId" value="${table.id}"/>
                                    <input type="hidden" name="tableStatusId" value="3"/>
                                    <input class="btn btn-success"  type="submit" name="action" value="Change as Waiter"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="TableStatus" method="post">
                                    <input class="btn btn-block"  type="submit" name="action" value="Just for seen" disabled="true"/>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <p></p>
                    </div>
                </div>
            </c:forEach>
        </div>



    </body>
</html>
