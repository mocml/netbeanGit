<%-- 
    Document   : Home
    Created on : Jul 3, 2021, 11:31:48 PM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME</title>
        <link rel="stylesheet" href="CSS/Main.css">
        <link rel="stylesheet" href="CSS/Panner.css">
        <link rel="stylesheet" href="CSS/Sidebar.css">
        <link rel="stylesheet" href="CSS/Content.css">
    </head>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
            <div class="container">
            <jsp:include page="Sidebar.jsp"></jsp:include>
            <jsp:include page="Content.jsp"></jsp:include>
        </div>
    </body>
</html>
