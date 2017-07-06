<%-- 
    Document   : tableManager
    Created on : Jul 6, 2017, 2:17:27 AM
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
        <h1>Update Table Page!</h1>
        <font color="green"><h2>${SUCCESS}</h2></font> 
        <font color="red"><h2>${FAIL}</h2></font>
            <minhnh:url var="insert" value="MainController">
                <minhnh:param name="txtFlag" value="Table"/>
                <minhnh:param name="action" value="InsertPage"/>
            </minhnh:url>
        <h4><a href="${insert}">Insert new Table</a></h4>
        <form action="MainController" method="POST">
            Search <select name="txtSearch">
                <option value="1" <minhnh:if test="${lastSearchValue eq '1'}">selected</minhnh:if>>Clean</option>
                <option value="2" <minhnh:if test="${lastSearchValue eq '2'}">selected</minhnh:if>>Waiting</option>
                <option value="3" <minhnh:if test="${lastSearchValue eq '3'}">selected</minhnh:if>>Eating</option>
                <option value="4" <minhnh:if test="${lastSearchValue eq '4'}">selected</minhnh:if>>Dirty</option>
                <option value="5" <minhnh:if test="${lastSearchValue eq '5'}">selected</minhnh:if>>Disable</option>
                </select> <br/>
                <input type="hidden" name="txtFlag" value="Table"/>
                <input type="submit" name="action" value="Search"/>
            </form>
        <minhnh:if test="${not empty requestScope.TABLEINFO}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>TableId</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                    <minhnh:forEach var="dto" items="${requestScope.TABLEINFO}" varStatus="counter">
                    <form action="MainController" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td>${dto.tableStatus}</td>
                            <td>
                                <input type="hidden" value="${lastSearchValue}" name="lastSearchValue"/>
                                <input type="hidden" value="txtTable" name="flagUpdate"/>
                                <input type="hidden" value="${dto.id}" name="txtTableID"/>
                                <input type="submit" value="Update" name="action"/>
                            </td>
                        </tr>
                    </form>
                </minhnh:forEach>
            </tbody>
        </table>
    </minhnh:if>
    <minhnh:if test="${not empty TABLEVALUE}">
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Table Id </td>
                    <td><input type="text" value="${TABLEVALUE.id}" name="txtTableId" readonly/> </td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="statusList">
                            <option value="1" <minhnh:if test="${TABLEVALUE.tableStatus eq 'Clean'}">selected</minhnh:if>>Clean</option>
                            <option value="2" <minhnh:if test="${TABLEVALUE.tableStatus eq 'Waiting'}">selected</minhnh:if>>Waiting</option>
                            <option value="3" <minhnh:if test="${TABLEVALUE.tableStatus eq 'Eating'}">selected</minhnh:if>>Eating</option>
                            <option value="4" <minhnh:if test="${TABLEVALUE.tableStatus eq 'Dirty'}">selected</minhnh:if>>Dirty</option>
                            <option value="5" <minhnh:if test="${TABLEVALUE.tableStatus eq 'Disable'}">selected</minhnh:if>>Disable</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="hidden" value="Table" name="txtFlag"/>
                            <input type="hidden" value="${lastSearchValue}" name="txtSearch"/>
                        <input type="submit" value="Edit" name="action"/> 
                    </td>
                </tr>
            </table>
        </form>
    </minhnh:if>
</body>
</html>
