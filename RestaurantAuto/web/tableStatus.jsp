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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Table status</h1>
        <p>Role: ${ROLE}</p>
        <table style="border: 1px black solid">
            <thead>
            <th>Table</th>
            <th>Status</th>
            <th>Change Status</th>
        </thead>
        <c:forEach var="table" items="${TABLES}">
            <tr>
                <td>${table.id}</td>
                <td>${table.tableStatus}</td>
                <td>
                    <c:choose>
                        <c:when test="${ROLE eq 'Host' && table.tableStatus eq 'Clean'}">
                            <form action="TableStatus" method="GET">
                                <input type="hidden" name="tableId" value="${table.id}"/>
                                <input type="hidden" name="tableStatusId" value="1"/>
                                <input type="submit" name="action" value="Change as Host"/>
                            </form>
                        </c:when>
                        <c:when test="${ROLE eq 'Busboy' && table.tableStatus eq 'Dirty'}">
                            <form action="TableStatus" method="GET">
                                <input type="hidden" name="tableId" value="${table.id}"/>
                                <input type="hidden" name="tableStatusId" value="4"/>
                                <input type="submit" name="action" value="Change as Busboy"/>
                            </form>
                        </c:when>
                        <c:when test="${ROLE eq 'Waiter' && table.tableStatus eq 'Waiting'}">
                            <form action="TableStatus" method="GET">
                                <input type="hidden" name="tableId" value="${table.id}"/>
                                <input type="hidden" name="tableStatusId" value="2"/>
                                <input type="submit" name="action" value="Change as Waiter"/>
                            </form>
                        </c:when>
                        <c:when test="${ROLE eq 'Waiter' && table.tableStatus eq 'Eating'}">
                            <form action="TableStatus" method="GET">
                                <input type="hidden" name="tableId" value="${table.id}"/>
                                <input type="hidden" name="tableStatusId" value="3"/>
                                <input type="submit" name="action" value="Change as Waiter"/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="TableStatus" method="GET">
                                <input type="submit" name="action" value="Just for seen" disabled="true"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>


    </table>
</body>
</html>
