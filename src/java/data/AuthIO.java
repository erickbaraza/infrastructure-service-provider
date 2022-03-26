package data;

import business.User;
import javax.servlet.http.Cookie;
import utils.Utils;

import javax.servlet.http.HttpServletRequest;

public class AuthIO {
    //login
    public static boolean login(String email, String password){
        //get user by email
        User user = UserIO.getUserByEmailAndPassword(email, password);
        
        return user != null;
    }
    
    public static boolean isLoggedIn(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null){
            String loggedIn = Utils.getCookieParam(request, "loggedIn");
            String email = Utils.getCookieParam(request, "email");
            
            return !(loggedIn == null && email == null);
        }
        return false;
    }
    
    public static User getLoggedInUser( String cookieEmail){
        User user = UserIO.getUserByEmail(cookieEmail);
        if(user != null){
            return user;
        }
        return null;
    }
}
