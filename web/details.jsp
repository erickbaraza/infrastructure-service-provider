<%-- 
    Document   : details
    Created on : Mar 5, 2022, 7:30:24 PM
    Author     : ERICK
--%>

<%@page import="business.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/header.jsp" %>

<% Product product  = (Product) request.getAttribute("product");%>

<div class="container">
    <div class="row mb-1">
        <div class="col-12">
            <div class="pull-left">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/products">Products</a></li>
                    <li class="breadcrumb-item active"><%= product.getSlug() %></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div>
                <img class="img-thumbnail img-fluid w-100" src="data:image/jpeg;base64,<%= product.getImageToBase64()%>"  alt="<%= product.getName()%>" />
            </div>
        </div>
        <div class="col-md-8">
            <h3 class=""text-primary><%= product.getName() %></h3>
            <div><%= product.getShortDescription() %></div>
            <p class="card-text">
                <span class="text-dark">Ksh <%= product.getPrice() %></span>
                <span>
                    <strong class="text-success"><ins>Ksh <%= "" %></ins></strong>
                    <del class="text-danger small">Ksh <%= ""  %></del>
                </span>
            </p>
            
            <div class="input-group input-group-sm" style="max-width: 250px">
                <div class="input-group-prepend">
                    <button class="btn btn-light btn-sm">
                        <i class="fa fa-minus "></i>
                    </button>
                </div>
                <input class="form-control form-control-sm" type="text" id="quantity" name="quantity" value="1"/>
                <div class="input-group-append">
                    <button class="btn btn-light btn-sm">
                        <i class="fa fa-plus"></i>
                    </button>
                </div>
            </div>
            <div>Stock Status: <%= product.getStatus() %></div>
            <a class="btn btn-warning btn-sm my-3" href="cart?action=add&slug=<%= product.getSlug() %>">Add Cart</a>
        </div>
        <div class="col-12 my-1">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                  <a class="nav-link active" data-toggle="tab" href="#description">Description</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#company-details">Company Details</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane container active" id="description">
                    <%= product.getDescription() %>
                </div>
                <div class="tab-pane container fade" id="company-details">
                    <table class="table table-bordered">
                        <tr>
                            <th>Name</th>
                            <td><%= product.getCompany().getName() %></td>
                        </tr>
                        <tr>
                            <th>Description</th>
                            <td><%= product.getCompany().getDescription() %></td>
                        </tr>
                        <tr>
                            <th>Category</th>
                            <td><%= product.getCompany().getCategory().getName()%></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
        <%@include file="./includes/footer.jsp" %>