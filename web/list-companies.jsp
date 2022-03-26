<%@page import="business.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<Company> companies = (ArrayList) request.getAttribute("companies") ;
    
%>   
<%@include file="./includes/admin-header.jsp" %>
<div class="container p-0">
    <div class="row">
        <div class="col-12 my-2">Companies</div> 
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
                            <a class="btn btn-success btn-sm" href="add-company">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">Add Company</span>
                            </a>
                        </div> 
                    </div> 
                </div> 
                
                <div class="card-body">
                    <table class="table table-border ">
                        <thead class="bg-dark text-light">
                            <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Registration number</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Contact number</th>
                            
                            <th>Description</th>
                            
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%if(companies != null) {%>
                                <% for(Company company : companies) { %>
                                    <tr>
                                        <td><%= company.getId() %></td>
                                        <td>
                                            <img class="img-thumbnail" src="data:image/jpeg;base64,<%= company.getImageToBase64()%>" alt="alt" width="64" height="64"/>
                                        </td>
                                        <td><%= company.getRegNo() %></td>
                                        <td><%= company.getName() %></td>
                                        <td><%= company.getEmail() %></td>
                                        <td><%= company.getPhone() %></td>
                                        <td><%= company.getDescription() %></td>
                                        
                                        <td>
                                            <ul class="list-inline" >
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-sucess btn-sm" href="edit-company?id=<%= company.getId() %>">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </li>
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-danger btn-sm" href="?companyId=<%= company.getId() %>&action=delete" onclick="return Confirm('Are you sure you want to delete this company?')">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } else { %> 
                        <p>No companies yet. <a href="list-companies">Click Here</a> to add companies</p>
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