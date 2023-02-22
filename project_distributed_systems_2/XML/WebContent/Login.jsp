<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

    <!-- Template Main CSS File -->
 
  <link rel="stylesheet" href="css/user.css">


    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i" rel="stylesheet">

      <script type="text/javascript" src="js/validator.js"> </script>
	<script>

  
     var email_validation = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
     var password_validation = /^(?=.*[A-Z]).{8,}$/;
     

         </script>
</head>
<body>


 <main id="main">

       <section class="inner-page">
        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <form action="${pageContext.request.contextPath}/ManageLogin"  method="POST" id="signup-form" name="myform" class="signup-form" onsubmit="return FormRegistValidator(this ,password_validation )">
                            <h2 class="form-title">Login</h2>
                             &nbsp;
                            <div class="form-group">
                                <input type="text" class="form-input input-lg " required name="username" id="username" placeholder="Username"/>
                            </div>
                            <div class="form-group">
                                <input type="password" required class="form-input input-lg "  name="password" id="password"  placeholder="Password"/>
                            </div>

                            <div class="form-group">
                                <div class="">
                                    <div class="g-recaptcha"  data-sitekey="6LdKRacZAAAAAJdnGKbHbk7NsRWm9hKyjMhPWmGk"></div>
                                </div>
                            </div>
 &nbsp;
                            <div class="form-group_2">
                                <input type="submit" name="submit" id="submit" type="button" class="btn btn-primary"  value="Login"/>
                            </div>
                            <p id="invalid" class="invalid" style="font-size: 16px;"/>
                        </form>
                        
                        <%
                        if (session.getAttribute("loginAttempt") !=null) {
                        boolean attempt = (boolean) session.getAttribute("loginAttempt");
                        
                        if (attempt){
                        	
                        %>
                        
                        <h3 style="color:red;">Wrong username or password combination</h3>
                        
                        <% 
                        session.removeAttribute("loginAttempt");

                        	
                        }
                        }
                        %>
                        
                        <!--  
                        <p class="loginhere">
                            Don't have an account yet ?
                        </p>
                        <form action="${pageContext.request.contextPath}/ManageGoToRegister"  method="POST" id="signup-form" name="formRegister" class="signup-form" onsubmit="">
                        
                        <div class="form-group_2">
                                <input type="submit" name="submit" id="submit" type="button" class="btn btn-primary"  value="Register"/>
                            </div>
                        
                        </form>
                        -->
                        
                    </div>
                </div>
            </section>
        </div>
       </section>
   </main>


</body>
</html>