<%-- 
    Document   : OrderManager
    Created on : Jul 16, 2021, 3:22:14 PM
    Author     : vanhv
--%>

<%@page import="entity.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        body{
            background-image: url("IMAGE/background2.png");

        }
        .allOrderArea{ 
            float :right;
            background-color: white;
            width: 1200px;
        }
        .allOrderArea table{
            background-color: cornflowerblue;
            border: 2px solid white;
            width: 100%;
            text-align: center;
            border-collapse: collapse;
        }
        .allOrderArea th,td{
            border: 2px solid white ;
            height: 70px;
            color:white;

        }
        .btnDone{
            background-color: white;
            padding: 10px 20px 10px 20px;
        }
        .btnWait{
            background-color: white;
            box-shadow: 2px 3px 5px 3px #666666;
            padding: 10px 20px 10px 20px;
        }
        .allOrderAreall td{
            box-shadow: 1px 1px 2px 2px #666666;
        }

        .allOrderArea th{
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            cursor: pointer;
            box-shadow: 1px 1px 2px 2px #666666;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>
    </head>
    <body>
        <%ArrayList<Bill> listAllBill
                    = (ArrayList<Bill>) request.getAttribute("listAllBill");%>

        <div class="allOrderArea" style="margin-top: 60px;">
            <table id="myTable">
                <tr>
                    <th onclick="sortTableNumber(0)" style="width: 120px;">Cid</th>
                    <th onclick="sortTableString(1)">Oder ID</th>
                    <th onclick="sortTableString(2)">Order Date</th>
                    <th onclick="sortTableString(3)">Name</th>
                    <th onclick="sortTableString(4)">Address</th>
                    <th onclick="sortTableNumber(5)"style="width: 130px;">Total</th>
                    <th onclick="sortTableString(6)">Status</th>
                </tr>
                <%
                    for (Bill b : listAllBill) {%>
                <tr>
                    <td><%=b.getCid()%></td>
                    <td><a href="ControllerMyAccount?service=detail&oid=<%=b.getoID()%>"><%=b.getoID()%></a></td>  
                    <td><%=b.getDateCreate()%></td>
                    <td><%=b.getCname()%></td>
                    <td><%=b.getcAddress()%></td>
                    <td style="font-weight: bold;"><%=b.getTotal()%></td>
                    <td>
                        <%if (b.getStatus() == 2) {%>
                        <a style = "color: #00ccff;" class="btnDone"> Done</a >
                        <%} else if (b.getStatus() == 1) {%>
                        <a style = "color: goldenrod;" class="btnDone"> Waiting</a >
                        <%} else if (b.getStatus() == 0) {%>
                        <a href="ControllerAdmin?service=updateOrder&oid=<%=b.getoID()%>&stt=1" style = "color: green;" class="btnWait"> Accept</a >
                        <%}%>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
