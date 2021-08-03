<%-- 
    Document   : MyOrder
    Created on : Jul 16, 2021, 5:49:01 PM
    Author     : vanhv
--%>

<%@page import="entity.Bill"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .allOrderArea th{
        cursor: pointer;
        box-shadow: 1px 1px 2px 1px #666666;
    }
</style>
<% ArrayList<Bill> listBill = (ArrayList<Bill>) request.getAttribute("listBill");%>

<div class="allOrderArea">
    <table id="myTable">
        <tr>
            <th onclick="sortTableString(0)">Oder ID</th>
            <th onclick="sortTableString(1)">Order Date</th>
            <th onclick="sortTableNumber(2)">Address</th>
            <th onclick="sortTableNumber(3)">Total</th>
            <th onclick="sortTableString(4)">Status</th>
        </tr>
        <%for (Bill b : listBill) {%>
        <tr>
            <td><a href="ControllerMyAccount?service=detail&oid=<%=b.getoID()%>"><%=b.getoID()%></a></td>
            <td><%=b.getDateCreate()%></td>
            <td><%=b.getcAddress()%></td> 
            <td><%=b.getTotal()%></td>
            <td>
                <%if (b.getStatus() == 2) {%>
                <a  style = "color: #00ccff;" class="btnDone"> Done</a >
                <% } else if (b.getStatus() == 1) {%>
                <a href="ControllerAdmin?service=customerConfirm&oid=<%=b.getoID()%>&stt=2" style = "color: green;" class="btnCancel"> Confirm</a >
                <% } else if (b.getStatus() == 0) {%>
                <a href="ControllerCart?service=cancel&oid=<%=b.getoID()%>" style = "color: red;" class="btnCancel"> Cancel</a >
                <%}%>
            </td>
        </tr>
        <%}%>
    </table>
</div>


