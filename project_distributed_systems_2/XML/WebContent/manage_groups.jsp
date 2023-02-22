<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="chats.ChatHandler"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data_info.Message"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

.dropbtn {
  background-color: #ffb03b;
  color: white;
  padding: 20px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}


/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #f1f1f1}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
  display: block;
}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {
  color: #ffc56e;
  text-decoration: none;
  
  
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Groups</title>


<link href="assets/img/icon.png" rel="icon">


<!-- Template Main CSS File -->



<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i"
	rel="stylesheet">
<link rel="stylesheet" href="css/comment.css">

<!-- Vendor CSS Files -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="vendor/icofont/icofont.min.css" rel="stylesheet">




</head>
<body>


	
	
<!-- Start Header -->

  <!-- ======= Top Bar ======= -->
  <section id="topbar" class="d-none d-lg-flex align-items-center fixed-top "">
    <div class="container text-right">
      <i class="icofont-phone"></i> +1 351 937050860

    </div>
  </section>

 <header id="header" class="fixed-top d-flex align-items-center ">
    <div class="container d-flex align-items-center">

      <div class="logo mr-auto">
        <h1 class="text-light"><span>SchoolChat</span></h1>
      
      
      </div>

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          
          
             <li class="book-a-table text-center">
          
           <form action="${pageContext.request.contextPath}/ManageGroups" method="POST" id="form" name="groupForm" class="signup-form" >
                  <div class="form-group_2">
                 <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Groups"/>
                 </div>
                            
                 </form>
          
          
          </li>
        
        <%
			
        if ((boolean) session.getAttribute("isProf") == false){
        	
        	%>
            <li class="book-a-table text-center">
            
            <form action="${pageContext.request.contextPath}/CreateGroups" method="POST" id="form" name="groupForm" class="signup-form" >
                   <div class="form-group_2">
                  <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Create"/>
                  </div>
                             
                  </form>
           
           
           </li>
        	<% 
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
        
 if ((boolean) session.getAttribute("isProf")){
        	
        	%>
            <li class="book-a-table text-center">
            
            <form action="${pageContext.request.contextPath}/Search" method="POST" id="form" name="groupForm" class="signup-form" >
                   <div class="form-group_2">
                  <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Search"/>
                  </div>
                             
                  </form>
           
           
           </li>
        	<% 
        	
        	
        	%>
            <li class="book-a-table text-center">
            
            <form action="createStudent.jsp" method="POST" id="form" name="groupForm" class="signup-form" >
                   <div class="form-group_2">
                  <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Create"/>
                  </div>
                             
                  </form>
           
           
           </li>
           
           
           
        	<% 
        	
	
        }


		%>
		
		
		    <li class="book-a-table text-center">
            
            <form action="${pageContext.request.contextPath}/ManageGoToProfile" method="POST" id="form" name="groupForm" class="signup-form" >
                   <div class="form-group_2">
                  <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Profile"/>
                  </div>
                             
                  </form>
           
           
           </li>
		
		
		
		
		

          
          <li class="book-a-table text-center">
          
           <form action="${pageContext.request.contextPath}/ManageLogout" method="POST" id="edit_user_form" name="logoutForm" class="signup-form" >
                  <div class="form-group_2">
                 <input type="submit" id="submit" name="submitLogoutForm" type="button" class="btn btn-primary"  value="Logout"/>
                 </div>
                            
                 </form>
          
          
          </li>
        
          
  
        </ul>
      </nav><!-- .nav-menu -->

    </div>
  </header><!-- End Header -->






	<main id="main"> <section class="inner-page">


  <h1>Manage<span>Groups</span></h1>


	</section> </main>


</body>
</html>