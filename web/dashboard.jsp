<%@page import="java.sql.ResultSet"%>
<%@page import="com.connection.DatabaseConnection"%>
<%@page import="business.User"%>
<%@page import="business.MyDB"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/admin-header.jsp" %>

<%
  User user = (User) request.getAttribute("user"); 
%>
<div class="container p-0">
    <div class="row">
        <div class="col-md-4 mb-1">
            <div class=" jumbotron-fluid">
                <div  style="padding: 50px 0">
                    <h2 class="header-line"><b>ADMIN DASHBOARD</b></h2>
                    <h5 class="font-weight-bolder"> Welcome <%= user.getFullName() %></h5> 
                    <div class="content-wrapper">
                <div class="container">
                    <div class="row pad-botm">
                        <div class="col-md-12">
                            
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4 col-xs-6">
                            <div class="alert alert-info back-widget-set text-center">
                                <i class="fa fa-history fa-5x"></i>
                            <%
                                //Count product query
                                ResultSet totalProduct = DatabaseConnection.getResultFromSqlQuery("select count(*) from products");
                                totalProduct.next();
                                int allProducts = totalProduct.getInt(1);
                            %>
                            <h3>
                                <%=allProducts%>
                            </h3>
                            Total Products
                        </div>
                    </div>
                            
                             <div class="col-md-4 col-sm-4 col-xs-6">
                            <div class="alert alert-info back-widget-set text-center">
                                <i class="fa fa-history fa-5x"></i>
                            <%
                                //Count service query
                                ResultSet totalService = DatabaseConnection.getResultFromSqlQuery("select count(*) from services");
                                totalService.next();
                                int allServices = totalService.getInt(1);
                            %>
                            <h3>
                                <%=allServices%>
                            </h3>
                            Total Services
                        </div>
                    </div>
                            
                    <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="alert alert-success back-widget-set text-center">
                            <i class="fa fa-users fa-5x"></i>
                            <%
                                //Count customer query
                                ResultSet totalCustomer = DatabaseConnection.getResultFromSqlQuery("select count(*) from users");
                                totalCustomer.next();
                                int allCustomer = totalCustomer.getInt(1);
                            %>
                            <h3><%=allCustomer%></h3>
                            Total Users
                        </div>
                    </div>
                            
                             <div class="col-md-4 col-sm-4 col-xs-6">
                            <div class="alert alert-info back-widget-set text-center">
                                <i class="fa fa-history fa-5x"></i>
                            <%
                                //Count product query
                                ResultSet totalCompany = DatabaseConnection.getResultFromSqlQuery("select count(*) from companies");
                                totalCompany.next();
                                int allCompanies = totalCompany.getInt(1);
                            %>
                            <h3>
                                <%=allCompanies%>
                            </h3>
                            Total Companies
                        </div>
                    </div>
                            
                    <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="alert alert-warning back-widget-set text-center">
                            <i class="fa fa-recycle fa-5x"></i>
                            <%
                                //Count orders query
                                ResultSet totalOrders = DatabaseConnection.getResultFromSqlQuery("select count(*) from orders");
                                totalOrders.next();
                                int allOrders = totalOrders.getInt(1);
                            %>
                            <h3><%=allOrders%></h3>
                            Total Orders
                        </div>
                            
                    </div>
                            
                            <div class="col-md-4 col-sm-4 col-xs-6">
                        <div class="alert alert-warning back-widget-set text-center">
                            <i class="fa fa-recycle fa-5x"></i>
                            <%
                                //Count orders query
                                ResultSet totalCategories = DatabaseConnection.getResultFromSqlQuery("select count(*) from categories");
                                totalCategories.next();
                                int allCategories = totalCategories.getInt(1);
                            %>
                            <h3><%=allCategories%></h3>
                            Total Categories
                        </div>
                            
                    </div>
                            
                </div>
            </div>
        </div>
                    
                    
                    
                    
                </div> 
           </div>
        </div>
    </div>
</div>
                    
<!-- footer -->
<%@include file="./includes/admin-footer.jsp" %>