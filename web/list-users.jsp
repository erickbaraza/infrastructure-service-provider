<%@page import="business.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<User> users = (ArrayList) request.getAttribute("users") ;
    
%> 
<%@include file="./includes/admin-header.jsp" %>

<div class="container p-0">
    <div class="row"> 
        <div class="col-12 my-2">
            <div class="card bg-light ">
                <div class="card-header bg-primary text-light">
                    <div class="pull-left">
                        Users
                    </div>
                     <div class="pull-left">
                    <form class="form-inline" action="search"method="POST">
                      <input class="form-control mr-sm-2" type="text" placeholder="Search">
                      <button class="btn btn-success" type="submit">Search</button>
                    </form> </div>
                    <div class="pull-right">
                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-success btn-sm" href="add-user">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">add User</span>
                            </a>
                        </div> 
                    </div>
                   
                </div> 
                <div class="card-body">
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
                    <%if(users != null) {%>
                    <table class="table table-border ">
                        <thead class="bg-dark text-light">
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Role</th>
                                <th>Phone number</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        <% for(User user : users ) { %>
                            <tr>
                                <td><%=user.getId()%></td>
                                <td><%=user.getFirstName()%></td>
                                <td><%=user.getLastName()%></td>
                                <td><%=user.getEmail()%></td>
                                <td><%=user.getRole()%></td>
                                <td><%=user.getPhoneNumber()%></td>
                                <td>
                                    <ul class="list-inline" >
                                        <li class="list-inline-item">
                                            <a class="card-link btn btn-sucess btn-sm" href="edit-user?id=<%= user.getId() %>">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item">
                                            <a class="card-link btn btn-danger btn-sm" href="?userId=<%= user.getId() %>&action=delete" onclick="return confirm('Are you sure you want to delete this user?')">
                                                <i class="fa fa-trash"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <% } else { %> 
                        <p>No Users yet. <a href="add-user">Click Here</a> to add user</p>
                    <% }%> 
                </div> 
            </div> 
        </div> 
    </div>
    
</div>
<!-- footer -->
<%@include file="./includes/admin-footer.jsp" %>