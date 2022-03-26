/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import business.User;
import data.UserIO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Utils;

/**
 *
 * @author ERICK
 */
@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = null;
        String messageType = null;
        
        User user = new User();
        
        user.setFirstName( request.getParameter("first_name"));
        user.setLastName( request.getParameter("last_name"));
        user.setEmail( request.getParameter("email"));
        user.setRole( "Customer");
        user.setPassword( request.getParameter("password"));
        user.setPhoneNumber( request.getParameter("phone_number"));
        
        
        String code = Utils.generateCode();
        
        user.setVerificationCode( code);
        user.setIsVerified(false);
        
        boolean saved = UserIO.saveUser(user);
        
        if(saved)
        {
            //TODO send verificatiion link to email
            message = "Registration was successful. Check email to verify account";
            messageType = "success";
        }
        else
        {
            message = "Unable to register at the moment";
            messageType = "danger";
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
        
    }
}
