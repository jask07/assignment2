package servlets;

import dataaccess.UserDB;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Users;
import services.UserService;
//import models.HomeItem;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        UserDB userdb = new UserDB();
        
        try{
            List<Users> users = userdb.getAllUsers();
            request.setAttribute("users", users);
        }catch(Exception ex){
            
        }
      
       getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
       return;
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        String oldUser = request.getParameter("oldUsername");
        
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        
        UserService us = new UserService();
        
       try{
        switch(action){
            case "create":
                us.insert(username, password, email, firstName, lastName);
                break;
            case "update":
                us.update(oldUser, username, password, email, firstName, lastName);
                break;
            case "delete":
                if (!username.equals(user)){
                 us.delete(username);   
                }
                break;
            case "view":
                request.setAttribute("selectedUser", us.get(username));
                break;
        }
        
        List<Users> users = us.getAllUsers();
        request.setAttribute("users", users);
       }catch(Exception ex){
           Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
           request.setAttribute("error", ex);
       }
       
       getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
       return;
    }


}
