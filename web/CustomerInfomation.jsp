<%-- 
    Document   : CustomerInfomation
    Created on : Jul 16, 2021, 7:24:15 PM
    Author     : vanhv
--%>

<%@page import="entity.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%Customer user = (Customer) request.getAttribute("user");
%>
<style>
    .infoArea{
        -webkit-user-select: none;  
        -moz-user-select: none;    
        -ms-user-select: none; 
        height: 280px;
    }
    .textf{
        padding-left: 5px;
    }
    .infoArea a{
        padding: 10px 20px 10px 20px ;
        background-color: red;
        color: white;
        box-shadow: 1px 1px 3px 2px #666666;
    }
</style>
<div class="infoArea">
    <h1 style="margin-left: 80px;">Account Information</h1>
    <table style="  margin-left: 60px;">
        <tr>
            <td class="txt">Name :</td>
            <td> <input class="inputText textf" type="text" value="<%=user.getCname()%>" name="name"></td>
        </tr>
        <tr>
            <td class="txt">Number Phone :</td>
            <td> <input class="inputText textf" type="text" value="<%=user.getCphone()%>" name="phone"></td>
        </tr>
        <tr>
            <td class="txt">Address :</td>
            <td> <input class="inputText textf" type="text" value="<%=user.getcAddress()%>" name="address"></td>
        </tr>
        <tr>
            <td class="txt">Gender :</td>
            <td>
                Male <input type="radio" value="1" <%=user.getStatus() == 1 ? "checked" : ""%> name="gender"  > 
                Female <input type="radio" value="0"  <%=user.getStatus() == 0 ? "checked" : ""%> name="gender">
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input style="background: cornflowerblue;color: white" type="submit" value="Update" name="service" />
                <input type="reset" value="Reset" />
            </td>
        </tr>
        <tr>
            <td>
                <a style="color: white" href="Login?login=logOut" >LogOut</a>
            </td>
        </tr>
    </table>
    <input type="hidden" value="<%=user.getUsername()%>" name="username">
</div>
