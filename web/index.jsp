<%-- 
    Document   : index
    Created on : Mar 4, 2022, 3:51:17 PM
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="./includes/header.jsp" %>
        
<div class="container p-0">
    <div class="row">
        <div class="col-md-4 mb-1">
            <div class=" jumbotron-fluid">
                <div  style="padding: 50px 0">
                    <h3 class="font-weight-bolder">Infrastructure Service Provider</h3> 
                    <p class="lead text-muted">The Best infrastructure services provider.</p>
                    <div class="my-2 ">
                        <div class="">
                            <button class="btn btn-sm btn-outline-secondary "><a href="login">Login</a></button> 
                            
                            <button class="btn btn-sm btn-outline-secondary" ><a href="products">Shop</a></button>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-8 mb-1">
            <!-- carousel sliders -->
            <div id="sliders" class="carousel slide" data-ride="carousel" style="height: 300px; max-height: 300px">
                <ul class="carousel-indicators">
                  <li data-target="#sliders" data-slide-to="0" class="active"></li>
                  <li data-target="#sliders" data-slide-to="1"></li>
                  <li data-target="#sliders" data-slide-to="2"></li>
                </ul>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img class="img-fuild" src="./Companies/Electrical4.jfif" alt="Los Angeles" width="100%" height="300px">
                    <div class="carousel-caption">
                        <h3>Infrastructural services</h3>
                        <p>Functional products and services for your firm</p>
                    </div>
                  </div>
                  <div class="carousel-item">
                      <img class="img-fuild" src="./Companies/New2.PNG" alt="Chicago" width="100%" height="300px">
                    <div class="carousel-caption">
                        <h3>Electrical installation</h3>
                        <p>Reach out anytime, anywhere</p>
                    </div>
                  </div>
                  <div class="carousel-item">
                      <img class="img-fuild" src="./Companies/Projects.PNG" alt="New York" width="100%" height="300px">
                    <div class="carousel-caption">
                        <h3>We Are trusted agents</h3>
                        <p>We value your feedback</p>
                    </div>
                  </div>
                </div>
                <a class="carousel-control-prev" href="#sliders" data-slide="prev">
                  <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#sliders" data-slide="next">
                  <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
    </div>
   

    <!-- top selling products -->
    <div class="row mt-3 border border-secondary">
        
        <div class="col-12 mb-2 bg-primary ">
            <h3 class="text-light">Top Selling Products</h3>
        </div>
        <div class="col-12 mb-2 ">
            <div id="product-sliders" class="carousel slide" data-ride="carousel" style="height: 200px; max-height: 200px">
                <ul class="carousel-indicators">
                  <li data-target="#product-sliders" data-slide-to="0" class="active"></li>
                  <li data-target="#product-sliders" data-slide-to="1"></li>
                  <li data-target="#product-sliders" data-slide-to="2"></li>
                </ul>
                <div class="carousel-inner">
                  <div class="carousel-item active">
                      <div class="row">
                            <div class="col-6 col-md-3 position-relative">
                                <div class="card bg-light w-100 h-100" >
                                    <img class="card-img-top img-fluid" src="./CompAccessories/LAPTOP TABLES.jpg" alt="Card image" style="height: 200px; max-height: 200px">
                                    <div class="card-img-overlay">
                                      <h4 class="card-title">Laptop tables</h4>
                                      <p class="card-text">Purchase one at discounted price.</p>
                                      <a href="products" class="btn btn-primary">View</a>
                                    </div>
                                </div>
                            </div>
                          <div class="col-6 col-md-3 position-relative">
                                <div class="card bg-light w-100 h-100" >
                                    <img class="card-img-top img-fluid" src="./CompAccessories/INKJET PRINTERS.jpg" alt="Card image" style="height: 200px; max-height: 200px">
                                    <div class="card-img-overlay">
                                      <h4 class="card-title">Ink-jet Printer</h4>
                                      <p class="card-text">With three years guarantee.</p>
                                      <a href="products" class="btn btn-primary">View</a>
                                    </div>
                                </div>
                          </div>
                          <div class="col-6 col-md-3 position-relative">
                                <div class="card bg-light w-100 h-100" >
                                    <img class="card-img-top img-fluid" src="./CompAccessories/LENOVO.jpg" alt="Card image" style="height: 200px; max-height: 200px">
                                    <div class="card-img-overlay pt-5 text-primary">
                                      <h4 class="card-title">Lenovo laptop</h4>
                                      <p class="card-text">
                                        <span class="text-dark">Ksh 67000</span>
                                        <span>
                                            <strong class="text-success"><ins>Ksh 67000</ins></strong>
                                            <del class="text-danger small">Ksh 74000</del>
                                        </span>
                                    </p>
                                      <a href="products" class="btn btn-primary">View</a>
                                    </div>
                                </div>
                            </div>
                           <div class="col-6 col-md-3 position-relative">
                                <div class="card bg-light w-100 h-100" >
                                    <img class="card-img-top img-fluid" src="./CompAccessories/phone3.png" alt="Card image" style="height: 200px; max-height: 200px">
                                    <div class="card-img-overlay pt-5 text-primary">
                                      <h4 class="card-title">Tecno spark 7</h4>
                                      <p class="card-text">
                                        <span class="text-dark">Ksh 67000</span>
                                        <span>
                                            <strong class="text-success"><ins>Ksh 67000</ins></strong>
                                            <del class="text-danger small">Ksh 74000</del>
                                        </span>
                                    </p>
                                      <a href="products" class="btn btn-primary">View</a>
                                    </div>
                                </div>
                            </div>
                      </div>
                      
                  </div>
                </div>
                <a class="carousel-control-prev" href="#product-sliders" data-slide="prev">
                  <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#product-sliders" data-slide="next">
                  <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
    </div>
</div>

<!-- footer -->
<%@include file="./includes/footer.jsp" %>
