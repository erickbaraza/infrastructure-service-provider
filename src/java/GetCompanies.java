import business.Category;
import business.Company;
import data.CategoryIO;
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
@WebServlet("/companies")
public class GetCompanies extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = null;
        String messageType = null;
        
        ArrayList<Company> companies = CompanyIO.getCompanies();
        
        ArrayList<Category> categories = CategoryIO.getCategories();
        
        if( request.getParameter("companyId") != null)
        {
            long companyId = Integer.parseInt(request.getParameter("companyId"));
            Company company = CompanyIO.getCompany(companyId);
            request.setAttribute("company", company);
            // forwards to the companies page
            getServletContext().getRequestDispatcher("/companies.jsp").forward(request, response);
        }
        else
        {
            if( request.getParameter("category") != null)
            {
                String categorySlug = request.getParameter("category");
                companies = CompanyIO.getCompaniesByCategory( categorySlug);
            }
            else
            {
                companies = CompanyIO.getCompanies();
            }
        }
            
        if(companies.isEmpty() )
        {
            message = "No companies found";
            messageType = "danger";
        }
        
        request.setAttribute("companies", companies);
        
        request.setAttribute("categories", categories);
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
       
        
        // forwards to the companies page
        getServletContext().getRequestDispatcher("/companies.jsp").forward(request, response);
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
