<%@page import="business.OrderItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<OrderItem> orderitems = (ArrayList) request.getAttribute("orderitems") ;
    
%>   
<%@include file="./includes/header.jsp" %>
<div class="container p-0">
    <div class="row">
        <div class="col-12 my-2">MY ORDERS</div> 
        <div class="col-12 my-2">
            <div class="card bg-light ">
                <div class="card-header bg-primary text-light">
                    <div class="pull-right">
                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-success btn-sm" href="checkout">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">Proceed to make payment</span>
                            </a>
                        </div> 
                    </div> 
                </div> 
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
                <div class="card-body">
                    <table class="table table-border ">
                        <thead class="bg-dark text-light">
                            <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Order id</th>
                            <th>Product id</th>
                            <th>Quantity</th>
                            <th>Price</th>
                             <th>Ordered date</th>
                           
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%if(orderitems != null) {%>
                                <% for(OrderItem orderitem : orderitems) { %>
                                    <tr>
                                        <td><%= orderitem.getId() %></td>
                                        <td>
                                            <img class="img-thumbnail" src="data:image/jpeg;base64,<%= orderitem.getImageToBase64()%>" alt="alt" width="64" height="64"/>
                                        </td>
                                        <td><%= orderitem.getOrderId() %></td>
                                        <td><%= orderitem.getProductId() %></td>
                                        <td><%= orderitem.getQuantity() %></td>
                                        <td><%= orderitem.getPrice() %></td>
                                       <td><%= orderitem.getCreatedAt() %></td>
                                        <td>
                                            <ul class="list-inline" >
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-sucess btn-sm" href="cart?id=<%= orderitem.getId() %>">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </li>
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-danger btn-sm" href="?orderitemId=<%= orderitem.getId() %>&action=delete" onclick="return confirm('Are you sure you want to remove this product from the cart?')">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } else { %> 
                        <p>No products yet. <a href="cart">Click Here</a> to add product</p>
                            <% }%> 
                        </tbody>
                        
                    </table>
                </div> 
            </div> 
        </div> 
    </div>
    
</div>
<!-- footer -->
<%@include file="./includes/admin-footer.jsp" %>