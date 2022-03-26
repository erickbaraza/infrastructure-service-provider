/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ERICK
 */
public class Utils {
    public static String generateCode(){
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@$&#_?^*";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() <= 32) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        
        return salt.toString();
        
    }
    
    public static String getCookieParam(HttpServletRequest request, String parameter){
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(parameter)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    public static String getFullURLFromRequest(HttpServletRequest request){
        StringBuilder sb = new StringBuilder(request.getRequestURL().toString());
        String queryPart = request.getQueryString();
        
        if(queryPart == null){
            return sb.toString();
        }
        else
        {
            return  sb.append("?").append(queryPart).toString().replace("&", "__");
        }   
        
    }
}
