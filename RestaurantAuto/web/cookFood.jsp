<%-- 
    Document   : cookFood
    Created on : Jul 6, 2017, 12:35:34 AM
    Author     : hoanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h4>Hi, ${STAFFID}</h4>
        <input type="hidden" name="staffID" value="${STAFFID}" />
        <input type="submit" value="Submit Food" /> <br/> <br/>
    <c:forEach items="${listChooseFood}" var="dto">
        <table border=1 style="width:25%; float: left">
            <caption>Table No: ${dto.tableID} Order: ${dto.seq}</caption>
            <thead>
                <tr>
                    <th>Food No</th>
                    <th>Food Name</th>
                    <th>Quantity</th>
                    <th>Choice</th>
                </tr>
            </thead>
            <tbody>


        </table>
    </c:forEach>
</body>
</html>
