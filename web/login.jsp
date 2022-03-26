<%-- 
    Document   : login
    Created on : Mar 5, 2022, 5:15:11 PM
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-8 col-md-6 mx-auto">
            <div class="card bg-transparent">
                <div class="card-header bg-transparent"><b>Login</b></div>
                <div class="card-body">
                    <%
                        String message =  (String) request.getAttribute("message");
                        String messageType =  (String) request.getAttribute("messageType");
                        String alertType = message != null ? "alert-" + messageType : "alert-success";
                    %>

                    <% if(message != null) { %>
                    <div class="alert <%= alertType %> alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <%= message %>
                    </div>
                    <% } %>
                    <form action="login" method="POST">
                        <% if(request.getParameter("redirect") != null) { %>
                            <input type="hidden" name="redirect" value="<%= request.getParameter("redirect") %>" />
                        <% } %>
                        
                        <div class="form-group">
                          <label for="email">Email address:</label>
                          <input type="email" class="form-control" placeholder="Enter email" name="email" id="email"required="">
                        </div>
                        <div class="form-group">
                          <label for="pwd">Password:</label>
                          <input type="password" class="form-control" placeholder="Enter password" name="password" id="password"required="">
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                </div>
                <div class="card-footer bg-transparent">
                    <p class="lead text-center"><b>Forgot your password? <a href="forget-password.jsp">Click here</a></b></p>
                    <p class="lead text-center"><b>No Account? <a href="register">Register</a></b></p>
                </div>
              </div>
        </div>
    </div>
    
</div>
<!-- footer -->
<%@include file="./includes/footer.jsp" %>