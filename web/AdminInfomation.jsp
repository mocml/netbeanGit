<%-- 
    Document   : AdminInfomation
    Created on : Jul 16, 2021, 7:25:47 PM
    Author     : vanhv
--%>

<%@page import="entity.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .infoAreaAdmin{
        float: left;
        margin-top: 60px;
        margin-left: 20px;
        width: 25%;
        position: fixed;
        background-color: white;
        box-shadow: 2px 7px 7px #666666;
        -webkit-user-select: none;  
        -moz-user-select: none;    
        -ms-user-select: none; 
    }
    button {
        background-color: red;
        padding: 5px 10px 5px 10px;
    }
    .infoAreaAdmin th,td{
        border :none;
    }
    .choosen{
        display: block;
    }

    .btnChoosen{
        box-shadow: 2px 1px 3px 3px #666666;
        background-color: deepskyblue;
        padding: 15px 20px 15px 20px;
        margin: 20px 80px 0 10px;;
        color: white;
    }
    .btnLogOut{
        padding: 10px 0px 10px 0px;
        margin : 40px 100px 20px 40px;
        box-shadow: 2px 2px 3px 1px #666666;
        border: none;
    }
</style>
<%Admin admin = (Admin) request.getAttribute("user");%>
<div class="infoAreaAdmin">
    <h1 style="margin-left: 150px;">Admin Manager</h1>

    <h2 style="margin-left: 50px">Admin : <%=admin.getUsername()%></h2>
    <div class="choosen">

        <a class="btnChoosen" style="margin-left: 60px;"  href="#accountManager" >ACCOUNT</a>


        <a class="btnChoosen"  href="ControllerProduct?service=productManager" >PRODUCT</a>

    </div>
    <button class="btnLogOut">
        <a style="color: white;padding: 10px 25px 10px 25px; " href="Login?login=logOut" >LogOut</a>
    </button>

    <input type="hidden" value="<%=admin.getUsername()%>" name="username">
</div>