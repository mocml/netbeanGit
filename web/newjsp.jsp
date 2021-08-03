<%-- 
    Document   : newjsp
    Created on : Aug 2, 2021, 4:29:55 PM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${num==12}">
                <p>day la 12</p>
            </c:when>
            <c:when test="${num>12}">
                <p>lon hon  12</p>
            </c:when>
            <c:when test="${num<12}">
                <p>nho  hon  12</p>
            </c:when>
            <c:otherwise>
                No comment sir...
            </c:otherwise>
        </c:choose>
    </body>
</html>
