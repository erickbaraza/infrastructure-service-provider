<%@page import="business.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Category> categories =  (ArrayList) request.getAttribute("categories");
    ArrayList<Company> companies =  (ArrayList) request.getAttribute("companies");
%>

<%@include file="./includes/admin-header.jsp" %>

        <div class="content-wrapper">
            <div class="container">
                <div class="row pad-botm">
                    <div class="col-md-12">
                        <h4 class="header-line">Add Service</h4>
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
                        <div class="panel-heading">Add Service</div>
                        <div class="panel-body">
                            <form role="form" action="add-services" method="post"
                                  enctype="multipart/form-data">
                                <div class="form-group">
                                    <label>Service Name</label> 
                                    <input class="form-control name-to-slug" type="text" id="name" name="name" required />
                                </div>
                                <div class="form-group">
                                    <label>Service Slug</label> 
                                    <input class="form-control slug" type="text" id="slug" name="slug" required />
                                </div>
                                <div class="form-group">
                                    <label>Price</label> 
                                    <input class="form-control" type="number" id="price" name="price" required/>
                                </div>
                                <div class="form-group">
                                    <label>Short Description</label> 
                                    <textarea class="form-control" rows="3" id="short_description" name="short_description" required></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Description</label> 
                                    <textarea class="form-control" rows="5" id="description" name="description" required></textarea>
                                </div>
                               
                                <div class="form-group">
                                    <label>Status</label> 
                                    <select class="form-control" id="status" name="status" required>
                                        <option value="active" selected>Active</option>
                                        <option value="inactive">In-Active</option>
                                    </select>
                                </div>
                                  <div>
                                <label>Service-Category</label> 
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
                                    <label>Seller Company</label> 
                                    <select class="form-control " id="company_id" name="company_id" required>
                                    <% if( companies != null) { %>
                                        <option value="<%= companies.get(0).getId()%>" selected><%= companies.get(0).getName()%></option>
                                        <% if(companies.size() > 1){ %>
                                            <% for( int i = 1; i < companies.size(); i++) { %>
                                                <option value="<%= companies.get(i).getId()%>"><%= companies.get(i).getName()%></option>
                                            <% }  %>
                                        <% }  %>
                                    <% }  %>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Attach Service image if any </label> 
                                    <input type="file" name="image"  id="image" />
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

                                <button type="submit" class="btn btn-success">Add Service</button>
                                <button type="reset" class="btn btn-danger">Reset</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="./includes/admin-footer.jsp" %>