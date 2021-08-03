<%-- 
    Document   : Register
    Created on : Jul 8, 2021, 8:16:53 PM
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
            .registerForm{
                position: fixed;
                top: 40%;
                left: 50%;
                background-color: white;
                transform: translate(-49%, -49%);
                padding: 0 20px 10px 20px;
                box-shadow: 2px 7px 7px #666666;
            }
            table{
                padding-left: 10px;
            }
            a,
            a:active,
            a:visited {
                text-decoration: none;
                color: white;
                text-decoration: none;
            }
            .inputArea{
                padding: 5px;
                font-size: 18px;
                width: 250px;
                margin-bottom: 5px;
            }
            select{
                text-align: center;
                width: 170px;
                padding: 2px 10px 2px 5px;
                font-size: 16px;
            }
            button{
                margin-top: 10px;
                color:white;
            }
            .buttonLogin{
                margin-left: 50px;
                width: 70px;
                background-color: deepskyblue;
            }
            .buttonRegister{
                margin-left: 40px;
                background-color: red;
            }
            .button-shadow {
                padding: 5px;
                box-shadow: 1px 1px 2px 1px #666666;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <div class="registerForm">
            <h1 style ="color: dodgerblue" >CREATE ACCOUNT</h1>
            <form action="Register" method="POST">
                <table>
                    <tr>
                        <td>
                            <input class="inputArea" type="text" placeholder="Your Name" name="name" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="inputArea" type="text" placeholder="Number Phone" name="phone" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="inputArea" type="text" placeholder="Your Address" name="address">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="inputArea" type="text" placeholder="User Name"  name="username" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="inputArea" type="password" minlength="8" placeholder="Password" name="password" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input class="inputArea" type="password" placeholder="Re-password" name="repassword" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label style="padding-right: 20px;color: grey;font-size: 20px">Gender : </label><select name="gender">
                                <option value="1">Male</option>
                                <option value="0">Female</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="button-shadow buttonRegister" name="register" value="register">Register</button>
                            <button class="button-shadow buttonLogin" ><a href="Login">Login</a></button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
