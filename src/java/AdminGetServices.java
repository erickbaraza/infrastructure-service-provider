/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import business.Category;
import business.Service;
import data.CategoryIO;
import data.ServiceIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ERICK
 */
@WebServlet(urlPatterns = {"/list-services"})
public class AdminGetServices extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    { String message = null;
        String messageType = null;
        
        String action = request.getParameter("action");
       ArrayList<Service> services ;
        
        if( request.getParameter("category") != null)
        {
            String categorySlug = request.getParameter("category");
            services = ServiceIO.getServicesByCategory(categorySlug);
        }
        else
        {
            services = ServiceIO.getServices();
        }
        
        if(services.isEmpty() )
        {
            message = "No services found";
            messageType = "danger";
        }

        request.setAttribute("services", services);
               
        
        if( action != null)
        {
            switch (action)
            {
                case "delete":
                    String id = request.getParameter("serviceId");
                    int serviceId = 0;

                    if(id != null)
                    {
                        serviceId = Integer.parseInt(id);
                    }
                    boolean deleted = ServiceIO.deleteService(Integer.parseInt(id));
                    if( deleted )
                    {
                        message = "Service with id :" + serviceId +" deleted successfully";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to delete service with id :" + serviceId;
                        messageType = "danger";
                    }
                    break;
                default:
                    break;
            }
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
       
        
        // forwards to the companies page
        getServletContext().getRequestDispatcher("/list-service.jsp").forward(request, response);
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
   }

    @Override
    public String getServletInfo() {
        return "Gets list of services, includes deleting a service";
    }

}
