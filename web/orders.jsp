<%@page import="business.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<Order> orders = (ArrayList) request.getAttribute("orders") ;
    
%>   
<%@include file="./includes/admin-header.jsp" %>
<div class="container p-0">
    <div class="row">
        <div class="col-12 my-2">Orders available</div> 
        <div class="col-12 my-2">
            <div class="card bg-light ">
                <div class="card-header bg-primary text-light">
                    <div class="pull-right">
                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-success btn-sm" href="search">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">Search the order </span>
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
                            <th>User</th>
                            <th>User id</th>
                            <th>Items</th>
                            <th>Date Created</th>
                            <th>Status</th>
                            <th>Amount</th>
                            
                             
                           
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%if(orders != null) {%>
                                <% for(Order order : orders) { %>
                                    <tr>
                                        <td><%= order.getId() %></td>
                                        
                                        <td><%= order.getUser() %></td>
                                        <td><%= order.getUserId() %></td>
                                        <td><%= order.getItems() %></td>
                                        <td><%= order.getCreatedAt() %></td>
                                       <td><%= order.getStatus() %></td>
                                       <td><%= order.getAmount() %></td>
                                    </tr>
                                <% } %>
                            <% } %>
                        </tbody>
                        
                    </table>
                </div> 
            </div> 
        </div> 
    </div>
    
</div>
<!-- footer -->
<%@include file="./includes/admin-footer.jsp" %>