<%-- 
    Document   : products
    Created on : Mar 5, 2022, 5:14:22 PM
    Author     : ERICK
--%>

<%@page import="business.Product"%>
<%@page import="business.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/header.jsp" %>

<div class="container">
    <div class="row mb-1">
        <div class="col-12">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="./">Home</a></li>
                    <li class="breadcrumb-item active">Products</li>
                </ul>
            </div>
            <% ArrayList<Category> categories = (ArrayList) request.getAttribute("categories"); %>

            <% if( categories != null) { %>
            
            <div class="pull-right">
                    <form class="form-inline" action="search"method="POST">
                      <input class="form-control mr-sm-2" type="text" placeholder="Search">
                      <button class="btn btn-success" type="submit">Search</button>
                    </form> </div>
                
            <% } %>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 d-none d-md-block">

            </div>
            <div class="col-md-8">
                <div class="row">
                    <% ArrayList<Product> products = (ArrayList) request.getAttribute("products"); %>
                    <% for(Product product : products) { %>
                    <div class="col-6 col-md-4  mb-2">
                        <div class="card bg-transparent shadow h-100 ">
                            <div class="card-img-top d-flex justify-content-between align-items-center" style="max-height: 150px">
                                <img style="width: 100%; height: 100%;" class="img-fluid" src="data:image/jpeg;base64,<%= product.getImageToBase64() %>" alt="<%= product.getSlug() %>" >
                            </div>
                            <div class="card-body">
                                <h4 class="card-title"><%= product.getName() %></h4>
                                <% if(product.getMrpPrice() == 0 ) { %>
                                    <span class="text-dark">Ksh <%= product.getPrice() %></span>
                                <% } else { %>
                                <span>
                                    <strong class="text-success"><ins>Ksh <%= product.getMrpPrice() %></ins></strong>
                                    <del class="text-danger small">Ksh <%= product.getPrice() %></del>
                                </span>
                                 <% } %>
                              <a class="btn btn-warning btn-sm my-3" href="add-to-cart?slug=<%= product.getSlug() %>">Add Cart</a>
                              <a href="details?slug=<%= product.getSlug() %>" class="btn btn-primary ">View</a>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>  
    </div>
    
<!-- footer -->
<%@include file="./includes/footer.jsp" %>