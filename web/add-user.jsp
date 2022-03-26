<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/admin-header.jsp" %>
    <div class="content-wrapper">
            <div class="container">
                <div class="row pad-botm">
                    <div class="col-md-12">
                        <h4 class="header-line">Add User</h4>
                    </div>
                </div>
         <%
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
        %>
        <%  if (message != null) {
                String alertType = "alert-" + messageType;
        %>
           <div class="alert <%= alertType %> alert-dismissible show " id="success">
                <p><%= message %>.</p>
                <button class="close" data-dismiss="alert">&times;</button>
            </div>
        <% request.removeAttribute("message") ;%>
        <% } %>
        
        <div class="row">
                <div class="col-md-8 mx-auto">
                    <div class="panel panel-info">
                        <div class="panel-heading">Add User</div>
                        <div class="panel-body">
                        <form role="form" action="add-user" method="post" >
                            <div class="form-group">
                                <label>Enter First Name</label> 
                                <input class="form-control" type="text" name="first_name" required />
                            </div>
                            <div class="form-group">
                                <label>Enter Last Name</label> 
                                <input class="form-control" type="text" name="last_name" required />
                            </div>
                            <div class="form-group">
                                <label>Email</label> 
                                <input class="form-control" type="email" name="email" required/>
                            </div>
                            <div class="form-group">
                                <label>Role</label> 
                                <select class="form-control" id="role" name="role" required>
                                    <option value="Admin" >Admin</option>
                                    <option value="Customer" selected>Customer</option>
                                    </select>
                            </div>   
                            <div class="form-group">
                                <label>Phone number</label> 
                                <input class="form-control" type="text" name="phone_number"  pattern="^254[1|7][0-9]{8}"required/>
                            </div><!-- comment -->
                            
                            <div class="form-group">
                                <label>Starting Password</label> 
                                <input class="form-control" type="password" name="password" required=""
                                       oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);
                                       maxlength="10"
                                       />
                            </div>
                            
                            <button type="submit" class="btn btn-success" >Add User</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                            
                                         </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        
        <%@include file="/includes/admin-footer.jsp" %>
        