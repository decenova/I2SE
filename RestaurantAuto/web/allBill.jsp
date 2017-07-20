<%-- 
    Document   : allBill
    Created on : Jul 11, 2017, 3:14:11 PM
    Author     : kubin
--%>

<%@ taglib prefix="minh" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width"/>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>


    </head>
    <body>
        <h1>Casher Page!</h1>
        <form action="MainController" method="GET">
            <input class="btn btn-default" type="submit" name="action" value="Logout"/>
        </form>
        <a href="allBill.jsp">Reload</a>
        <table class="table table-hover" id="table">

        </table>
    </body>
    <script>
        function gettable() {
            $.ajax({
                url: "/BillController",
                method: "GET",
                success: function (data) {
                    var tbody = $("#table");
                    tbody.empty();
                    var s = '';
                    s += "<thead><th>Table Id</th><th>Order Id</th><th>Waiter serve</th><th>View detail</th></thead><tbody>";
                    var arr = JSON.parse(data);
                    for (index in arr) {
                        var item = arr[index];
                        s += "<tr>"
                                + "<td>" + item.tableId + "</td>"
                                + "<td>" + item.orderId + "</td>"
                                + "<td>" + item.waiterName + "</td>"
                                + "<td>"
                                + "<form action='MainController' method='POST'>"
                                + "<input type='hidden' name='orderId' value='" + item.orderId + "'/>"
                                + "<input type='hidden' name='tableId' value='" + item.tableId + "'/>"
                                + "<input type='submit' value='View Detail' name='action'/>"
                                + "</form>"
                                + "</td>"
                                + "</tr>";
                    }
                    s += "</tbody>";
                    tbody.append(s);
                }
            });
        }

        gettable();
        setInterval(gettable, 10000);
    </script>
</html>

