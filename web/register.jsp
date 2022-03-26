<%-- 
    Document   : register
    Created on : Mar 5, 2022, 5:15:24 PM
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./includes/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-8 col-md-6 mx-auto">
            <div class="card bg-transparent">
                <div class="card-header bg-transparent"><b>Create account</b></div>
                <div class="card-body">
            <%
                String message =  (String) request.getAttribute("message");
                String messageType =  (String) request.getAttribute("messageType");
                String alertType = message != null ? "alert" + messageType : "alert-success";
            %>

            <% if(message != null) { %>
            <div class="alert <%= alertType %> alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <%= message %>
            </div>
            <% } %>
            <form action="register" method="POST">
                <div class="form-group">
                  <label for="first_name">First Name</label>
                  <input type="text" class="form-control" placeholder="First Name" name="first_name" id="first_name" required>
                </div>
                <div class="form-group">
                  <label for="last_name">Last Name</label>
                  <input type="text" class="form-control" placeholder="Last Name" name="last_name" id="last_name" required>
                </div>
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="email" class="form-control" placeholder="Enter email" name="email" id="email" required>
                </div>
                
                <div class="form-group">
                  <label for="phone_number">Phone number</label>
                  <input type="text" class="form-control" placeholder="Phone number" name="phone_number" id="phone_number"required
                          pattern="^254[1|7][0-9]{8}">
                </div>
                <div class="form-group">
                  <label for="pwd">Password</label>
                  <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
        <div class="card-footer bg-transparent">
            <p class="lead text-center"><b>Already have an Account? <a href="login">Login</a></b></p>
        </div>
      </div>
</div>
<!-- footer -->
<%@include file="./includes/footer.jsp" %>