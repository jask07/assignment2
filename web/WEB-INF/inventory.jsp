<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INVENTORY</title>
    </head>
    <body>
        <h1>Home Inventory for ${username} </h1>
        <h4>Add Item</h4>
        <form method="POST" action="inventory">
            <label for="category">Category:</label>
            <select id="category" name="category">
                <option value="bedroom">Bedroom</option>
                <option value="garage">Garage</option>
                <option value="kitchen">Kitchen</option>
                <option value="living room">Living Room</option>
            </select>
            <br>
            <label>Item Name: </label>
            <input type="text" name="iname" value="${iname}">
            <br>
            <label>Price: </label>
            <input type="int" name="price" value="${price}">
            <br>
            <br><br>
            <input type="submit" value="add">
        </form>
        
        Total value in inventory ${tprice}
        <a href="login?logout">Logout</a>
        <p>${message}</p>
    </body>
</html>
