<%-- 
    Document   : Panner
    Created on : Jul 3, 2021, 11:31:58 PM
    Author     : vanhv
--%>
<%@page import="entity.Customer"%>
<style>
    .navbar {
        position: fixed;
        background-color: cadetblue;
        width: 100%;
        float: left;
        left: 0;
        top: 0;
        z-index: 4;
        color: white;
        box-shadow: 2px 1px 1px 1px #666666;
        -webkit-user-select: none;  
        -moz-user-select: none;    
        -ms-user-select: none; 
    }
    a,
    a:active,
    a:visited {
        color: white;
        text-decoration: none;
    }

    .btnHome {
        float: left;
        padding: 20px 30px 20px 30px;
        display: inline;
        border-right: 1px solid white;
    }

    .active {
        background-color: cornflowerblue;
    }
    .btnShowCart{
        border-left:  1px solid white;
        float: right;
        padding: 20px 30px 20px 30px;
    }
    .btnLogin {
        border-left:  1px solid white;
        background-color: lightskyblue;
        float: right;
        padding: 20px 50px 20px 50px;
    }
    .txtSearch{
        background-color: white;
        padding: 18px 5px 19px 5px;
        width: 500px;
        border: none;
        outline: none;
        font-size: 18px;
        text-align: left;

    }
    .btnSearch{
        padding: 12px 15px 12px 15px;
        background: red; 
        border: none;
        color: white;
        outline: none;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Customer user = (Customer) session.getAttribute("user");
    String username = (String) session.getAttribute("username");
%>
<div class="navbar">
    <a class="btnHome active" href="ControllerProduct?service=productManager">Home</a>
    <a class="btnHome" href="#">Menu #1</a>
    <input class="txtSearch" placeholder="Search by name , id.." type="text" name="txtSearch" >
    <a class="btnSearch" href="#" type="submit" name="search" value="Search">Search</a>
    <%if (username == null) {%>
    <a href="Login.jsp" class="btnLogin">Login</a>
    <%} else {%>
    <a href="ControllerMyAccount?service=info" class="btnLogin"><%=username%></a>
    <%}%>  
    <!--    <a class="btnShowCart" href="ControllerCart?service=showcart">Show Cart</a>-->
</div>
