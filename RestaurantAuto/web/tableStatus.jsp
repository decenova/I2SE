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
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

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
        <div class="card">
            <div class="card-block">
                <h4 class="card-title">Staff ID: ${STAFFID}</h4>
                <div class="card-text">
                    Role: ${ROLE}
                    <c:if test="${ROLE eq 'Waiter'}">
                        <a href="LoadWaitingFoodController">View waiting food</a>
                    </c:if>
                    <form action="MainController" method="GET">
                        <input class="btn btn-default" type="submit" name="action" value="Logout"/>
                    </form>
                </div>
            </div>
        </div>

        <div class="card-deck col-lg-12" id="cardBody">

            
        </div>
        <script>

            function gettable() {
                $.ajax({
                    url: "/ShowTableStatusController",
                    method: "GET",
                    success: function (data) {
                        var cardBody = $("#cardBody");
                        cardBody.empty();
                        var index;
                        var arr = JSON.parse(data);
                        for (index in arr) {
                            var item = arr[index];
//                            console.log(item.id + " " + item.tableStatus);
                            var s = '';

                            s += '<div class="card text-center col-xs-11 col-sm-5 col-md-3 col-lg-2">'
                                    + '<div class="card-block">'
                                    + '<h4 class="card-title">' + item.id + '</h4>'
                                    + '<p class="card-text">' + item.tableStatus + '</p>';



                            if (item.tableStatus == "Clean") {
                                s += '<div class="backgroundDiv backgroundDiv-clean"></div>';
                            } else if (item.tableStatus == "Dirty") {
                                s += '<div class="backgroundDiv backgroundDiv-dirty"></div>';
                            } else if (item.tableStatus == "Waiting") {
                                s += '<div class="backgroundDiv backgroundDiv-wating"></div>';
                            } else if (item.tableStatus == "Eating") {
                                s += '<div class="backgroundDiv backgroundDiv-eating"></div>';
                            }

                            if ('${sessionScope.ROLE}' == "Host" && item.tableStatus == "Clean") {
                                s += '<form action="ChangeTableStatusController" method="post">'
                                        + '<input type="hidden" name="tableId" value="' + item.id + '"/>'
                                        + '<input type="hidden" name="tableStatusId" value="1"/>'
                                        + '<input class="btn btn-success" type="submit" name="action" value="Change as Host"/>'
                                        + '</form>';
                            } else if ('${sessionScope.ROLE}' == "Busboy" && item.tableStatus == "Dirty") {
                                s += '<form action="ChangeTableStatusController" method="post">'
                                        + '<input type="hidden" name="tableId" value="' + item.id + '"/>'
                                        + '<input type="hidden" name="tableStatusId" value="4"/>'
                                        + '<input class="btn btn-success"  type="submit" name="action" value="Change as Busboy"/>'
                                        + '</form>';
                            } else if ('${sessionScope.ROLE}' == "Waiter" && item.tableStatus == "Waiting") {
                                s += '<form action="MainController" method="post">'
                                        + '<input type="hidden" name="staffId" value="${sessionScope.STAFFID}"/>'
                                        + '<input type="hidden" name="tableId" value="' + item.id + '"/>'
                                        + '<input type="hidden" name="tableStatusId" value="2"/>'
                                        + '<input class="btn btn-success"  type="submit" name="action" value="Create order as Waiter"/>'
                                        + '</form>';
                            } else {
                                s += '<form action="TableStatus" method="post">'
                                        + '<input class="btn btn-block"  type="submit" name="action" value="Just for seen" disabled="true"/>'
                                        + '</form>';
                            }

                            s += '</div>'
                                    + '</div>';


                            cardBody.append(s);

                        }

                    }
                })
            }

            gettable();
            setInterval(gettable, 1000);
        </script>
    </body>
</html>
