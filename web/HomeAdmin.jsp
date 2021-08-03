<%-- 
    Document   : HomeAdmin
    Created on : Jul 10, 2021, 11:33:41 PM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME Admin</title>
        <link rel="stylesheet" href="CSS/Main.css">
        <link rel="stylesheet" href="CSS/Panner.css">
        <link rel="stylesheet" href="CSS/Sidebar.css">
        <link rel="stylesheet" href="CSS/Content.css">
    </head>
    <body>
        <jsp:include page="PannerAdmin.jsp"></jsp:include>
            <div class="container">
            <jsp:include page="SideBarAdmin.jsp"></jsp:include>
            <jsp:include page="ContentAdmin.jsp"></jsp:include>
        </div>
    </body>
</html>

