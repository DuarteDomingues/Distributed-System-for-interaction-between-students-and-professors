<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="chats.ChatHandler"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="user.ClientXml"%>
<%@ page import="data_info.Message"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import="server_data.ServerData"%>


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


<style>
.dropdown-check-list {
	display: inline-block;
}

.dropdown-check-list .anchor {
	position: relative;
	cursor: pointer;
	display: inline-block;
	padding: 5px 50px 5px 10px;
	border: 1px solid #ccc;
}

.dropdown-check-list .anchor:after {
	position: absolute;
	content: "";
	border-left: 2px solid black;
	border-top: 2px solid black;
	padding: 5px;
	right: 10px;
	top: 20%;
	-moz-transform: rotate(-135deg);
	-ms-transform: rotate(-135deg);
	-o-transform: rotate(-135deg);
	-webkit-transform: rotate(-135deg);
	transform: rotate(-135deg);
}

.dropdown-check-list .anchor:active:after {
	right: 8px;
	top: 21%;
}

.dropdown-check-list ul.items {
	padding: 2px;
	display: none;
	margin: 0;
	border: 1px solid #ccc;
	border-top: none;
}

.dropdown-check-list ul.items li {
	list-style: none;
}

.dropdown-check-list.visible .anchor {
	color: #0094ff;
}

.dropdown-check-list.visible .items {
	display: block;
}
</style>

<style>
.checked {
	color: orange;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create</title>


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

	ClientXml clientXml = new ClientXml();
	
	String userName = (String) session.getAttribute("username");
	String userInfo = (String)  session.getAttribute("userInfo");
	String userId = (String) session.getAttribute("userId");
	
	HashMap<String,String> users = clientXml.processStudentMessage(userInfo);
	
	ServerData.setUsers(users);

	

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


	
	
	
	



	<main id="main">
	
	 <section class="inner-page">


	<form  action="${pageContext.request.contextPath}/ManageCreateButton"    method="POST" id="signup-form" class="signup-form"
				onsubmit="" name="FormRegist">
				<h2 class="form-title">
					Create <span>Chat</span>
				</h2>
				&nbsp;

				<div class="form-group">
					<label> Chat Name: </label> <input type="text"
						class="form-input input-lg" name="chatname" id="chatname"
						placeholder="Chat Name" />

				</div>
				


			<div id="list1" class="dropdown-check-list" tabindex="100">
				<span class="anchor">Add users to chat group</span>
				<ul class="items">
					<input type="hidden" name="titleRecurso" value="titulo" />
					<input type="hidden" name="idRecurso" value="id" />
					
					<%
					
					Iterator hmIterator = users.entrySet().iterator();

					while (hmIterator.hasNext()) {
						Map.Entry mapElement = (Map.Entry) hmIterator.next();
						String ind = (String) mapElement.getKey();
						
						if (!ind.equals(userId)){
						String username = (String) mapElement.getValue();
					%>

					<li><input type="checkbox" name=<%=ind%> value=<%=ind%> /> <%=username%></li>
					<%
					}
					}
					%>
					
					
				
				</ul>
			</div>
			<br> 
			
			<input name="adicionar" type="submit"
				value="create" />
		</form>
	
	
	
	
	</section> 
	
	</main>


<script>
		var checkList = document.getElementById('list1');
		checkList.getElementsByClassName('anchor')[0].onclick = function(evt) {
			if (checkList.classList.contains('visible'))
				checkList.classList.remove('visible');
			else
				checkList.classList.add('visible');
		}
	</script>


</body>
</html>