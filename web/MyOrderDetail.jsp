<%-- 
    Document   : MyOrderDetail
    Created on : Jul 16, 2021, 7:11:49 PM
    Author     : vanhv
--%>

<%@page import="entity.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .allOrderArea th{
        box-shadow: 2px 1px 2px 1px #666666;
        cursor: pointer;
        -webkit-user-select: none;  
        -moz-user-select: none;    
        -ms-user-select: none; 
    }
</style>
<%
    ArrayList<Cart> listCart = (ArrayList<Cart>) request.getAttribute("listCart");
%>

<div class="allOrderArea">
    <table id="myTable">
        <tr>
            <th onclick="sortTableString(0)">Product ID</th>
            <th>Order Date</th>
            <th onclick="sortTableString(2)">Product Name</th>
            <th onclick="sortTableNumber(3)">Quantity</th>
            <th onclick="sortTableNumber(4)">Price</th>
            <th onclick="sortTableString(5)">Total</th>
        </tr>
        <%for (Cart c : listCart) {%>
        <tr>
            <td><%=c.getpId()%></td>
            <td><%=c.getDate()%></td>
            <td><%=c.getpName()%></td> 
            <td> <%=c.getcQuantity()%></td>
            <td><%=c.getPrice()%></td>
            <td><%=c.getTotal()%></td>
        </tr>
        <%}%>
    </table>
</div>
