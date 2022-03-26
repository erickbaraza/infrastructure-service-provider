<%@page import="business.Service"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<Service> services = (ArrayList) request.getAttribute("services") ;
    
%>   
<%@include file="./includes/admin-header.jsp" %>
<div class="container p-0">
    <div class="row">
        <div class="col-12 my-2">Services</div> 
        <div class="col-12 my-2">
            <div class="card bg-light ">
                <div class="card-header bg-primary text-light">
                     <div class="pull-left">
                    <form class="form-inline" action="search" method="POST">
                      <input class="form-control mr-sm-2" type="text" placeholder="Search">
                      <button class="btn btn-success" type="submit">Search</button>
                    </form> </div>
                    <div class="pull-right">
                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-success btn-sm" href="add-service">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">Add Service</span>
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
                            <th>Name</th>
                            <th>Company</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%if(services != null) {%>
                                <% for(Service service : services) { %>
                                    <tr>
                                        <td><%= service.getId() %></td>
                                        <td>
                                            <img class="img-thumbnail" src="data:image/jpeg;base64,<%= service.getImageToBase64()%>" alt="alt" width="64" height="64"/>
                                        </td>
                                        <td><%= service.getName() %></td>
                                        <td><%= service.getCompany().getName() %></td>
                                        <td><%= service.getCategory().getName() %></td>
                                        <td><%= service.getPrice() %></td>
                                       
                                        <td>
                                            <ul class="list-inline" >
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-sucess btn-sm" href="edit-service?id=<%= service.getId() %>">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </li>
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-danger btn-sm" href="?serviceId=<%= service.getId() %>&action=delete" onclick="return confirm('Are you sure you want to delete this service?')">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } else { %> 
                        <p>No products yet. <a href="add-service">Click Here</a> to add services</p>
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