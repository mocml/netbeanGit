<%-- 
    Document   : Content
    Created on : Jul 3, 2021, 11:36:04 PM
    Author     : vanhv
--%>

<%@page import="entity.Category"%>
<%@page import="model.DAOProduct"%>
<%@page import="model.DBConnect"%>
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
        position: absolute;
        background-image: url("IMAGE/background2.png");
        left: 140px;
        width: 91.1%;
        min-height: 96%;
    }
    select{
        outline: none;
    }
    a,
    a:active,
    a:visited {
        color: white;
        text-decoration: none;
    }
    .content{
        padding :20px;
        color : white;
    }
    .content a{
        background-color: white;
        color : red;
        box-shadow: 2px 3px 4px 2px #666666;
        padding: 15px;
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
            <th onclick="sortTablePrice()">Price ⬍</th>
            <th onclick="sortTableString(3)">Image ⬍</th>
            <th onclick="sortTableString(4)">Description ⬍</th>
            <th onclick="sortTableString(5)">Category ⬍</th>
            <th>Add to Cart</th>
        </tr>

        <%for (Product product : pro) {%>
        <tr id="<%= product.getPid()%>">         
            <td><%= product.getPid()%></td>
            <td><%= product.getPname()%></td>
            <td><%= product.getPrice()%></td>
            <td ><img style="width: 100px;padding-left: 0px" src="<%=product.getImage()%>"></td>
            <td><%= product.getDescription()%></td>

            <%for (Category cate : listCategory) {%>
            <%if (cate.getCateID() == product.getCateID()) {%>
            <td><%=cate.getCateName()%></td>
            <%}%>
            <%}%>

            <td><a href="ControllerCart?service=addtocart&pid=<%= product.getPid()%>">Add to Cart</a></td>
        </tr>
        <%}%>
    </table>
</div>
<script>
    function sortTablePrice() {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("myTable");
        switching = true;
        dir = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[2];
                y = rows[i + 1].getElementsByTagName("TD")[2];
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
