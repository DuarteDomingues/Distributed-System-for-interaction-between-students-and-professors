<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="server_data.ServerData"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Groups</title>


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
	
	
	String[] arrInfo = proInfo.splitInfo(userInfo);
	
	String strChats = arrInfo[0];
	String strUcs = arrInfo[1];
	
	proInfo.checkTurmasUcsWithChats(strChats);
	
	
	HashMap<String, String> turmaMap = proInfo.processTurmaInfo(strUcs);
	HashMap<String, String> ucMap = proInfo.processUcInfo(strUcs);

	boolean isProf = (boolean) session.getAttribute("isProf");

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
	
	
	<br>
	<%
	
	
		
	 for (String i : ucMap.keySet()) {
	 		
	 		%>
	<h1><span><%=ucMap.get(i) %></span></h1>

	<% 
	 	
	 	if (proInfo.getActiveUcChats().get(i)) {

	 		%>

	<form action="${pageContext.request.contextPath}/ManageChat"
		method="POST" id="signup-form" name="myform" class="signup-form"
		onsubmit="">
		<div class="form-group_2">
			<input type="submit" name="submit" id="submit" type="button"
				class="btn btn-primary" value="chat" />
		</div>
		<input type="hidden" value="<%=i%>" name="id">
		 <input	type="hidden" value="uc_chat" name="type">
		<p id="invalid" class="invalid" style="font-size: 16px;" />
	</form>


	<%
	 	}
	 	
	 	else{
 			if(isProf){
 				
 				
 				%>
	&nbsp;
	<form method="POST" id="signup-form" name="myform" class="signup-form"
		onsubmit="">
		<div class="form-group_2">
			<input type="submit" name="submit" id="submit" type="button"
				class="btn btn-primary" value="create chat" />
		</div>
		<p id="invalid" class="invalid" style="font-size: 16px;" />
	</form>

<br>


	<% 
 			}
	 	}
	 	
	 
	 	
	 	for (String j : turmaMap.keySet()){
	 		
	 		
	 		String idTurma = j;
	 		
	 		String[] arr = idTurma.split("_",2);
	 		
	 		if (arr[1].equals(i)){
	 			
	 			%>

	<h2><%=turmaMap.get(j) %></h2>

	<% 
	
	String turmaChatId = i+"_"+arr[0];
	 				
	 			
	 			if (proInfo.getActiveTurmaChats().get(j)){
	 				
	 				%>
		&nbsp;
	
	
	<div style="display:inline-flex">
	<form action="${pageContext.request.contextPath}/ManageChat"
		method="POST" id="signup-form" name="myform" class="signup-form"
		onsubmit="">
		<div class="form-group_2">
			<input type="submit" name="submit" id="submit" type="button"
				class="btn btn-primary" value="chat" />
		</div>
		<input type="hidden" value="<%=turmaChatId%>" name="id"> 
		<input type="hidden" value="turma_chat" name="type">
		<p id="invalid" class="invalid" style="font-size: 16px;" />
	</form>
	</div>
	<%
	if (isProf){
	%>
		<div style="display:inline-flex">
	
		<form action="${pageContext.request.contextPath}/ChangeTurmas"
		method="POST" id="signup-form" name="myform" class="signup-form"
		onsubmit="">
		<div class="form-group_2">
			<input type="submit" name="submit" id="ManageTurmas" type="button"
				class="btn btn-primary" value="Manage Class" />
		</div>
		<input type="hidden" value="<%=turmaChatId%>" name="id"> 
		<input type="hidden" value="turma_chat" name="type">
		<p id="invalid" class="invalid" style="font-size: 16px;" />
	</form>
</div>
	
	<%
	}
	%>

	
	
	<%
			
	 			}
	 			
	 			
	 			else{
	 	 			if(isProf){
	 	 				
	 	 				
	 	 				%>

	<form action="${pageContext.request.contextPath}/ManageCreateProfessor"
	
	method="POST" id="signup-form" name="myform" class="signup-form"
		onsubmit="">
		<div class="form-group_2">
			<input type="submit" name="submit" id="submit" type="button"
				class="btn btn-primary" value="create chat" />
			<input type="hidden" value="<%=turmaChatId%>" name="id"> 
			<input type="hidden" value="turma_chat" name="type">
				
		</div>
		<p id="invalid" class="invalid" style="font-size: 16px;" />
	</form>
	
	
	

	
	
	


	<% 
	 	 			}
	 		 	}
	 			
	 			
	 			
	 			
	 			
	 		}
	 		
	 		
	 		
	 		}
	 	

	 	
	 	}

	
	//tenho que guardar os chats na serverData, e depois isso e facilllllllllll
	
	 ArrayList<String> chatIds;
	 ArrayList<String> chatNames;

	if(ServerData.getChatNames()!=null && ServerData.getChatNames().size()>0){
		
		chatIds = ServerData.getChatIds();
		chatNames = ServerData.getChatNames();
		
		%>
		<br>
		<h1><span>Group Chats</span></h1>
		
	
		
		<% 
		
		for ( int i=0; i <chatIds.size();i++){
			
			%>
			
			<h2><%=chatNames.get(i)%></h2>
			
	
		&nbsp;
				<form action="${pageContext.request.contextPath}/ManageChat"
			method="POST" id="signup-form" name="myform" class="signup-form"
			onsubmit="">
			<div class="form-group_2">
			<input type="submit" name="submit" id="submit" type="button"
				class="btn btn-primary" value="chat" />
		</div>
		<input type="hidden" value="<%=chatIds.get(i)%>" name="id"> 
		<input type="hidden" value="group_chat" name="type">
			<p id="invalid" class="invalid" style="font-size: 16px;" />
			</form>
			
			<% 
			
			
			
			
			
			%>
			
			
			<% 
			
			
		}
		
	}
	
	
	

	
   
   %> </section> </main>


</body>
</html>