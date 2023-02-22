<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="server_data.ServerData"%>
<%@ page import="java.util.ArrayList"%>

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




<%

	ProcessUserInfo proInfo = new ProcessUserInfo();

	String userName = (String) session.getAttribute("username");
	String userInfo = (String)  session.getAttribute("userInfo");
	
	
	
	session.removeAttribute(userInfo);
	
	proInfo.processUserInfoMessage(userInfo);
	
	String[] arrInfo = new String[10];
	
	arrInfo = proInfo.getArrInfo();
	
	String type = arrInfo[7];
	
	if (type.equals("prof")){
		
		session.setAttribute("isProf",true );
	}
	
	else{
		
		session.setAttribute("isProf",false );

	}
		
	session.setAttribute("userId",	proInfo.getUserId() );
	
	ServerData.updateUserInfo(arrInfo);
	
	String password = proInfo.getPassword();
	
	ServerData.setPassword(password);
	
	ArrayList<String> chatIds = proInfo.getChatIds();
	ArrayList<String> chatNames = proInfo.getChatNames();
	
	ServerData.setChatIds(chatIds);
	ServerData.setChatNames(chatNames);



%>

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




  <main id="main">

       <section class="inner-page">

           
           
<!------ Include the above in your HEAD tag ---------->

<div class="container">
	<div class="row">
        
       <div class="col-md-10 ">

<div class="panel panel-default">
  <div class="panel-heading">  <h1 ><?= $userName   ?></h1></div>
   <div class="panel-body">
       
    <div class="box box-info">
        
            <div class="box-body">
                     <div class="col-sm-3">
                         <div  align="center"> <img alt="User Pic" src="assets/pinguim.png" id="profile-image1" class="img-circle img-responsive"> 
                
             

                <!--Upload Image Js And Css-->
           
 
                     </div>
              
              <br>
    
              <!-- /input-group -->
            </div>
           
            <div class="clearfix"></div>
            <hr style="margin:5px 0 5px 0;">
    
              
     

<div class="col-sm-5 col-xs-6 tital " ><h3> Name:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[0]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Surname:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[1]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Address:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[2]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Nationality:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[3]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Number:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[4]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Email:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[5]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>


<div class="col-sm-5 col-xs-6 tital " ><h3> Telephone:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[6]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>


<div class="col-sm-5 col-xs-6 tital " ><h3> Type:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[7]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Language:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[8]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>

<div class="col-sm-5 col-xs-6 tital " ><h3> Gender:<h3> </div><div class="col-sm-7 col-xs-6 "><h4><%=arrInfo[9]%></h4></div>
     <div class="clearfix"></div>
<div class="bot-border"></div>




	<!-- EDIT BUTTON !-->

	<div class="col-sm-3 col-xs-6 tital " >
    <form    action="EditUser.jsp"     method="POST" id="edit_user_form" name="editForm" class="signup-form">
<div class="form-group_2">
       <input type="submit" id="submit" type="button" class="btn btn-primary"  value="Edit"/>
        </div>
                            
     </form>
</div>

   </div>
          <!-- /.box -->

        </div>
	




       
            
    </div> 
    </div>
</div>  
</section>
</main>


</body>
</html>