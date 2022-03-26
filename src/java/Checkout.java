import business.Address;
import business.Order;
import business.OrderItem;
import business.User;
import data.AddressIO;
import data.AuthIO;
import data.CartIO;
import data.OrderIO;
import data.OrderItemIO;
import data.UserIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {

    String message = null;
    String messageType = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check login
        if( AuthIO.isLoggedIn(request))
        {
            //get user and cart Details
            getUserAndCartDetails(request);
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
        }
        else
        {
            response.sendRedirect("login");
        } 
        request.setAttribute("info", false);
        request.setAttribute("address", false);
        
        getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //check login
        if( AuthIO.isLoggedIn(request))
        {
            //get user and cart details
            getUserAndCartDetails(request);
            
            String action = request.getParameter("action");
            
            switch(action)
            {
                case "info" : 
                    // update user details
                    updateUser(request);
                    break;
                case "address" :
                    //update user address
                    updateOrSaveUserAddress(request);
                    break;
                case "place-order-and-pay":
                    // store order items
                    storeOrder(request,response);
                    
                    //get the order id
                    long orderId = Integer.parseInt( request.getAttribute("orderId").toString());
                    
                    if(orderId != 0){
                        String paymentMethod = (String) request.getParameter("payment_method");
                        String accountNumber = (String) request.getParameter("account_number");
                        float cartMprAmount = Float.parseFloat(request.getAttribute("cartAmount").toString());
                        
                        request.setAttribute("pay",true);
                        request.setAttribute("paymentMethod",paymentMethod);
                        request.setAttribute("accountNumber",accountNumber);
                        request.setAttribute("amount",cartMprAmount);
                        
                    }else{
                        message = "Sorry, but we are unable to process your request at given time. Try again later  ";
                        messageType = "danger";
                        request.setAttribute("pay",false);
                    }
                    
                    break;
                default :
                    message = "Unkown request ";
                    messageType = "danger";
                    break;
            }
            
        }
        else
        {
            response.sendRedirect("login");
        }
        
       
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
        getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request)
            throws ServletException, IOException
    {
        //retrieve user
        String userEmail = Utils.getCookieParam(request, "email");
        User user = UserIO.getUserByEmail(userEmail);
        
        if(user != null) //user exists
        {
            user.setFirstName( request.getParameter("first_name"));
            user.setLastName( request.getParameter("last_name"));
            user.setEmail( request.getParameter("email"));
            user.setPhoneNumber(request.getParameter("phone_number"));
            
            //update
            boolean updated = UserIO.updateUser(user);
            
            Boolean info = (Boolean) request.getAttribute("info");
            request.setAttribute("info", info != null ? info : updated );
            
            Boolean address = (Boolean) request.getAttribute("address");
            request.setAttribute("address", address != null ? address : false );
        }
        else{
            request.setAttribute("info", false);
            message = "You have no items in cart yet";
            messageType = "danger";
        }
           
    }
    
    private void updateOrSaveUserAddress(HttpServletRequest request)
            throws ServletException, IOException
    {
        //retrieve user
        String userEmail = Utils.getCookieParam(request, "email");
        User user = UserIO.getUserByEmail(userEmail);
        
        if(user != null) //user exists
        {
            Address address = new Address();
            address.setUserId( user.getId());
            address.setType( request.getParameter("type"));
            address.setCountry( request.getParameter("country"));
            address.setCity( request.getParameter("city"));
            address.setStreet( request.getParameter("street"));
            address.setHouseNumber( Integer.parseInt(request.getParameter("house_number")));
            address.setPostalCode( Integer.parseInt(request.getParameter("postal_code")));
            address.setZipCode( Integer.parseInt(request.getParameter("zip_code")));
            
            boolean saved;
            
            if(user.getAddress() == null){
                saved = AddressIO.saveAddress(address);
            }
            else{
                saved = AddressIO.updateAddress(address);
            }
            
            if(!saved){
                message = "Unable to save your address";
                messageType = "danger";
            }
            
            Boolean info = (Boolean) request.getAttribute("info");
            request.setAttribute("info", info != null ? info : false );
            
            Boolean addr = (Boolean) request.getAttribute("address");
            request.setAttribute("address", addr != null ? addr : saved );
        }
        else{
            request.setAttribute("info", false);
            message = "You have no items in cart yet";
            messageType = "danger";
        }
    }
    
    private void storeOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //retrieve user
        String userEmail = Utils.getCookieParam(request, "email");
        User user = UserIO.getUserByEmail(userEmail);
        
        if(user != null) //user exists
        {
            //get user and cart details
            getUserAndCartDetails(request);
            
            ArrayList<business.Cart> confirmedItems = new ArrayList();
            
            ArrayList<business.Cart> cartItems = (ArrayList) request.getAttribute("cart");
            
            if(cartItems != null){
                
                //get confirmed Items
                for(business.Cart cartItem : cartItems){
                    if(cartItem.isConfirm()){
                        confirmedItems.add(cartItem);
                    }
                }
                
                if(confirmedItems.isEmpty()){
                    confirmedItems = cartItems;
                }
                
                float cartTotal = Float.parseFloat(request.getAttribute("cartTotal").toString());
                float cartMprAmount = Float.parseFloat(request.getAttribute("cartAmount").toString());
                //create order
                Order order = new Order();
                order.setUserId( user.getId());
                order.setAmount( cartTotal );
                order.setMrpAmount( cartMprAmount);
                order.setItems( confirmedItems.size());
                order.setStatus( "pending");
                
                ArrayList<OrderItem> orderItems = fillOrderItems(confirmedItems);
                //save order
                int orderId = OrderIO.createOrder(order, orderItems);
                if(orderId != 0){
                    //save order items
                   
                    for(OrderItem orderItem: orderItems  ){
                        if(!OrderItemIO.addItemToOrder(orderItem)){
                            message = "Your order was placed successfully but an uknown error. ";
                            messageType = "danger";

                            //delete the order
                            OrderIO.deleteOrder(orderId);
                            //delete associeted order items
                            // reset orderId
                            orderId = 0;
                            break;
                        }
                            
                    }
                    request.setAttribute("orderId", orderId);
                }
                else{
                    request.setAttribute("orderId", 0);
                }
            }
            
        }
        
    }
   
    private ArrayList<OrderItem> fillOrderItems( ArrayList<business.Cart> cartItems){
         ArrayList<OrderItem> orderItems = new ArrayList();
         for(business.Cart cartItem : cartItems){
            OrderItem item = new OrderItem();
            item.setProductId( cartItem.getProductId());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice( cartItem.getPrice());
            item.setMrpPrice(cartItem.getMrpPrice());
        }
        return orderItems;
    }
    private void makePayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
    }
    
    private void getUserAndCartDetails(HttpServletRequest request)
            throws ServletException, IOException
    {
        //retrieve user
        String userEmail = Utils.getCookieParam(request, "email");
        
        User user = UserIO.getUserByEmail(userEmail);
        if(user != null) //user exists
        {
            //get cart details
           ArrayList<business.Cart> carts = CartIO.getCartsByUserId(user.getId());

            request.setAttribute("cart", carts);
            float totalMrpPrices = CartIO.getSubTotalByUserId(user.getId());
            float totalPrices = CartIO.getTotalByUserId(user.getId());

            request.setAttribute("cartTotal" , totalPrices );
            request.setAttribute("cartDiscount" , (totalPrices - totalMrpPrices) );
            request.setAttribute("cartAmount" , totalMrpPrices );
        }
        else{
            message = "You have no items in cart yet";
            messageType = "danger";
        }
        request.setAttribute("user", user);
        // get user cart items
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
