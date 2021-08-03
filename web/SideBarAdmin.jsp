<%-- 
    Document   : Sidebar
    Created on : Jul 3, 2021, 11:36:12 PM
    Author     : vanhv
--%>
<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<style>

    .sidebar {
        overflow: auto;
        position: fixed;
        float: left;
        background-color: lightsalmon;
        height: 90%;
    }

    .sidebar a {
        display: block;
        padding: 40px 5px 40px 0;
        border-bottom: 1px solid white;
    }

    .sidebar{
        padding: 26px;
        color: white; 
    }
    a,
    a:active,
    a:visited {
        color: white;
        text-decoration: none;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Category> listCategory
            = (ArrayList<Category>) request.getAttribute("listCategory");
%>
<div class="sidebar">
    <a href="ControllerProduct?service=productManager">ALL Product</a>
    <%for (Category cate : listCategory) {%>
    <a href="ControllerProduct?service=productManager&cate=<%=cate.getCateID()%>"><%= cate.getCateName()%></a>
    <%}%>
</div>
