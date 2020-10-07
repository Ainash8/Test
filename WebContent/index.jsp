<%-- <%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<html>
<head>
<title>
Rich Trials
</title>
</head>
<body>
<center>
<div style="text-align:center;">
<form action ="uploadimage" method = "POST" modelAttribute = "fileUpload"
         enctype = "multipart/form-data">
         Please select a file to convert into mapping sheet </br></br>
         <input type = "file" name = "profile" /></br></br>
         <input type = "submit" value = "Convert" />
      </form>
</div>
</center>
</body> --%>


<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
width="800px" height="600px" viewBox="0 0 800 600"
enable-background="new 0 0 800 600" xml:space="preserve">
<linearGradient id="SVGID_1_" gradientUnits="userSpaceOnUse"
x1="174.7899" y1="186.34" x2="330.1259" y2="186.34"
gradientTransform="matrix(0.8538 0.5206 -0.5206 0.8538 147.9521 -79.1468)">
<stop offset="0" style="stop-color:#FFC035" />
<stop offset="0.221" style="stop-color:#F9A639" />
<stop offset="1" style="stop-color:#E64F48" />
</linearGradient>
<circle fill="url(#SVGID_1_)" cx="266.498" cy="211.378" r="77.668" />
<linearGradient id="SVGID_2_" gradientUnits="userSpaceOnUse"
x1="290.551" y1="282.9592" x2="485.449" y2="282.9592">
<stop offset="0" style="stop-color:#FFA33A" />
<stop offset="0.0992" style="stop-color:#E4A544" />
<stop offset="0.9624" style="stop-color:#00B59C" />
</linearGradient>
<circle fill="url(#SVGID_2_)" cx="388" cy="282.959" r="97.449" />
<linearGradient id="SVGID_3_" gradientUnits="userSpaceOnUse"
x1="180.3469" y1="362.2723" x2="249.7487" y2="362.2723">
<stop offset="0" style="stop-color:#12B3D6" />
<stop offset="1" style="stop-color:#7853A8" />
</linearGradient>
<circle fill="url(#SVGID_3_)" cx="215.048" cy="362.272" r="34.701" />
<linearGradient id="SVGID_4_" gradientUnits="userSpaceOnUse"
x1="367.3469" y1="375.3673" x2="596.9388" y2="375.3673">
<stop offset="0" style="stop-color:#12B3D6" />
<stop offset="1" style="stop-color:#7853A8" />
</linearGradient>
<circle fill="url(#SVGID_4_)" cx="482.143" cy="375.367" r="114.796" />
<linearGradient id="SVGID_5_" gradientUnits="userSpaceOnUse"
x1="365.4405" y1="172.8044" x2="492.4478" y2="172.8044"
gradientTransform="matrix(0.8954 0.4453 -0.4453 0.8954 127.9825 -160.7537)">
<stop offset="0" style="stop-color:#FFA33A" />
<stop offset="1" style="stop-color:#DF3D8E" />
</linearGradient>
<circle fill="url(#SVGID_5_)" cx="435.095" cy="184.986" r="63.504" />
</svg>


<body>
<form action="uploadimage" method="POST" modelAttribute="fileUpload"
enctype="multipart/form-data">
<div class="form-box">
<div class="head">Axis Bank</div>

<br>
<br>
<br>

<div id="example2">
<input type="file" id="real-file" name="profile" hidden="hidden" />
<button type="button" id="custom-button">CHOOSE FILE</button>
<span id="custom-text">No file chosen, yet.</span>
</div>
<br>
<br>
<br>
<br>





&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<input type="submit"  value="Convert" class="btn-hover color-4" />



</div>
</form>
</body>

</html>


<script>
 
 
 
 const realFileBtn = document.getElementById("real-file");
 const customBtn = document.getElementById("custom-button");
 const customTxt = document.getElementById("custom-text");

 customBtn.addEventListener("click", function() {
   realFileBtn.click();
 });

 realFileBtn.addEventListener("change", function() {
   if (realFileBtn.value) {
     customTxt.innerHTML = realFileBtn.value.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
   } else {
     customTxt.innerHTML = "No file chosen, yet.";
   }
 });
 
 </script>


<style>


#example2 {
  border: 2px solid;
  padding: 10px;
  margin-left: 15px;
  margin-right: 15px;
 
}
body {
background-color: #cccccc;
max-height: 500px;
/* background: linear-gradient(110deg, #fdcd3b 60%, #ffed4b 60%)
background: linear-gradient(110deg, rgba(0, 0, 200, 0.6) , rgba(0, 0, 200, 0) ),
linear-gradient(120deg, rgba(50, 150, 100, 0.6) , rgba(0, 0, 100, 0) ),
linear-gradient(240deg, rgba(150, 50, 50, 0.6) , rgba(0, 0, 200, 0) ); */

background:
  linear-gradient(100deg, rgba(0, 0, 100, 0.6) , rgba(0, 0, 200, 0) ),
linear-gradient(110deg, #990033 60%, #FFFFFF 60%),
linear-gradient(100deg, rgba(100, 50, 50, 0.9) , rgba(0, 0, 200, 0) ),
linear-gradient(100deg, #ffed4b 60%, #fdcd3b 60%);
}

.form-box {
background: #fff;
margin: -500px auto;
margin-left: 650px;
max-width: 400px;
max-height: 500px;
box-shadow: 0 3px 6px 0px rgba(0, 0, 0, 0.16), 0 3px 6px 0px
rgba(0, 0, 0, 0.23);
}

form#login-form {
overflow: hidden;
position: relative;
padding: 70px;
}

.head {
color: #fff;
font-size: 34px;
font-weight: normal;
padding: 40px 0;
text-align: center;
text-transform: uppercase;
background: #990066;
}
</style>


<style>
.form-group {
margin-bottom: 15px;
position: relative;
width: 100%;
overflow: hidden;
}

.form-group .label-control {
color: #888;
display: block;
font-size: 14px;
position: absolute;
top: 0;
left: 0;
padding: 0;
width: 100%;
pointer-events: none;
height: 100%;
}

.form-group .label-control::before, .form-group .label-control::after {
content: "";
left: 0;
position: absolute;
bottom: 0;
width: 100%;
}

.form-group .label-control::before {
border-bottom: 1px solid #B9C1CA;
transition: transform 0.3s;
-webkit-transition: -webkit-transform 0.3s;
}

.form-group .label-control::after {
border-bottom: 2px solid #03A9F4;
-webkit-transform: translate3d(-100%, 0, 0);
transform: translate3d(-100%, 0, 0);
-webkit-transition: -webkit-transform 0.3s;
transition: transform 0.3s;
}

.form-control {
border: none;
border-radius: 0;
margin-top: 20px;
padding: 12px 0;
width: 100%;
font-size: 14px;
}

.form-control:focus {
outline: none;
box-shadow: none;
}

.form-group .label-control .label-text {
-webkit-transform: translate3d(0, 30px, 0) scale(1);
-moz-transform: translate3d(0, 30px, 0) scale(1);
transform: translate3d(0, 30px, 0) scale(1);
-webkit-transform-origin: left top;
-moz-transform-origin: left top;
transform-origin: left top;
-webkit-transition: 0.3s;
-moz-transition: 0.3s;
transition: 0.3s;
position: absolute;
}

.active .label-control::after {
-webkit-transform: translate3d(0%, 0, 0);
transform: translate3d(0%, 0, 0);
}

.active .label-control .label-text {
opacity: 1;
-webkit-transform: scale(0.9);
-moz-transform: scale(0.9);
transform: scale(0.9);
color: #03A9F4 !important;
}

.input-field label:before {
content: '';
position: absolute;
bottom: 0;
left: 0;
width: 100%;
border-bottom: 1px solid #B9C1CA;
transition: transform 0.3s;
-webkit-transition: -webkit-transform 0.3s;
}

input.btn[type="submit"] {
background: #6498fe;
border: none;
border-radius: 2px;
color: #fff;
cursor: pointer;
font-size: 16px;
font-weight: bold;
letter-spacing: 3px;
margin: 5px 0;
margin-right: 5px 0;
outline: medium none;
overflow: hidden;
padding: 10px;
text-transform: uppercase;
transition: all 0.15s ease-in-out 0s;
width: 100%;

/* box-shadow: 0 1px 2px 0px rgba(0, 0, 0, 0.16), 0 1px 2px 0px
rgba(0, 0, 0, 0.23); */
}

input.btn[type="submit"]:hover {
background: #4b81eb;
box-shadow: 0 2px 4px 0px rgba(0, 0, 0, 0.16), 0 2px 4px 0px
rgba(0, 0, 0, 0.23);
}

.text-p {
font-size: 14px;
text-align: center;
margin: 10px 0;
}

.text-p a {
color: #175690;
}
</style>


<style>
#custom-button {
padding: 10px;
color: white;
 background-color: #f4511e;
border: 1px solid #000;
border-radius: 5px;
cursor: pointer;


  display: inline-block;
  border-radius: 4px;

   border: none;
  color: #FFFFFF;
  transition: all 0.5s;
  cursor: pointer;
  margin: 20px;
}
}

#custom-button:hover {
background-color: #00b28f;
}

#custom-text {
margin-left: 10px;
font-family: sans-serif;
color: #aaa;
}
</style>

<style>

* {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.buttons {
    margin: 50%;
    text-align: center;
}

.btn-hover {
    width: 200px;
    font-size: 16px;
    font-weight: 600;
    color: #fff;
    cursor: pointer;
    margin: 20px;
    height: 55px;
    text-align:center;
    border: none;
    background-size: 300% 100%;

    border-radius: 50px;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.btn-hover:hover {
    background-position: 100% 0;
    moz-transition: all .4s ease-in-out;
    -o-transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    transition: all .4s ease-in-out;
}

.btn-hover:focus {
    outline: none;
}

.btn-hover.color-1 {
    background-image: linear-gradient(to right, #25aae1, #40e495, #30dd8a, #2bb673);
    box-shadow: 0 4px 15px 0 rgba(49, 196, 190, 0.75);
}
.btn-hover.color-2 {
    background-image: linear-gradient(to right, #f5ce62, #e43603, #fa7199, #e85a19);
    box-shadow: 0 4px 15px 0 rgba(229, 66, 10, 0.75);
}
.btn-hover.color-3 {
    background-image: linear-gradient(to right, #667eea, #764ba2, #6B8DD6, #8E37D7);
    box-shadow: 0 4px 15px 0 rgba(116, 79, 168, 0.75);
}
.btn-hover.color-4 {
    background-image: linear-gradient(to right, #fc6076, #ff9a44, #ef9d43, #e75516);
    box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
}
.btn-hover.color-5 {
    background-image: linear-gradient(to right, #0ba360, #3cba92, #30dd8a, #2bb673);
    box-shadow: 0 4px 15px 0 rgba(23, 168, 108, 0.75);
}
.btn-hover.color-6 {
    background-image: linear-gradient(to right, #009245, #FCEE21, #00A8C5, #D9E021);
    box-shadow: 0 4px 15px 0 rgba(83, 176, 57, 0.75);
}
.btn-hover.color-7 {
    background-image: linear-gradient(to right, #6253e1, #852D91, #A3A1FF, #F24645);
    box-shadow: 0 4px 15px 0 rgba(126, 52, 161, 0.75);
}
.btn-hover.color-8 {
    background-image: linear-gradient(to right, #29323c, #485563, #2b5876, #4e4376);
    box-shadow: 0 4px 15px 0 rgba(45, 54, 65, 0.75);
}
.btn-hover.color-9 {
    background-image: linear-gradient(to right, #25aae1, #4481eb, #04befe, #3f86ed);
    box-shadow: 0 4px 15px 0 rgba(65, 132, 234, 0.75);
}
.btn-hover.color-10 {
        background-image: linear-gradient(to right, #ed6ea0, #ec8c69, #f7186a , #FBB03B);
    box-shadow: 0 4px 15px 0 rgba(236, 116, 149, 0.75);
}
.btn-hover.color-11 {
       background-image: linear-gradient(to right, #eb3941, #f15e64, #e14e53, #e2373f);  box-shadow: 0 5px 15px rgba(242, 97, 103, .4);
}





</style>
<script>
 
 
 $('.form-group input').on('focus blur', function (e) {
   $(this).parents('.form-group').toggleClass('active', (e.type === 'focus' || this.value.length > 0));
}).trigger('blur');
 
 


 </script>
