<%-- 
    Document   : admin
    Created on : 3-Jul-2022, 11:18:54 AM
    Author     : jaspr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Summary</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h4>Admin Summary</h4>
        Total value for all users:  ${utprice}.
        <br>
        ${message}
        <a href="login?logout">Logout</a>
    </body>
</html>
