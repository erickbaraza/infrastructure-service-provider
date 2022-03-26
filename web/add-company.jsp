<%@page import="business.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Category> categories =  (ArrayList) request.getAttribute("categories");
%>
<%@include file="./includes/admin-header.jsp" %>
    <div class="content-wrapper">
            <div class="container">
                <div class="row pad-botm">
                    <div class="col-md-12">
                        <h4 class="header-line">Add Company</h4>
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
                        <div class="panel-heading">Add Company</div>
                        <div class="panel-body">
                        <form role="form" action="add-company" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Enter Registration Number</label> 
                                <input class="form-control" type="text" name="reg_no" required />
                            </div>
                            <div class="form-group">
                                <label>Enter Name</label> 
                                <input class="form-control" type="text" name="name" required />
                            </div>
                            <div class="form-group">
                                <label>Email</label> 
                                <input class="form-control" type="email" name="email" required/>
                            </div>
                            <div class="form-group">
                                <label>Phone</label> 
                                <input class="form-control" type="tel" name="phone" required/>
                            </div>
                            <div class="form-group">
                                <label>Company-address</label> 
                                <input class="form-control" type="text" name="address" required/>
                            </div>
                            <div class="form-group">
                                <label>Description</label> 
                                <textarea class="form-control" rows="5" name="description" required></textarea>
                            </div>
                            <div>
                                <label>Company-Category</label> 
                                <select class="form-control " id="category_id" name="category_id" required>
                                <% if( categories != null) { %>
                                    <option value="<%= categories.get(0).getId()%>" selected><%= categories.get(0).getName()%></option>
                                    <% if(categories.size() > 1){ %>
                                        <% for( int i = 1; i < categories.size(); i++) { %>
                                            <option value="<%= categories.get(i).getId()%>"><%= categories.get(i).getName()%></option>
                                        <% }  %>
                                    <% }  %>
                                <% }  %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Website-link</label>
                                <input class="form-control"type="url"name="website"required/>
                            </div>
                             <div class="form-group">
                                <label>Attach Company Image</label> 
                                <input type="file" name="image" id="image" required/>
                                <img class="img-thumbnail" id="image-preview"  src="" width="120" height="120" />
                                <script>
                                    var imageSrc = document.getElementById("image");
                                    var imagePreview = document.getElementById("image-preview");
                                    imageSrc.addEventListener('change', (event) => {
                                        imagePreview.src = URL.createObjectURL(event.target.files[0]);
                                        imagePreview.onload = function() {
                                          URL.revokeObjectURL(imageSrc) // free memory
                                        }
                                    });
                                </script>
                            </div>

                            <button type="submit" class="btn btn-success" >Add Company</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        
<%@include file="/includes/admin-footer.jsp" %>
