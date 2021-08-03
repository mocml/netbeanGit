<%-- 
    Document   : CustomerBillDetail
    Created on : Jul 16, 2021, 7:34:54 PM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body{
                background-image: url("IMAGE/background2.png");
            }
            .infoArea {
                position: fixed;
                float: left;
                padding: 1px 5px 10px 15px;
                margin-left: 30px;
                margin-top: 60px;
                box-shadow: 2px 1px 3px 3px #666666;
                background-color: white;
                width: 25%;
            }
            .infoAreaAdmin{
                float: left;
                padding: 1px 5px 10px 15px;
                margin-top: 40px;
                margin-left: 20px;
                box-shadow: 2px 1px 3px 3px #666666;
                background-color: white;
                width: 30%;
                position: fixed;
                top: 40%;
                left: 50%;
                transform: translate(-49%, -49%);
                padding: 0 20px 10px 20px;
                background-color: white;
                box-shadow: 2px 7px 7px #666666;
            }
            .allOrderArea{
                position: relative;
                margin:60px;
                width: 1200px;
                float: right;
                background-color: white;
                box-shadow: 2px 1px 3px 3px #666666;
            }
            .allOrderArea table{
                background-color: cornflowerblue;
                border: 2px solid white;
                width: 100%;
                text-align: center;
                border-collapse: collapse;
            }
            .allOrderArea td,
            th {
                border: 2px solid white ;
                height: 70px;
                color:white;
            }
            table{

            }
            .btnDone{
                background-color: white;
                padding: 10px 20px 10px 20px;
            }
            .btnCancel{
                background-color: white;
                box-shadow: 2px 3px 4px 2px #666666;
                padding: 10px 20px 10px 20px;
            }
            a {
                text-decoration: none;
            }

            button {
                background-color: red;
                padding: 5px 10px 5px 10px;
            }

            a,
            a:active,
            a:visited {
                color: blue;
                text-decoration: none;
            }
            .inputText{
                padding: 3px 12px 3px 0;
                font-size: 18px;
            }
            .txt{
                font-size: 18px;
                font-weight: bold;
            }
            th{
                font-size: 20px;
            }
        </style>
        <%

            String sp = request.getParameter("sp");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Infomation</title>
    </head>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
            <div style="margin-top: 50px;">
                <form action="ControllerMyAccount" method="POST" >
                <jsp:include page="CustomerInfomation.jsp"></jsp:include>

                <jsp:include page="MyOrderDetail.jsp"></jsp:include>
                    <!--INCLUDE-->
            </form>
        </div>
    </body>
</html>
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