<%-- 
    Document   : insert
    Created on : Jul 5, 2017, 11:20:44 PM
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
        <h1>Insert page!</h1>
        <form method="POST" action="MainController" accept-charset="ISO-8859-1">
            <minhnh:if test="${txtFlag eq 'Staff'}">
                <table>
                    <tr>
                        <td>StaffID</td>
                        <td><input type="text" name="txtStaffId"/> </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="txtPassword"/> </td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="txtFirstName"/></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="txtLastName"/> </td>
                    </tr>
                    <tr>
                        <td>Date Of Birth</td>
                        <td><input type="date" name="dateDOB"</td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="txtAddress"/> </td>
                    </tr>
                    <tr>
                        <td>Sex</td>
                        <td>
                            <input type="radio" name="cbSex" value="M" checked/>Male
                            <input type="radio" name="cbSex" value="F"/>Female
                        </td>
                    </tr>
                    <tr>
                        <td>Salary</td>
                        <td><input type="number" name="txtSalary"/> </td>
                    </tr>
                    <tr>
                        <td>Staff Role</td>
                        <td>
                            <select name="roleList">
                                <option value="1">Manager</option>
                                <option value="2">Host</option>
                                <option value="3">Waiter</option>
                                <option value="4">Busboy</option>
                                <option value="5">Cook</option>
                                <option value="6">Casher</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                            <input type="submit" name="action" value="Insert"/> 
                        </td>
                    </tr>
                </table>
            </minhnh:if>
            <minhnh:if test="${txtFlag eq 'Food'}">
                <table>
                    <tr>
                        <td>FoodID</td>
                        <td>
                            <input type="text" name="txtFoodId" required/>
                            <font color="red">${ERROR.foodIdErr}</font>
                            <font color="red">${ERROR.duplicateErr}</font>
                        </td>
                    </tr>                    
                    <tr>
                        <td>Name</td>
                        <td>
                            <input type="text" name="txtFoodName"/>
                            <font color="red">${ERROR.foodNameErr}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Cost</td>
                        <td>
                            <input type="number" name="txtCost"/>
                            <font color="red">${ERROR.costErr}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Available</td>
                        <td>
                            <input type="checkbox" name="ckAvailable" checked/>
                        </td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td>
                            <select name="typeList">
                                <option value="1">Khai vị</option>
                                <option value="2">Chính</option>
                                <option value="3">Tráng miệng</option>
                                <option value="4">Nước giải khát</option>
                                <option value="5">Rượu</option>
                                <option value="6">Bia</option>
                                </select>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                            <input type="submit" name="action" value="Insert"/> 
                        </td>
                    </tr>
                </table>
            </minhnh:if>
            <minhnh:if test="${txtFlag eq 'Table'}">
                <table>
                    <tr>
                        <td>TableID</td>
                        <td>
                            <input type="text" name="txtTableId" required/> 
                            <font color="red">${ERROR.tableId}</font>
                            <font color="red">${ERROR.duplicate}</font>
                        </td>
                    </tr>                    
                    <tr>
                        <td>Type</td>
                        <td>
                            <select name="statusList">
                                <option value="1">Clean</option>
                                <option value="2">Waiting</option>
                                <option value="3">Eating</option>
                                <option value="4">Dirty</option>
                                <option value="5">Disable</option>
                                </select>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="hidden" value="${txtFlag}" name="txtFlag"/>
                            <input type="submit" name="action" value="Insert"/> 
                        </td>
                    </tr>
                </table>
            </minhnh:if>
        </form>
    </body>
</html>
