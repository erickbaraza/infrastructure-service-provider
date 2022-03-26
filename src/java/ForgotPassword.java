

import com.connection.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String email = request.getParameter("email");
        String phone_number = request.getParameter("phone_number");
        String password = request.getParameter("password");
        
        Connection conn = DatabaseConnection.getConnection();
        
        ResultSet rs = DatabaseConnection.getResultFromSqlQuery("Select * from users where email = '"+email+"' and phone_number='"+phone_number+"'");
        try {
            while(rs.next()){
                DatabaseConnection.insertUpdateFromSqlQuery("update users set password=md5(?) where email ='"+email+"'");
                response.sendRedirect("forgetPassord.jsp?msg=done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("forgetPassord.jsp?msg=failed");

        }
        
        getServletContext().getRequestDispatcher("/forget-password.jsp").forward(request, response);
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
        
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/forget-passwordjsp").forward(request, response);
        
    }

   
   
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
