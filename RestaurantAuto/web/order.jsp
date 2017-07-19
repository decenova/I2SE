<%-- 
    Document   : order
    Created on : Jun 18, 2017, 5:05:39 PM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <meta name="viewport" content="width=device-width"/>
        <link type="text/css" href="bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="fontawesome/css/font-awesome.min.css" rel="stylesheet"/>
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Order</h1>
        <a href="tableStatus.jsp">Back to table status</a>
        <p>Table No: ${tableID}</p>
        <p>Waiter ID: ${STAFFID}</p>
        <p>Order No: ${orderSeq}</p>
        <p>Date: ${DATE}</p>
        <div class="col-lg-6">
            <table class="table table-hover">
                <caption>Menu</caption>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${MENU}" var="dto" varStatus="counter">
                    <form action="MainController" method="POST"  accept-charset="ISO-8859-1">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.foodID}</td>
                            <td>${dto.foodName}</td>
                            <td>
                                <input type="number" id="${dto.foodID}" name="txtQuantity" value="1" min="1" required />
                            </td>
                            <td>
                                <input type="button" value="Add" onclick="addOrder('${dto.foodID}', '${dto.foodName}')" class="btn btn-primary" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <c:if test="${not empty FoodCooking}">
            <div class="col-lg-6">
                <table class="table table-hover">
                    <caption>Foods is cooking</caption>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Food ID</th>
                            <th>Food Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${FoodCooking}" var="dto" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.foodID}</td>
                                <td>${dto.foodName}</td>
                                <td>${dto.quantity}</td>
                                <td>Disable</td>
                            </tr>
                        </c:forEach>
                    </tbody>                   
                </table>                
            </div>
        </c:if>
        <div class="col-lg-6" id="container">

        </div>     
        <script>
            function addOrder(id, name) {
                $.ajax({
                    url: "/SubmitOrderController",
                    method: "POST",
                    data: "action=Add&txtFoodID=" + id + "&txtFoodName="
                            + name + "&txtQuantity=" + document.getElementById(id).value,
                    success: function (data) {
                        getData(data);
                    }
                });
            }
            function removeOrder(number) {
                $.ajax({
                    url: "/SubmitOrderController",
                    method: "POST",
                    data: "action=Remove&FoodNo=" + number,
                    success: function (data) {
                        getData(data);
                    }
                });
            }

            function getData() {
                $.ajax({
                    url: "/SubmitOrderController",
                    method: "POST",
                    data: "action=GetData",
                    success: function (data) {
                        var div = $("#container");
                        div.empty();
                        var s = '';
                        s += '<form action="MainController" method="POST" accept-charset="ISO-8859-1">';
                        if (data.length > 0) {
                            s += '<table class="table table-hover">';
                            s += '<caption>Order Submit</caption>';
                            s += '<thead><tr><th>No</th><th>Food ID</th><th>Food Name</th><th>Quantity</th><th>Action</th></tr></thead>';
                            s += '<tbody>';
                            for (var i = 0; i < data.length; i++) {
                                s += '<tr>';
                                s += '<td>' + (i + 1) + '</td>';
                                s += '<td>' + data[i].foodID + '</td>';
                                s += '<td>' + data[i].foodName + '</td>';
                                s += '<td>' + data[i].quantity + '</td>';
//                                s += '<td><a href="SubmitOrderController?action=Remove&FoodNo=' + (i + 1) +'">Remove</a></td>';
                                s += '<td><input type="button" value="Remove" onclick="removeOrder(' + (i + 1) + ' )" class="btn btn-danger" /></td>';
                                s += '</tr>';
                            }
                            s += '</tbody></table>';
                            s += '<input type="hidden" name="txtSEQOrder" value="${orderSeq}"/>';
                            s += '<input type="submit" name="action" value="Submit order" />';
                            s += '</form>';
                        }
                        div.append(s);
                    }

                });

            }

            getData();
        </script>

    </body>
</html>
