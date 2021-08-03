<%-- 
    Document   : ContentAdmin
    Created on : Jul 10, 2021, 11:09:25 PM
    Author     : vanhv
--%>

<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    td,
    th {
        border: 2px solid white ;
        height: 70px;
    }

    th {
        font-size: 20px;
    }

    table {
        background-color: cornflowerblue;
        border: 2px solid black;
        width: 100%;
        text-align: center;
        border-collapse: collapse;
    }

    .content {
        float: right;
        width:91.1%;
        min-height: 96%;
        padding :20px;
        color : white;
        z-index: 1;
    }
    a,
    a:active,
    a:visited {
        color: white;
        text-decoration: none;
    }

    .content a{
        /*        background-color: white;*/
        font-family: Arial;
        color : white;
        box-shadow: 2px 3px 4px 2px #666666;
        padding: 10px;
    }

    .enable{
        color:white;
        font-weight: bold;
    }
    .disable{
        color: dimgrey;
        font-weight: bold;
    }
    .content th{
        box-shadow: 2px 1px 2px 1px #666666;
        cursor: pointer;
    }
    .category {
        float: left;
        padding: 10px 20px 10px 20px;
        background-color: royalblue;
    }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Product</title>
<%

    ArrayList<Product> pro
            = (ArrayList<Product>) request.getAttribute("listProduct");
    ArrayList<Category> listCategory
            = (ArrayList<Category>) request.getAttribute("listCategory");
%>
<div class="content">
    <table id="myTable">
        <tr>
            <th onclick="sortTableString(0)">ID ⬍</th>
            <th onclick="sortTableString(1)">Name ⬍</th>
            <th onclick="sortTableNumber(2)">Quantity ⬍</th>
            <th onclick="sortTableNumber(3)">Price ⬍</th>
            <th onclick="sortTableString(4)">Image ⬍</th>
            <th onclick="sortTableString(5)">Description ⬍</th>
            <th onclick="sortTableString(6)">Category ⬍</th>
            <th onclick="sortTableString(7)">Status ⬍</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>

        <%for (Product product : pro) {%>
        <tr>         
            <td><%= product.getPid()%></td>
            <td><%= product.getPname()%></td>
            <td><%=product.getQuantity()%></td>
            <td><%= product.getPrice()%></td>
            <td ><img style="width: 100px;padding-left: 0px" src="<%=product.getImage()%>"></td>
            <td><%= product.getDescription()%></td>

            <%for (Category cate : listCategory) {%>
            <%if (cate.getCateID() == product.getCateID()) {%>
            <td><%=cate.getCateName()%></td>
            <%}%>
            <%}%>
            <td><%=product.getStatus() == 1 ? "<label class=\"enable\">Enable</label>" : "<label class=\"disable\">Disable</label>"%></td>
            <td><a style="background-color: #00cc33;color:white"
                   href="ControllerProduct?service=update&pid=<%=product.getPid()%>">
                    Update</a></td>
            <td><a style="background-color: #ff3333;color:white" 
                   href="ControllerProduct?service=delete&pid=<%=product.getPid()%>">
                    Delete</a></td>
        </tr>
        <%}%>
    </table>
</div>
<script>
    function sortTableNumber(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("myTable");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                if (dir === "asc") {
                    if (Number(x.innerHTML) > Number(y.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (Number(x.innerHTML) < Number(y.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount++;
            } else {
                if (switchcount === 0 && dir === "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
    function sortTableString(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("myTable");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                if (dir === "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir === "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount++;
            } else {
                if (switchcount === 0 && dir === "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
</script>

