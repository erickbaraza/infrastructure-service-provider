<%@page import="java.util.ArrayList"%>
<%@page import="business.Cart"%>
<%@page import="data.CartIO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./includes/header.jsp" %>
<%
  ArrayList<Cart> cart = (ArrayList) request.getAttribute("cart");
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
    <div class="row mt-3 border border-secondary">
        
        <div class="col-12 mb-2 bg-primary py-2">
            <div class="text-light pull-left">Cart Items</div>
            <div class="pull-right">
                <div class="btn-group btn-group-sm">
                     <a class="btn btn-secondary btn-sm" href="clear-cart" >
                        Clear Cart
                    </a>
                    <a class="btn btn-success btn-lg text-capitalize ml-2" href="checkout" >
                        Checkout
                    </a>
                </div>
            </div>
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
                                <tr>
                                    <td colspan="4" class="text-left"> 
                                       <a class="card-link btn btn-outline-secondary btn-sm" href="products" >
                                            Continue Shopping
                                        </a>
                                    </td>
                                    <td colspan="4" class="text-right"> 
                                       <a class="card-link btn btn-primary btn-lg text-capitalize" href="checkout" >
                                            Checkout
                                        </a>
                                    </td>
                                </tr>
                            <% } else { %> 
                        <p>No items in cart yet. <a href="products"> Continue Shopping</a></p>
                            <% }%> 
                        </tbody>
                    </table>
        </div>
    </div>
</div>

<%@include file="./includes/footer.jsp" %>