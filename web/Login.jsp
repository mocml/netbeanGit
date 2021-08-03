<%-- 
    Document   : Login
    Created on : Jul 4, 2021, 10:50:32 AM
    Author     : vanhv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" href="/CSS/login.css">

        <style>
            body {
                position: relative;
                background-image: url("IMAGE/background.png");
            }
            a,
            a:active,
            a:visited {
                color: white;
                text-decoration: none;
            }
            th {
                font-size: 16px;
                font-family: Arial, Helvetica, sans-serif;
            }

            p {
                text-align: center;
                font-weight: bold;
                font-size: 28px;
            }

            a {
                text-decoration: none;
            }

            .tile {
                color: red;
            }

            .textfield {
                border-radius: 5px;
                outline: none;
                padding: 5px;
                font-size: 18px;
                width: 250px;
            }

            .login-middle {
                border-radius: 10px;
                position: fixed;
                top: 40%;
                left: 50%;
                transform: translate(-49%, -49%);
                padding: 0 50px 20px 50px;
                background-color: wheat;
                box-shadow: 2px 7px 7px #666666;
            }

            .button-shadow {
                border-radius: 5px;
                padding: 10px 20px 10px 20px;
                box-shadow: 1px 2px 1px 1px #666666;
            }
            .login{
                background-color: deepskyblue;
                color: white;
                margin-left: 40px;

            }
            .register{
                color: white;
                margin-left: 10px;
                background-color: red;
            }
        </style>
        <title>Login</title>
    </head>

    <body>

        <div class="login-middle">
            <p >LOGIN</p>
            <form action="Login" method="POST">
                <table>
                    <tr>
                        <th></th>
                        <td><input class="textfield" type="text" name="username" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td><input class="textfield" type="password" name="password" placeholder="Password"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="checkbox" name="remember" value="remember" checked>Remember
                        </td>
                    </tr>
                    <tr>
                        <td>

                        </td>
                        <td>
                            <button class="button-shadow login" name="login" value="login">Login</button>
                            <button class="button-shadow register" name="login" value="register">Register</button>
                        </td> 
                    </tr>
                </table>
            </form>
        </div>

    </body>

</html>
