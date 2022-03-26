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
@WebServlet("/services")

public class GetServices extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = null;
        String messageType = null;
        
        ArrayList<Service> services = null;
        
        ArrayList<Category> categories = CategoryIO.getCategories();
        
        long serviceId ;
        
        if( request.getParameter("serviceId") != null)
        {
            serviceId = Integer.parseInt(request.getParameter("serviceId"));
            Service service = ServiceIO.getService(serviceId);
            request.setAttribute("service", service);
            // forwards to the products page
            getServletContext().getRequestDispatcher("/services.jsp").forward(request, response);
        }
        else
        {
            if( request.getParameter("category") != null)
            {
                String categorySlug = request.getParameter("category");
                services = ServiceIO.getServicesByCategory( categorySlug);
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
        
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("services", services);
            request.setAttribute("categories", categories);

            // forwards to the products page
            getServletContext().getRequestDispatcher("/services.jsp").forward(request, response);
        }
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gets list of services, includes deleting a service";
    }

}
