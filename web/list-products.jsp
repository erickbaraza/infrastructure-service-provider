<%@page import="business.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  ArrayList<Product> products = (ArrayList) request.getAttribute("products") ;
    
%>   
<%@include file="./includes/admin-header.jsp" %>
<div class="container p-0">
    <div class="row">
        <div class="col-12 my-2">Products</div> 
        <div class="col-12 my-2">
            <div class="card bg-light ">
                <div class="card-header bg-primary text-light">
                     <div class="pull-left">
                    <form class="form-inline" action="https://www.w3schools.com/action_page.php">
                      <input class="form-control mr-sm-2" type="text" placeholder="Search">
                      <button class="btn btn-success" type="submit">Search</button>
                    </form> </div>
                    <div class="pull-right">
                        <div class="btn-group btn-group-sm">
                            <a class="btn btn-success btn-sm" href="add-product">
                                <i class="fa fa-plus"></i>
                                <span class="ml-1">Add Product</span>
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
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Stock Status</th>
                            <th>Price</th>
                            <th>Mrp Price</th>
                            <th>Category</th>
                            <th>Company</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                            <%if(products != null) {%>
                                <% for(Product product : products) { %>
                                    <tr>
                                        <td><%= product.getId() %></td>
                                        <td>
                                            <img class="img-thumbnail" src="data:image/jpeg;base64,<%= product.getImageToBase64()%>" alt="alt" width="64" height="64"/>
                                        </td>
                                        <td><%= product.getName() %></td>
                                        <td><%= product.getQuantity() %></td>
                                        <td><%= product.getStatus() %></td>
                                        <td><%= product.getPrice() %></td>
                                        <td><%= product.getMrpPrice() %></td>
                                        <td><%= product.getCategory().getName() %></td>
                                        <td><%= product.getCompany().getName() %></td>
                                        <td>
                                            <ul class="list-inline" >
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-sucess btn-sm" href="edit-product?id=<%= product.getId() %>">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </li>
                                                <li class="list-inline-item">
                                                    <a class="card-link btn btn-danger btn-sm" href="?productId=<%= product.getId() %>&action=delete" onclick="return confirm('Are you sure you want to delete this product?')">
                                                        <i class="fa fa-trash"></i>
                                                    </a>
                                                </li>
                                            </ul>
                                        </td>
                                    </tr>
                                <% } %>
                            <% } else { %> 
                        <p>No products yet. <a href="add-product">Click Here</a> to add products</p>
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