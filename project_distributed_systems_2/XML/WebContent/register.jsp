<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>


<link href="assets/img/icon.png" rel="icon">
  
  
    <!-- Template Main CSS File -->
 


    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i" rel="stylesheet">
  <link rel="stylesheet" href="css/user.css">

  <!-- Vendor CSS Files -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="vendor/icofont/icofont.min.css" rel="stylesheet">




</head>
<body>






 <main id="main">

       <section class="inner-page">
        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <form method="POST" id="signup-form" class="signup-form" name="FormRegist">
                            <h2 class="form-title">Create <span>account</span></h2>
                                 &nbsp;
                                 <p>
                            
                            <div class="form-group">
                                <span style="color: red !important; display: inline; float: none;">*</span> 
                                <input type="text" class="form-input input-lg" name="username" id="username" placeholder="Username" required/>
                                
                            </div>
                            
                    
                            
                            <div class="form-group">
                                <span style="color: red !important; display: inline; float: none;">*</span> 
                                <input type="email" class="form-input input-lg" name="email" id="email"  placeholder="Your Email" required/>
                            </div>
                            
                            <div class="form-group">
                                <span style="color: red !important; display: inline; float: none;">*</span> 
                                <input type="password" class="form-input input-lg"  name="password" id="password"  placeholder="Password" required/>
                            </div>
                            
                             <div class="form-group">
                                 <span style="color: red !important; display: inline; float: none;">*</span> 
                                <input type="password" class="form-input input-lg"  name="password_confirm" id="password_confirm"  placeholder="Password Confirm" required/>
                            </div>
                                 
                                         <div class="form-group">
                                             <span style="color: red !important; display: inline; float: none;">*</span> 
                                             <label  for="gender" >Gender: </label>
                                <select id="gender" class="form-input input-sm " name="gender" required>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                  
                                </select>
                            </div>
                            
                               &nbsp;
                            <div class="form-group">
                                <span style="color: red !important; display: inline; float: none;">*</span> 
                                <label > Age: </label>
                                <p>
                                <input type="date" class="md-form md-outline" inline="true" name="birthday" id="birthday" required>
                            </div>
                                 
                                 
                            
                               &nbsp;
                            
                            <div class="form-group">
                                <img src="captcha.php" alt="Captcha code" >
                            </div>    
                             <div class="form-group">
                                                                 <span style="color: red !important; display: inline; float: none;">*</span> 

                                <input type="text" name="captcha" class="form-input input-lg"    placeholder="Type your captcha" required>
                            </div>    
                               &nbsp;
                            <div class="form-group_2">
                                <input type="submit" name="submit" id="submit"  class="btn btn-primary" value="Sign up"/>
                            </div>

                            <p id="invalid" class="invalid" style="font-size: 16px;"/>
                        </form>
                        <p class="loginhere">
                            Already have an account ?
                            <a href="Login.jsp" class="loginhere-link">Login here</a>
                        </p>
                    </div>
                </div>
            </section>
        </div>
       </section>
           </main>

 
 
 
 
 


</body>
</html>