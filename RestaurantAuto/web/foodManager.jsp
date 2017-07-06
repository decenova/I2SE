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
        <form action="MainController" method="POST">
            Search <input type="text" name="txtSearch" value="${lastSearchValue}"/> <br/>
            <input type="hidden" name="txtFlag" value="Food"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <minhnh:if test="${not empty requestScope.FOODINFO}">
            <table border="1">
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
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Food Id </td>
                    <td><input type="text" value="${FOODVALUE.foodId}" name="txtFoodId" readonly/> </td>
                </tr>
                <tr>
                    <td>Food Name </td>
                    <td><input type="text" value="${FOODVALUE.foodName}" name="txtFoodName"/> </td>
                </tr>
                <tr>
                    <td>Cost </td>
                    <td><input type="number" value="${FOODVALUE.cost}" name="txtCost"/> </td>
                </tr>
                <tr>
                    <td>Available</td>
                    <td>
                        <input type="checkbox" name="ckAvailable" 
                               <minhnh:if test="${FOODVALUE.available eq 'True'}">checked</minhnh:if> />
                        </td>
                    </tr>
                    <tr>
                        <td>Type </td>
                        <td>
                            <select name="typeList">
                                <option value="1" <minhnh:if test="${FOODVALUE.foodType == 'Khai vị'}">selected</minhnh:if>>Khai vị</option>
                            <option value="2" <minhnh:if test="${FOODVALUE.foodType == 'Chính'}">selected</minhnh:if>>Chính</option>
                            <option value="3" <minhnh:if test="${FOODVALUE.foodType == 'Tráng miệng'}">selected</minhnh:if>>Tráng miệng</option>
                            <option value="4" <minhnh:if test="${FOODVALUE.foodType == 'Nước giải khát'}">selected</minhnh:if>>Nước giải khát</option>
                            <option value="5" <minhnh:if test="${FOODVALUE.foodType == 'Rượu'}">selected</minhnh:if>>Rượu</option>
                            <option value="6" <minhnh:if test="${FOODVALUE.foodType == 'Bia'}">selected</minhnh:if>>Bia</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="hidden" value="Food" name="txtFlag"/>
                            <input type="hidden" value="${lastSearchValue}" name="txtSearch"/>
                        <input type="submit" value="Edit" name="action"/> 
                    </td>
                </tr>
            </table>
        </form>
    </minhnh:if>
</body>
</html>
