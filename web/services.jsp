<%-- 
    Document   : products
    Created on : Mar 5, 2022, 5:14:22 PM
    Author     : ERICK
--%>

<%@page import="business.Service"%>
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
                    <li class="breadcrumb-item active">Services</li>
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
<div class="col-md-8">
                <div class="row">
        <% ArrayList<Service> services = (ArrayList) request.getAttribute("services"); %>
        <% for(Service service :  services) { %>
          <div class="col-6 col-md-4  mb-2">
                        <div class="card bg-transparent shadow h-100 ">
                <div class="card-img-top d-flex justify-content-between align-items-center" style="max-height: 150px">
                    <img src="data:image/jpeg;base64,<%= service.getImageToBase64() %>" alt="<%= service.getSlug() %>" width="100%" >
                </div>
                <div class="card-body">
                    <h4 class="card-title"><%= service.getName() %></h4>
                  
                  <a href="services/<%= service.getSlug() %>" class="btn btn-primary stretched-link">View</a>
                </div>
            </div>
        </div>
        <% } %>
</div>
<!-- footer -->
<%@include file="./includes/footer.jsp" %>