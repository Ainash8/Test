<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File uploaded result</title>
</head>
<body>
<h1>${msg}</h1>
File name:<b>${filename}</b>

</body>
</html>
 --%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
  <head>
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
  </head>
    <style>
      body {
        text-align: center;
        padding: 20px 0;
        background: #660033;
       
      }
        h1 {
          color: #88B04B;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          font-size: 40px;
          margin-bottom: 10px;
        }
        p {
          color: #404F5E;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-size:20px;
          margin: 0;
        }
     
      .card {
        background: white;
        padding: 20px;
        border-radius: 4px;
        box-shadow: 0 2px 3px #C8D0D8;
        display: inline-block;
        margin: 0 auto;
      }
      b {
          color: red;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          margin-bottom: 10px;
          }
    </style>
    <body>
      <div class="card">
        <body>
        <form action="redirect" method="GET" modelAttribute="backToHome">
<h1>${msg}</h1>
<p><h3>File name:<b>&nbsp; ${filename}</b></h3></p>
<h3>Your file is saved at :</h3> <b>${folderpath}</b>
<input type="hidden" id="refreshed" value="no"></br>

<button type="submit" id="home" name="home" class="btn-hover color-4">Back to Home</button>
</form>
</body>
      </div>
    </body>
   
 
   
</html>
 
    <script type="text/javascript">
   

         /*   onload=function(){
               var e=document.getElementById("refreshed");
               if(e.value=="no")e.value="yes";
               else{e.value="no";location.reload();}
           }
 */
 
 $(document).ready(function() {
   function disableBack() { window.history.forward() }

   window.onload = disableBack();
   window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
});
 
 

    </script>
   
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
    width: 100px;
    font-size: 16px;
    font-weight: 600;
    color: #fff;
    cursor: pointer;
    margin: 40px;
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

.btn-hover.color-4 {
    background-image: linear-gradient(to right, #fc6076, #ff9a44, #ef9d43, #e75516);
    box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
}


 

    </style>

