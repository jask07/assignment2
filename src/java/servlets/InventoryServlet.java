package servlets;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Categories;
import models.Items;
import models.Users;
import services.InventoryService;
//import models.HomeItem;

public class InventoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        
        
        ItemDB itemdb = new ItemDB();
        UserDB userdb = new UserDB();
        try{
            Users newUser = userdb.get(user);
            request.setAttribute("firstName", newUser.getFirstName());
            request.setAttribute("lastName", newUser.getLastName());
            List<Items> items = itemdb.getAll(newUser.getUsername());
            request.setAttribute("items",items);

        }catch (Exception ex){
            
        }
        
       getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
       return;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();        
        
        String action = request.getParameter("action");
        String itemid = request.getParameter("itemId");
        
        String user = (String) session.getAttribute("username");
        // room = category
        String room = request.getParameter("room");
        String item = request.getParameter("item");
        String cost = request.getParameter("price");
        
        ItemDB itemdb = new ItemDB();
        UserDB userdb = new UserDB();
        
        InventoryService is = new InventoryService();
       
        
        try{
            Users oldUser = userdb.get(user);
            List<Items> items = itemdb.getAll(oldUser.getUsername());
            switch(action){
                case "create":
                    if (item.length() <= 0 || cost.length() <=0 || Integer.parseInt(cost) >= 10000 || Integer.parseInt(cost) <= 0){
                        request.setAttribute("errorMsg", "Invalid. Please re-enter");
                    }else{
                        is.insert(items.size()+1,Integer.parseInt(room), item, Double.parseDouble(cost), user);
                        request.setAttribute("message", "The item was successfully added to your inventory.");
                    }
                    break;
                case "delete":
                    is.delete(Integer.parseInt(itemid), user);
            }
            List<Items> newItems = is.getAll(user);
            request.setAttribute("firstName", oldUser.getFirstName());
            request.setAttribute("lastName", oldUser.getLastName());
            request.setAttribute("items",newItems);
        }catch (Exception ex){
            request.setAttribute("errorMsg", ex);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;
        }
        
       getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
       return;
    }

}
