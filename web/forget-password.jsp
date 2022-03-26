<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./includes/header.jsp" %>

 <div class="col-8 col-md-6 mx-auto">
            <div class="card bg-transparent">
                <div class="card-header bg-transparent"><b>If you have forget password, enter your email and phone number to change your password </b></div>
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
            <form action="ForgotPassword" method="POST">
                <div class="form-group">
                  <label for="email">Email address</label>
                  <input type="text" class="form-control" placeholder="Email address" name="email" id="email" required>
                </div>
                <div class="form-group">
                  <label for="phone_number">Phone number</label>
                  <input type="text" class="form-control" placeholder="Phone number" name="phone_number" id="phone_number" required minlength="10" maxlength="10" pattern="^0[1|7][0-9]{8}">
                </div>
                 <div class="form-group">
                  <label for="password">New password</label>
                  <input type="password" class="form-control" placeholder="New password" name="password" id="password" required>
                </div>
                <div class="form-group">
                  <label for="password-match">Confirm New password</label>
                  <input type="password" class="form-control" placeholder="New password" name="password-match" id="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="card-footer bg-transparent">
            <p class="lead text-center"><b><a href="login">Login</a></b></p>
        </div>
      </div>
</div>
</form>


<%@include file="./includes/footer.jsp" %>