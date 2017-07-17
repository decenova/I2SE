<%-- 
    Document   : viewWaitingFood
    Created on : Jul 6, 2017, 9:46:19 PM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h4>Hi, ${STAFFID} [<a href="LogoutController">Logout</a>]</h4>
        <h1>Foods need to bring to customers</h1>
        <a href="ShowTableStatusController">back to table status</a>
        <div id="container"> 
        </div>
        <script>
            function getWaitingFood() {
                $.ajax({
                    url: "/LoadWaitingFoodController",
                    method: "POST",
                    success: function (data) {
                        var div = $("#container");
//                        data = JSON.parse(data);
                        var index;
                        for (index in data) {
                            var arr = data[index].foodWaiting;
                            if (arr.length > 0) {
                                var s = '';
                                s += '<table class="table table-hover">';
                                s += '<caption>Table No:' + data[index].tableID + ' | Order: ' + data[index].seq + '</caption>';
                                s += '<thead><tr><th>Food No</th><th>Food Name</th><th>Quantity</th><th>Action</th></tr></thead>';
                                s += '<tbody>';
                                for (var i = 0; i < arr.length; i++) {
                                    s += '<tr>';
                                    s += '<td>' + (i + 1) + '</td>';
                                    s += '<td>' + arr[i].foodName + '</td>';
                                    s += '<td>' + arr[i].quantity + '</td>';
                                    s += '<td>';
                                    s += '<form action="FoodDeliveredController" method="POST">';
                                    s += '<input type="submit" value="Delivered" />';
                                    s += '<input type="hidden" name="seqOD" value="' + arr[i].seqOD + '"/>';
                                    s += '<input type="hidden" name="seqOrder" value="' + data[index].seq + '"/>';
                                    s += '<input type="hidden" name="' + data[index].seq + '" value="${dto.beginEatTime}"/>';
                                    s += '<input type="hidden" name="tableId" value="' + data[index].tableID + '"/>';
                                    s += '</form>';
                                    s += '</td>';
                                    s += '</tr>';
                                }
                                s += '</tbody>';
                                s += '</table>';
                            }
                            div.append(s);
                        }
                    }
                });
            }
            getWaitingFood();
//            
//            function checkBringDone(seqOrder, foodId) {
//            $.ajax({
//                url: "/FoodDeliveredController",
//                method: "POST",
//                data: 
//                
//            });
//            }



        </script>
    </body>
</html>
