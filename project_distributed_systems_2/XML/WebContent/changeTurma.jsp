<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="user.ProcessUserInfo"%>
<%@ page import="server_data.ServerData"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="groups.ProcessChangeTurma"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Turma</title>


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















<body>

	
	<%
	
	boolean isProf = (boolean)  session.getAttribute("isProf");
	
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

	
          
 
        
          
  
        </ul>
      </nav><!-- .nav-menu -->

    </div>
  </header><!-- End Header -->

	<%
	
	String resp = (String) session.getAttribute("userInfo");
	ProcessChangeTurma pro = new ProcessChangeTurma();
	pro.processInput(resp);
	
	ArrayList<String> members = pro.getMembers();
	HashMap<String,String> students = pro.getStudents();
	
	ServerData.setDataMembers(members);
	ServerData.setDataStudents(students);
	
	
	
	
	
	
	
	%>


	<main id="main"> 
	
	<section class="inner-page"> 
	
	
	
	
		<form  action="${pageContext.request.contextPath}/ManageChangeClass"    method="POST" id="signup-form" class="signup-form"
				onsubmit="" name="FormRegist">
				<h2 class="form-title">
					Edit <span>Class</span>
				</h2>
				
							&nbsp;
				
				
				
		
		<div id="list1" class="dropdown-check-list" tabindex="100">
				<span class="anchor">Remove Users</span>
				<ul class="items">
					<input type="hidden" name="titleRecurso" value="titulo" />
					<input type="hidden" name="idRecurso" value="id" />
					
					<%
					
					 for (String i : students.keySet()) {
						  
						 if (members.contains(i)){
					
					
				
					%>
					<li><input type="checkbox" name=<%="r"+i%> value=<%="r"+i%>  /> <%=students.get(i)%></li>
					<%
					
					
					
					}
					  }
					%>
					
					
				
				</ul>
			</div>
			
							&nbsp;
			<p>
			
				<div id="list2" class="dropdown-check-list" tabindex="100">
				<span class="anchor">Add Users</span>
				<ul class="items">
					<input type="hidden" name="titleRecurso" value="titulo" />
					<input type="hidden" name="idRecurso" value="id" />
					
					<%
					
					 for (String i : students.keySet()) {
						  
						if (members.contains(i)==false){
					
					
				
					%>
					<li><input type="checkbox" name=<%="a"+i%> value=<%="a"+i%> /> <%=students.get(i)%></li>
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
		
		var checkList2 = document.getElementById('list2');
		checkList2.getElementsByClassName('anchor')[0].onclick = function(evt) {
			if (checkList2.classList.contains('visible'))
				checkList2.classList.remove('visible');
			else
				checkList2.classList.add('visible');
		}
	</script>







</body>
</html>