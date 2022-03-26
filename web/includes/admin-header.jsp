<%-- 
    Document   : header
    Created on : Mar 4, 2022, 3:50:11 PM
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Infrastructure service provider</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta content="Official Digital payments platform that enables Kenyan citizens , residents and visitors access and pay for infrastructural services online." name="description" />
    <meta content="eCitizen" name="author" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="./assets/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="./assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all" />
    <link href="./assets/summernote/summernote.min.css" rel="stylesheet" type="text/css" media="all" />
    <link href="./css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />
    <link href="./css/flexslider.css" rel="stylesheet" type="text/css" media="all" />
    <link href="./css/bootstrap.css" rel="stylesheet"/>
    <style>
        
    </style>
</head>

<body>
    <div class="container p-0">
        <div class="row mb-1">
            <div class="col-md-6 d-flex justify-content-between align-items-center">
                <h3 class="font-weight-bold text-primary">The Best infrastructure services provider.</h3>
            </div>
            <div class="col-md-6 text-right">
               
            </div>
        </div>
    </div>
    
    <div class="container p-0">
        <header class="header">
            <nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
                <a class="navbar-brand" href="admin">
                    <img src="./Companies/PC1.PNG" alt="Logo" style="width:40px;">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav ml-auto">
                      <li class="nav-item">
                        <a class="nav-link " href="dashboard">Dashboard</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link " href="a-orders">Orders</a>
                      </li>
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          Companies
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="add-company">Add Company</a>
                          <a class="dropdown-item" href="list-companies">List Companies</a>
                          
                        </div>
                      </li>
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          Products
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="add-product">Add Product</a>
                          <a class="dropdown-item" href="list-products">List Products</a>
                        </div>
                      </li>
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          Services
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="add-services">Add Service</a>
                          <a class="dropdown-item" href="list-services">List Services</a>
                        </div>
                      </li>
                      
                      <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          Categories
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="?tcategory=technology">Technology</a>
                          <a class="dropdown-item" href="?category=electrics-and-electronics">Electrics and Electronics</a>
                          <a class="dropdown-item" href="?category=building-and-construction">Building and Construction</a>
                          <a class="dropdown-item" href="?category=plumbing">Plumbing</a>
                        </div>
                      </li>
                     <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                          Accounts
                        </a>
                        <div class="dropdown-menu">
                          <a class="dropdown-item" href="add-user">Add User</a>
                          <a class="dropdown-item" href="list-users">List Users</a>
                          <span  class="dropdown-divider"></span>
                          <a class="dropdown-item" href="profile.jsp">Profile</a>
                          <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                      </li>
                    </ul>
                </div>
             </nav>
        </header>
