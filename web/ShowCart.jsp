<%-- 
    Document   : ShowCart
    Created on : Jul 15, 2021, 11:23:16 AM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Cart</title>
        
        <link rel="stylesheet" href="CSS/Main.css">
        <link rel="stylesheet" href="CSS/Panner.css">
        <link rel="stylesheet" href="CSS/Sidebar.css">
        <link rel="stylesheet" href="CSS/Content.css">
    </head>
    <body>
        <jsp:include page="Panner.jsp"></jsp:include>
           
            <jsp:include page="showcarts.jsp"></jsp:include>
       
    </body>
</html>
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
