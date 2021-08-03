<%-- 
    Document   : UpdateProduct
    Created on : Jul 17, 2021, 9:16:37 PM
    Author     : vanhv
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    body{
        position: relative;
        background-image: url("IMAGE/background2.png");
        -webkit-user-select: none;  
        -moz-user-select: none;    
        -ms-user-select: none;  
    }
    .imgs{
        width: 15%;
        height: 40%;
        position: fixed;
        top: 40%;
        left: 66%;
        transform: translate(-49%, -49%);
        padding: 0 20px 10px 20px;
        background-color: white;
        box-shadow: 3px 7px 7px 1px #666666;
        border-radius: 10px;
    }
    .updateForm{
        width: 30%;
        height: 50%;
        position: fixed;
        top: 40%;
        left: 38%;
        transform: translate(-49%, -49%);
        padding: 0 20px 10px 20px;
        background-color: white;
        box-shadow: 3px 7px 7px 1px #666666;
        border-radius: 10px;
    }
    .updateForm th{
        text-align: right;
        font-size: 20px;
        padding-right: 10px;
    }
    .updateForm table{
        margin-left: 100px;
    }
    .pInputArea{
        padding: 5px;
        font-size: 18px;
        width: 200px;
        margin-bottom: 5px;
    }
    .btnReset,.btnUpda{
        border-radius: 4px;
        margin-top: 19px;
        color:white;
        padding: 10px 20px 10px 20px;
        box-shadow: 1px 1px 2px 1px #666666;
    }
    .btnUpda{
        cursor: pointer;
        background-color: #78cdf8;
    }
    .btnUpda:hover{
        background-color:#5bb7e5 ;
    }
    .btnReset{
        cursor: pointer;
        margin-left: 10px;
        background-color: gray;
    }
    .btnReset:hover{
        background-color: #5b6063;
    }
</style>
<body>
    <%
        Product product = (Product) request.getAttribute("product");
        //======
        ArrayList<Category> cateList
                = (ArrayList<Category>) request.getAttribute("listCate");
        String note = (String) request.getAttribute("note");

    %>
    <jsp:include page="PannerAdmin.jsp"></jsp:include>

        <form action="ControllerProduct" method="POST">

            <div>
                <div class="imgs">
                    <img style="margin-left: 40px;margin-top: 20px;width: 200px;padding-left: 0px" src="<%=product.getImage()%>" alt="<%=product.getImage()%>">
                <input class="pInputArea " style="margin-left: 20px;margin-top: 20px;width: 250px" type="text" name="image" value="<%=product.getImage()%>">
            </div>
            <div class="updateForm">
                <h1 style="color: #389fd3;margin-left: 140px">UPDATE PRODUCT</h1>
                <table>
                    <tr>
                        <th>Product ID : </th>
                        <td><input class="pInputArea" type="text" name="pid" readonly value="<%=product.getPid()%>"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Product Name : </th>
                        <td><input class="pInputArea" type="text" name="pname" value="<%=product.getPname()%>"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Quatity : </th>
                        <td><input class="pInputArea" type="text" name="quantity" value="<%=product.getQuantity()%>"></td>
                        <td></td>
                    </tr>
                    <tr> 
                        <th>Price : </th>
                        <td><input class="pInputArea" type="text" name="price" value="<%=product.getPrice()%>"></td>
                        <td></td>
                    </tr>
                    <tr> 
                        <th>Description : </th>
                        <td><input class="pInputArea" type="text" name="description" value="<%=product.getDescription()%>"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Status : </th>
                        <td>
                            <input style="cursor: pointer;" type="radio" name="status" value="1" 
                                   <%=(product.getStatus() == 1 ? "checked" : "")%> />Enable
                            <input style="cursor: pointer;" type="radio" name="status" value="0"
                                   <%=(product.getStatus() == 0 ? "checked" : "")%>/>Disable
                        </td>
                        <td></td>
                    </tr>
                    <tr> 
                        <th>Category: </th>
                        <td><select style="width: 140px;padding: 5px 15px 5px 15px;font-size: 16px;cursor: pointer;" name="cateid">  
                                <% for (Category cate : cateList) {%>
                                <%if (product.getCateID() == cate.getCateID()) {%>
                                <option value="<%=cate.getCateID()%>" selected><%= cate.getCateName()%></option>
                                <%} else {%>
                                <option value="<%=cate.getCateID()%>" ><%= cate.getCateName()%></option>
                                <%}%>
                                <%}%>
                            </select>
                        </td>
                        <td></td>
                    </tr>

                    <tr>
                        <th></th>
                        <td> 
                            <input class="btnUpda" type="submit" value="Update" name="submit" />
                            <input class="btnReset" type="reset" value="Reset" name="reset" />
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td  style="padding: 15px 10px 10px 0px" >
                            <%if (note == null) {%>
                            <label style="color:#2eaf55"><%=note%></label>
                            <%} else {%>
                            <label style="color:#2eaf55;"><%=note%></label>
                            <%}%>
                        </td>
                    </tr>
                    <input type="hidden" value="updated" name="service" />
                </table>
                        
            </div>       
        </div>
    </form>
</body>
