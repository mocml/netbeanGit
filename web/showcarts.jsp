<%-- 
    Document   : showcarts
    Created on : Jul 15, 2021, 12:09:05 PM
    Author     : vanhv
--%>

<%@page import="entity.Product"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.BigInteger"%>
<%@page import="entity.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        td,
        th {
            border: 2px solid white ;
            height: 70px;
            color:white;
        }

        th {
            font-size: 20px;
        }
        input{
            font-size: 18px;
            width: 70px;
            text-align: center;
        }
        .show{
            margin-bottom: 10px;
        }
        b{
            color: red;
        }
        .containerShowCart{
            position: relative;
            background-image: url("IMAGE/background2.png");
            min-height: 101%;   
            margin-top: 100px;
        }
        .containerShowCart th{
            box-shadow:1px 1px 2px 1px #666666;
            cursor: pointer;
            -webkit-user-select: none;  
            -moz-user-select: none;    
            -ms-user-select: none;  
        }
        .btnUpdate{
            font-size: 16px;
            margin-left: 20px;
            border: none;
            box-shadow:2px 2px 2px 1px #666666;
            padding: 15px 40px 15px 40px;
            color: white;
            background-color: deepskyblue;
        }
        .btnBack{
            margin-left: 50px;
            box-shadow:2px 2px 2px 1px #666666;
            background-color: black;
            padding: 13px 20px 15px 20px;
        }
        .btnCheckOut{
            box-shadow: 2px 2px 2px 1px #666666;
            float: right;
            margin-right: 22px;
            padding: 15px 40px 12px 40px;
            background-color: #2eaf55;
        }
        .btnRemoveAll{
            box-shadow: 2px 2px 2px 1px #666666;
            float: right;
            margin-right: 30px;
            padding: 15px 40px 12px 40px;
            background-color:red;
        }
        .btnBack,.btnCheckOut, .btnRemoveAll,.btnUpdate{
            -webkit-user-select: none;  
            -moz-user-select: none;    
            -ms-user-select: none;  
        }
        table{
            width: 98%;
            margin-left: 15px;
        }
        .allBtn{
            margin-top: 110px;
            display: block;
        }
        label{
            font-size: 18px;
            font-weight: bold;
            float: right ;
            padding: 20px 40px 20px 30px ;
            color: red;
            background-color: white;
            margin: 20px 29px;
            box-shadow: 3px 2px 3px 1px #666666;
        }
    </style>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="containerShowCart">
        <%
            ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("listCart");
            ArrayList<Product> maxQuantity = (ArrayList<Product>) request.getAttribute("maxQuantity");
            BigDecimal grandTotal = new BigDecimal("0");
        %>
        <form action="ControllerCart" method="POST">
            <table id="myTable">
                <tr>
                    <th onclick="sortTableString(0)">Product ID</th>
                    <th onclick="sortTableNumber(1)">Order Date</th>
                    <th onclick="sortTableString(2)">Product Name</th>
                    <th onclick="sortTableString(3)">Quantity</th>
                    <th onclick="sortTableNumber(4)">Price</th>
                    <th onclick="sortTableNumber(5)">Total</th>
                    <th>Remove</th>
                </tr>
                <%for (Cart c : listCart) {%>
                <tr>
                    <td><%=c.getpId()%></td>
                    <td><%=c.getDate()%></td>
                    <td><%=c.getpName()%></td>
                    <td>
                        <%for (Product p : maxQuantity) {%>
                        <%if (c.getpId().equals(p.getPid())) {%>
                        <input type="number" min="1" max="<%=p.getQuantity()%>" value="<%=c.getcQuantity()%>" name="p<%=c.getpId()%>">
                        <% } %>
                        <%}%>
                    </td>
                    <td><%=c.getPrice()%></td>
                    <td><%=c.getTotal()%></td>
                    <td><a  style="text-decoration: none;color :red;
                            padding: 10px 15px 10px 15px;
                            box-shadow: 1px 2px 1px 2px #666666;
                            background-color: white" 
                            href="ControllerCart?service=remove&id=<%=c.getpId()%>">
                            Remove</a></td>
                </tr>
                <%
                    String getTotal = String.valueOf(c.getTotal());
                    BigDecimal total = new BigDecimal(getTotal);
                    grandTotal = grandTotal.add(total);
                %>

                <%}%>
            </table>
            <label>Cart Total : <%=grandTotal.toString()%> $</label>
            <input type="hidden" name="service" value="update">
            <div class="allBtn">
                <a class="btnBack" href="ControllerProduct">← Continue Shopping</a>
                <button class="btnUpdate">Update</button>
                <a class="btnCheckOut" href="ControllerCart?service=checkout">Check Out →</a>
                <a class="btnRemoveAll" href="ControllerCart?service=removeAll">Remove All</a>
            </div>
        </form>

    </body>
</html>
