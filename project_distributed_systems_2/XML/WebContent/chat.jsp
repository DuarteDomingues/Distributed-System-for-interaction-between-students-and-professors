<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="chats.ChatHandler"%>
<%@ page import="java.util.HashMap"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data_info.Message"%>
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
<title>Chat</title>


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



	<%
	
	
	
	
	
	String userName = (String) session.getAttribute("username");
	String userInfo = (String)  session.getAttribute("userInfo");
	String userId = (String) session.getAttribute("userId");
	int chatType = (int) session.getAttribute("chatType");
	
	ChatHandler chatHandler = new ChatHandler();
	
	
	
	ServerData.setUsersInChat(chatHandler.getUsersChat());
	
	String uc_id = null;
	String turma_id = null;
	String group_chat_id = null;
	
	if (chatType ==0){
		
		chatHandler.processInputString(userInfo);
		turma_id = chatHandler.getTurma_id();
		
	}
	
	else if (chatType ==1){
		
		chatHandler.processInputUcString(userInfo);
	}
	
	
	else if (chatType == 2){
		
		chatHandler.processInputGroupChatString(userInfo);
		//chatHandler.processGroupString
	}
	
	
	
	
	String nome_turma = chatHandler.getNome_turma();
	
	if (nome_turma == null){
		nome_turma ="";
	}
	
	uc_id = chatHandler.getUc_id();
	String nome_uc = chatHandler.getNome_uc();
	group_chat_id = chatHandler.getGroup_chat_id();
	
	ArrayList<Message> msgs = chatHandler.getMsgs();
	
	


	
	
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


  <div class="container">
<div id="title" style="display: flex;
    flex-direction: row; ">

	<h1 style="color:#ffb03b;"><%=nome_uc %></h1>
	<h2  style="padding-left:20px; font-size:23px'"  ><%=nome_turma %></h2>

</div>
</div>
	&nbsp;
		&nbsp;
<p>

<% 

	for (int i=0; i <msgs.size();i++){
		
		String texto = msgs.get(i).getText();
		String dateStr = msgs.get(i).getDate();
		String user = msgs.get(i).getUserId();
		
		
		
		%>	
		


  <div class="container">

             <div class="card">
	    <div class="card-body">
	        <div class="row">
        	    <div class="col-md-1">
        	        <img src="assets/pinguim.png" class="img img-rounded img-fluid"/>
        	        <p class="text-secondary text-center"><%=dateStr%></p>
        	    </div>
        	    <div class="col-md-15">
        	        <p>
        	            <a class="float-left"><strong><%=user%></strong></a>
                            
                            
                          
                   
        	       </p>
        	       <div class="clearfix"></div>
        	        <p><%=texto%></p>
        	       
        	    </div>
	        </div>
	        
	        
	        	
            
	            </div>
	    </div>
</div>
<br>


<% 

	}

%>





&nbsp;
&nbsp;
&nbsp;

<p>
<br>

	<div class="container"></div>



	<form action="${pageContext.request.contextPath}/ManageSendMessage" method="POST" id="comment_form" name="commentForm"
		class="comment-form">
	
		<div class="d-flex flex-row align-items-start" style="padding-left:64px ">
			<img class="img-circle img-responsive" src="assets/pinguim.png"
				width="40">
			<textarea class="form-control ml-1 textarea" name="commentText" style="max-width:91%; "
				placeholder="Write..."></textarea>
		</div>

		<div class="mt-2 text-right" style="padding-right:63px ">
			<button class="btn btn-primary btn-sm shadow-none"  
				name="submitCommentForm" type="submit">Post</button>
		</div>
		<input type="hidden" value="<%=chatType%>" name="type">
		<input type="hidden" value="<%=uc_id%>" name="uc_id">
		<input type="hidden" value="<%=turma_id%>" name="turma_id">
		<input type="hidden" value="<%=group_chat_id%>" name="group_chat_id">
		
		
	
	</form>


	<div class="container">





<%

ArrayList<String> usersChat = chatHandler.getUsersChat();





%>
 
<div class="dropdown">
 <button class="dropbtn">Users</button>
  <div class="dropdown-content">
  <% 
  for (int i =0; i < usersChat.size();i++){
	  
	  if (usersChat.get(i).equals(chatHandler.getNome_admin())){
	  
	  %>
	  	
	 <a href="#" style="color:#ffb03b;"><%=usersChat.get(i)%></a>
	  	
	  
	  
	  <% 
	  }
	  
	  else{
		  
		  %>
		  	
		  	
		  	
	     <a href="#"><%=usersChat.get(i)%></a>

		  
		  
		  <% 
	  }
	  
  }



 
 
  
  %>

  </div>
</div> 
</div> 

&nbsp;
<% 
 if (userName.equals(chatHandler.getNome_admin()) && !((boolean) session.getAttribute("isProf"))){
		
		%>
		<p>
		<br>
		&nbsp;
	<form  action="${pageContext.request.contextPath}/ManageEditUserChat"    method="POST" id="signup-form" class="signup-form"
				onsubmit="" name="FormRegist">
				<h2 class="form-title">
					Edit <span>Chat</span>
				</h2>

				<input type="hidden" name="id_chat" value="<%=chatHandler.getGroup_chat_id()%>" />
				<br>
		&nbsp;
				<div class="form-group">
					<label> Change chat name: </label> <input type="text"
						class="form-input input" name="chatname" id="chatname"
						placeholder="Chat Name" />

				</div>
				
		
		<div id="list1" class="dropdown-check-list" tabindex="100">
				<span class="anchor">Manage Users</span>
				<ul class="items">
					<input type="hidden" name="titleRecurso" value="titulo" />
					<input type="hidden" name="idRecurso" value="id" />
					
					<%
					
					  for (int i =0; i < usersChat.size();i++){
						  
						  if (!usersChat.get(i).equals(chatHandler.getNome_admin())){
					
					
				
					%>

					<li><input type="checkbox" name=<%=usersChat.get(i)%> value=<%=usersChat.get(i)%> /> <%=usersChat.get(i)%></li>
					<%
					
					}
					  }
					%>
					
					
				
				</ul>
			</div>
			
			&nbsp;
			<p>
			
			
			<input name="adicionar" type="submit"
				value="submit" />
			
			
			
		</form>
		
		<% 
  }




%>



	</section> </main>




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