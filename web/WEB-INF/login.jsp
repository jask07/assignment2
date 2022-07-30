<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
    </head>
    <body>
        <h1>login</h1>
        <form method="POST" action="login">
            <label>Username: </label>
            <input type="username" name="username" value="">
            <br>
            <label>Password </label>
            <input type="password" name="password" value="">
            <br>
            <br><br>
            <input type="submit" value="Submit">
        </form>
        <p>${message}</p>
    </body>
</html>
