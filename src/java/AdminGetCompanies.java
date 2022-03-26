import business.Company;
import data.CompanyIO;
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
@WebServlet("/list-companies")
public class AdminGetCompanies extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = null;
        String messageType = null;
        
        String action = request.getParameter("action");
        ArrayList<Company> companies ;
        
        if( request.getParameter("category") != null)
        {
            String categorySlug = request.getParameter("category");
            companies = CompanyIO.getCompaniesByCategory( categorySlug);
        }
        else
        {
            companies = CompanyIO.getCompanies();
        }
        
        if(companies.isEmpty() )
        {
            message = "No companies found";
            messageType = "danger";
        }

        request.setAttribute("companies", companies);
               
        
        if( action != null)
        {
            switch (action)
            {
                case "delete":
                    String id = request.getParameter("companyId");
                    int companyId = 0;

                    if(id != null)
                    {
                        companyId = Integer.parseInt(id);
                    }
                    boolean deleted = CompanyIO.deleteCompany( Integer.parseInt(id));
                    if( deleted )
                    {
                        message = "Company with id :" + companyId +" deleted successfully";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to delete company with id :" + companyId;
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
        getServletContext().getRequestDispatcher("/list-companies.jsp").forward(request, response);
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gets list of companies, includes deleting a company";
    }

}
