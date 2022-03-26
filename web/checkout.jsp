<%@page import="java.util.ArrayList"%>
<%@page import="business.Cart"%>
<%@page import="data.CartIO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./includes/header.jsp" %>
<%
    User user = (User) request.getAttribute("user");
    ArrayList<Cart> cart = (ArrayList) request.getAttribute("cart");
    
    Boolean info = (Boolean) request.getAttribute("info");
    Boolean address = (Boolean) request.getAttribute("address");
    
    Boolean pay = (Boolean) request.getAttribute("pay");
    String paymentMethod = (String) request.getParameter("payment_method");
    String accountNumber = (String) request.getParameter("account_number");
    float cartMprAmount = Float.parseFloat(request.getAttribute("cartAmount").toString());
%> 
<div class="container">
    <div class="row">
        <div class="col-12">
            <%
                String message = (String) request.getAttribute("message");
                String messageType = (String) request.getAttribute("messageType");
            %>
            <%  if (message != null) {
                    String alertType = "alert-" + messageType;
            %>
               <div class="alert <%= alertType %> alert-dismissible show " id="success">
                    <p><%= message %>.</p>
                    <button class="close" data-dismiss="alert">&times;</button>
                </div>
            <% request.removeAttribute("message") ;%>
            <% } %>
        </div>
    </div>
    <% if(pay != null && pay == true){ %>
    <div class="container">
        <div class="row">
            <div class="col-sm-6 mx-auto my-2">
                <div class="card bg-transparent">
                    <div class="card-header bg-primary text-light">Payment</div>
                    <div class="card-body">
                        <%= Integer.parseInt(request.getAttribute("orderId").toString()) %>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="container">
        <div class="row my-2 border border-secondary">
            <div class="col-12 mb-2 bg-primary py-2">
                <div class="text-light pull-left">User Information</div>
            </div>
            <div class="col-12 mb-2">
                <form action="checkout?action=info" method="post">
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Enter First Name</label> 
                                <input class="form-control" type="text" name="first_name" value="<%= user.getFirstName()%>" required />
                            </div>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Enter Last Name</label> 
                                <input class="form-control" type="text" name="last_name" value="<%= user.getLastName()%>" required />
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Email</label> 
                                <input class="form-control" type="email" name="email" value="<%= user.getEmail()%>" required/>
                            </div>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Phone number</label> 
                                <input class="form-control" type="text" name="phone_number" value="<%= user.getPhoneNumber()%>"  pattern="^254[1|7][0-9]{8}"required/>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group btn-group-sm">
                        <button class="btn btn-primary btn-sm" type="submit">
                            Update
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row mt-3 border border-secondary">
            <div class="col-12 mb-2 bg-primary py-2">
                <div class="text-light pull-left">Address Information</div>
            </div>
            <div class="col-12 mb-2">
                 <form action="checkout?action=address" method="post">
                    <div class="form-group">
                        <label>Type</label> 
                        <select name="type" class="form-control">
                            <option value="address">Residential Address</option>
                            <option value="shipping">Shipping Address</option>
                            <option value="billing">Shipping and Billing Address</option>
                        </select>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Country</label> 
                                <input class="form-control" type="text" name="country" value="<%= user.getAddress().getCountry() %>" required />
                            </div>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>City</label> 
                                <input class="form-control" type="text" name="city" value="<%= user.getAddress().getCity()%>" required />
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Street</label> 
                                <input class="form-control" type="text" name="street" value="<%= user.getAddress().getStreet()%>" />
                            </div>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>House Number</label> 
                                <input class="form-control" type="text" name="house_number" value="<%= user.getAddress().getHouseNumber()%>"   />
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Postal Code</label> 
                                <input class="form-control" type="text" name="postal_code" value="<%= user.getAddress().getPostalCode()%>"  />
                            </div>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Zip Code</label> 
                                <input class="form-control" type="text" name="zip_code" value="<%= user.getAddress().getZipCode()%>"  />
                            </div>
                        </div>
                    </div>
                    <div class="btn-group btn-group-sm">
                        <button class="btn btn-primary btn-sm" type="submit">
                            Update
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row mt-3 border border-secondary">
            <div class="col-12 mb-2 bg-primary py-2">
                <div class="text-light pull-left">Order Preview</div>
            </div>
            <div class="col-12 mb-2">
                <table class="table table-border w-100 ">
                    <thead class="bg-dark text-light">
                        <tr>
                        <th>#</th>
                        <th>Image</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Stock Status</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%if(cart != null) {%>
                            <% for(business.Cart item : cart) { %>
                                <tr>
                                    <td><%= item.getId()%></td>
                                    <td>
                                        <img class="img-thumbnail" src="data:image/jpeg;base64,<%= item.getProduct().getImageToBase64()%>" alt="alt" width="64" height="64"/>
                                    </td>
                                    <td><%= item.getProduct().getName() %></td>
                                    <td>
                                        <p class="small">
                                            <% if(item.getMrpPrice() == 0 ) { %>
                                                <span class="text-dark">Ksh <%= item.getPrice() %></span>
                                            <%  } else { %>
                                            <span>
                                                <strong class="text-success"><ins>Ksh <%= item.getMrpPrice() %></ins></strong>
                                                <del class="text-danger small">Ksh <%= item.getPrice() %></del>
                                            </span>
                                             <% } %>
                                        </p>
                                    </td>
                                    <td><%= item.getQuantity() %></td>
                                    <td><%= item.getProduct().getStatus() %></td>
                                    <td><%= item.getItemSubTotal() %></td>
                                    <td>
                                        <ul class="list-inline" >
                                            <% if(!item.isConfirm()) { %>
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-success btn-sm" href="confirm-cart-item?slug=<%= item.getProduct().getSlug() %>&userId=<%= item.getUserId() %>&redirect=checkout">
                                                        Confirm
                                                    </a>
                                                </li>
                                            <% }  %> 
                                            <li class="list-inline-item">
                                                <a class="card-link btn btn-danger btn-sm" href="remove-from-cart?slug=<%= item.getProduct().getSlug() %>&userId=<%= item.getUserId() %>" onclick="return confirm('Are you sure you want to remove this product from cart?')">
                                                    <i class="fa fa-trash"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                            <% } %>
                            <tr>
                                <td colspan="8" class="text-right"> 
                                    <p>
                                        <strong>Total</strong>: <span><%= (float)request.getAttribute("cartTotal") %></span>  
                                    </p>
                                    <p>
                                        <strong>Discount</strong>: <span><%= (float)request.getAttribute("cartDiscount") %></span>  
                                    </p>
                                    <p>
                                        <strong>Amount</strong>: <span><%= (float)request.getAttribute("cartAmount") %></span>  
                                    </p>
                                </td>
                            </tr>
                        <% } else { %> 
                    <p>No items in cart yet. <a href="products"> Continue Shopping</a></p>
                        <% }%> 
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row mt-3 border border-secondary">
            <div class="col-12 mb-2 bg-primary py-2">
                <div class="text-light pull-left">Pay & Order</div>
            </div>
            <div class="col-12 mb-2">
                <form action="checkout?action=place-order-and-pay" method="post">
                    <input type="hidden" name="amount" value="<%= (float)request.getAttribute("cartAmount") %>"/>
                    <div class="form-row">
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label for="payment_method">Payment Method</label> 
                                <select class="form-control" id="payment_method" name="payment_method" required>
                                    <option value="mpesa"  selected>MPesa</option>
                                    <option value="paypal" >PayPal</option>
                                </select>
                            </div>  
                            <script >
                                document.querySelector('#payment_method').addEventListener('change', (evt) => {
                                    let paymentMethod = evt.target.value;
                                    selectPaymentAccount(paymentMethod);
                                });
                                
                                function selectPaymentAccount(paymentMethod){
                                   if(paymentMethod === 'mpesa'){
                                       document.getElementById('paypal-account').style.display = 'none';
                                       document.getElementById('mpesa-account').style.display = 'block';
                                   }else{
                                       document.getElementById('mpesa-account').style.display = 'none';
                                       document.getElementById('paypal-account').style.display = 'block';
                                   }
                                }
                            </script>
                        </div>
                        <div class="col-md-6 mb-1">
                            <div class="form-group">
                                <label>Account Number</label> 
                                <input class="form-control " type="text" name="account_number" id="mpesa-account"   placeholder="254XXXXXXXXX" pattern="^254[1|7][0-9]{8}" required value="<%= user.getPhoneNumber() %>" />
                                <input class="form-control " style="display:none;" type="email" name="account_number" id="paypal-account" placeholder="PayPal Email Address" required value="<%= user.getEmail() %>" />
                            </div>
                        </div>
                    </div>
                    <div class="my-1">
                        <button class="btn btn-warning btn-block" type="submit">
                            Place Order
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div> 
    <% } %>
</div>

<%@include file="./includes/footer.jsp" %>