<%-- 
    Document   : footer
    Created on : Mar 4, 2022, 3:50:27 PM
    Author     : ERICK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="footer">
    <div class="row">
        <div class="col-12 mb-2">
            <div class="text-center">
                <ul class="list-inline">
                    <li class="list-inline-item">
                        <a href="#" target="_blank">
                            <i class="fa fa-facebook-square"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#" target="_blank">
                            <i class="fa fa-instagram-square"></i>
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="#" target="_blank">
                            <i class="fa fa-twitter-square"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-4  mb-2">
            <div class="d-flex justify-content-center align-items-center">
                <a href="/" class="">
                    <img class="rounded-circle" src="./assets/img/1.jpg" alt="Logo" style="width:64px; height: 64px">
                </a>
            </div>
        </div>
        <div class="col-md-4   mb-2">
            <h5 class="text-primary">Contact Us</h5>
            <div class="text-left">
                <ul class="list-unstyled">
                    <li><i class="glyphicon glyphicon-map-marker mr-1" aria-hidden="true"></i> Nakuru, <span>Kenya.</span></li>
                    <li><i class="glyphicon glyphicon-envelope mr-1" aria-hidden="true"></i> <a href="mailto:erickcompsci@gmail.com">isps@gmail.com</a></li>
                    <li><i class="glyphicon glyphicon-earphone mr-1" aria-hidden="true"></i> +254705599058</li>
                </ul>
            </div>
        </div>
        <div class="col-md-4   mb-2">
            <h5 class="text-primary">Information</h5>
            <div>
                 <ul class="list-unstyled">
                    <li>
                        <a href="about-us.jsp">About Us</a>
                    </li>
                    <li>
                        <a href="privacy-policy.jsp">Privacy Policy</a>
                    </li>
                    <li>
                        <a href="terms-and-conditions.jsp">Terms & Conditions</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="col">
            <br/><br/><br/>
            <center>
                <div class="container">
                    <h4>
                        &copy;  <span id="year"></span> ISPS System |<a href="https://data-flair.training" target="_blank"> Designed by : Erick Baraza</a>
                    </h4>
                </div>
            </center>
        </div>
</footer>
<script>
    document.getElementById("year").innerHTML = new Date().getFullYear();
</script>
</div>
    <script src="./assets/jquery/jquery.min.js"></script>
    <script src="./assets/popper.js"></script>
    <script src="./assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="./assets/summernote/summernote.min.js"></script>
    <script src="./js/jquery.easing.min.js"></script>
    <script src="./js/pignose.layerslider.js"></script>
    <script src="./js/jquery.flexslider.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.flexslider').flexslider();
//            $('#layer-slider').pignoseLayerSlider({
//                play: '.btn-play',
//                pause: '.btn-pause',
//                next: '.btn-next',
//                prev: '.btn-prev'
//            });
            function string_to_slug (str) {
                str = str.replace(/^\s+|\s+$/g, ''); // trim
                str = str.toLowerCase();

                // remove accents, swap ñ for n, etc
                var from = "àáäâèéëêìíïîòóöôùúüûñç·/_,:;";
                var to   = "aaaaeeeeiiiioooouuuunc------";
                for (var i=0, l=from.length ; i<l ; i++) {
                    str = str.replace(new RegExp(from.charAt(i), 'g'), to.charAt(i));
                }

                str = str.replace(/[^a-z0-9 -]/g, '') // remove invalid chars
                    .replace(/\s+/g, '-') // collapse whitespace and replace by -
                    .replace(/-+/g, '-'); // collapse dashes

                return str;
            }
            
            $('.name-to-slug').on("keyup", (evt) => {
                let slug = string_to_slug (evt.target.value);
                $('.slug').val(slug);
            })
        });
    </script>
</body>


</html>

