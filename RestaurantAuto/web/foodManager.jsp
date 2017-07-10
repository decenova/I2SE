<%-- 
    Document   : foodManager
    Created on : Jul 6, 2017, 12:24:01 AM
    Author     : kubin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h1>Update Food Page!</h1>
        <font color="green"><h2>${SUCCESS}</h2></font> 
        <font color="red"><h2>${FAIL}</h2></font>
            <minhnh:url var="insert" value="MainController">
                <minhnh:param name="txtFlag" value="Food"/>
                <minhnh:param name="action" value="InsertPage"/>
            </minhnh:url>
        <h4><a href="${insert}">Insert new Food</a></h4>
        <form action="MainController" method="POST" class="form-inline">
            <div class="form-group">            
                <label for="txtSearch">Search</label> 
                <input type="text" id="txtSearch" name="txtSearch" value="${lastSearchValue}"/>
            </div>
            <input type="hidden" name="txtFlag" value="Food"/>
            <input type="submit" name="action" value="Search"  class="btn btn-success"/>
        </form>
        <minhnh:if test="${not empty requestScope.FOODINFO}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>FoodId</th>
                        <th>Food Name</th>
                        <th>Cost</th>
                        <th>Food Type</th>
                        <th>Available</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    <minhnh:forEach var="dto" items="${requestScope.FOODINFO}" varStatus="counter">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.foodId}</td>
                            <td>${dto.foodName}</td>
                            <td>${dto.cost}</td>
                            <td>${dto.foodType}</td>
                            <td>
                                <minhnh:if test="${dto.available eq 'True'}">Con hang</minhnh:if>
                                <minhnh:if test="${dto.available eq 'False'}">Het hang</minhnh:if>                               
                                </td>
                                <td>
                                    <input type="hidden" value="${lastSearchValue}" name="lastSearchValue"/>
                                <input type="hidden" value="txtFood" name="flagUpdate"/>
                                <input type="hidden" value="${dto.foodId}" name="txtFoodID"/>
                                <input type="submit" value="Update" name="action"/>
                            </td>
                        </tr>
                    </form>
                </minhnh:forEach>
            </tbody>
        </table>
    </minhnh:if>
    <minhnh:if test="${not empty FOODVALUE}">
        <div class="col-md-2 col-lg-3"></div>
        <form class="col-xs-12 col-md-8 col-lg-6" method="POST" action="MainController" accept-charset="ISO-8859-1">
            <div class="form-group">
                <label for="FoodID">FoodID</label>
                <input type="text" name="txtFoodId" value="${FOODVALUE.foodId}" readonly class="form-control" id="FoodID">
            </div>
            <div class="form-group">
                <label for="Name">Name</label>
                <input type="text" name="txtFoodName" value="${FOODVALUE.foodName}" class="form-control" id="Name">
                <font color="red">${ERROR.foodNameErr}</font>
            </div>
            <div class="form-group">
                <label for="Cost">Cost</label>
                <input type="number" name="txtCost" value="${FOODVALUE.cost}" class="form-control" id="Cost">
                <font color="red">${ERROR.costErr}</font>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="ckAvailable" <minhnh:if test="${FOODVALUE.available eq 'True'}">checked</minhnh:if> />Available
                    </label>
                </div>
                <div class="form-group">
                    <label for="typeList">Type</label>
                    <select class="form-control" name="typeList" id="typeList">
                        <option value="1" <minhnh:if test="${FOODVALUE.foodType == 'Khai vị'}">selected</minhnh:if> >Khai vị</option>
                    <option value="2" <minhnh:if test="${FOODVALUE.foodType == 'Chính'}">selected</minhnh:if> >Chính</option>
                    <option value="3" <minhnh:if test="${FOODVALUE.foodType == 'Tráng miệng'}">selected</minhnh:if> >Tráng miệng</option>
                    <option value="4" <minhnh:if test="${FOODVALUE.foodType == 'Nước giải khát'}">selected</minhnh:if> >Nước giải khát</option>
                    <option value="5" <minhnh:if test="${FOODVALUE.foodType == 'Rượu'}">selected</minhnh:if> >Rượu</option>
                    <option value="6" <minhnh:if test="${FOODVALUE.foodType == 'Bia'}">selected</minhnh:if> >Bia</option>
                    </select>
                </div>
                <input type="hidden" value="Food" name="txtFlag"/>
                <input type="hidden" value="${lastSearchValue}" name="txtSearch"/>
            <input type="submit" name="action" value="Edit"  class="btn btn-default"/> 
        </form>
    </minhnh:if>
</body>
</html>
