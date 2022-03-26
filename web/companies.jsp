<%-- 
    Document   : companies
    Created on : Mar 7, 2022, 12:07:01 PM
    Author     : ERICK
--%>

<%@page import="business.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@include file="./includes/header.jsp" %>
        
        <div class="container p-0">
            <div class="row mb-1">
        <div class="col-12">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="./">Home</a></li>
                    <li class="breadcrumb-item active">Companies</li>
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
    <% ArrayList<Company> companies = (ArrayList) request.getAttribute("companies"); %>
    <% for(Company company : companies) { %>
    <div class="row">
        <div class="col-sm-4 ">
            <img class="img-fluid" src="data:image/jpeg;base64,<%= company.getImageToBase64() %>" alt="<%= company.getName() %>"  >
        </div>
        <div class="col-sm-8 ">
            <div class="card bg-transparent ">
                <div class="card-body">
                    <h4 class="card-title"><%= company.getName() %></h4>
                    <p class="card-text"><%= company.getCategory().getName()%></p>
                    <p class="card-text"><%= company.getDescription() %></p>
                    <a href="/<%= company.getWebsite() %>" class="btn btn-primary stretched-link"><%= company.getWebsite()%></a>
                </div>
            </div>
        </div>
    </div> 
    <% } %>
<!-- footer -->
<%@include file="./includes/footer.jsp" %>
