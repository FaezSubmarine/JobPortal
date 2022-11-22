<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap"%>
<%boolean sameId = (session !=null && request.getAttribute("id") == session.getAttribute("id")); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Job Seeker Home Page</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@500&display=swap" rel="stylesheet">

<link rel="stylesheet" href="CSS/EmployerJobPosting.css">
<link rel="stylesheet" href="CSS/RegisterAsSeeker.css">
<link rel="stylesheet" href="CSS/NavBar.css">
<link rel="stylesheet" href="CSS/Footer.css">

<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

<script src="JS/Notification.js"></script>
<script type="module" src="JS/RegisterToSeeker.js"></script>
<script type="module" src="JS/ConfirmPassword.js"></script>
<script type="module" src="JS/TimerForInputer.js"></script>
<script type="module" src="JS/AccountSettingConfirmPassword.js"></script>
<script type="module" src="JS/CheckPasswordStrength.js"></script>

<script type="text/javascript" src="JS/HideShowOverlay.js"></script>

</head>
<body>
<%HashMap<String,String> seekerAttr = (HashMap<String,String>)request.getAttribute("SeekerPage");%>
	<div class="hide" id="OverlayForPassword">
		<img class="Cross" src="image/cross.png">
		<div class="InfoContainer">	
			<form action="/JobPortal/SeekerChangePassword">
				<label for="oldPassword">old password</label><br>
			    <input id="oldPassword" name="oldPassword" type="password" required><br>
			    
			    <label for="password">new password</label><br>
			    <input id="newPassword" name="password" type="password" required><br>
			    <div id="passwordStrength">
					<p>Password Strength:</p><span id="passwordComment"></span>
					<p id="complaint"></p>
				</div>
			    <label for="conPassword">confirm password</label><br>
			    <input id="newConPassword" name="conPassword" type="password" required>
			    
			    <button id="PasswordSubmitButton" type="submit" value="employer">change password</button>
			</form>
		</div>	
	</div>
	<div class="hide" id="overlay">
		<img class="Cross" src="image/cross.png">
		<div class="InfoContainer">	
			<div id="details">
				<form action="/JobPortal/SeekerUpdateDetail">
					<label for="firstName">First Name </label><br>
					<input id="firstName" name="firstName" type="text" value="<%=seekerAttr.get("firstName")%>" required><br>
					
					<label for="lastName">Last Name</label><br>
					<input id="lastName" name="lastName" type="text" value="<%=seekerAttr.get("lastName")%>" required><br>

					<label for="email">Email </label><br>
					<input id="SeekerEmail" name="email" type="text" value="<%=seekerAttr.get("email")%>" required>
					<h4 id="EmailNotifier"></h4>
					<br>
					<label for="phoneNumber">Phone Number</label><br>
					<input id="phoneNumber" name="phoneNumber" type="text" value=<%=seekerAttr.get("phoneNumber")%> required><br>

					<label for="address">address</label><br>
					<input id="address" name="address" type="text" value="<%=seekerAttr.get("address")%>" required><br>
					<label for="SeekStatus">Choose your status:</label><br>
					<select name="SeekStatus" id="SeekStatus">
						<option value="ActLook" Selected>Actively Looking</option>
						<option value="openOp">Open for opportunity</option>
						<option value="NotLook">Not Looking</option>
					</select><br> 
					<label for="password">password</label> <br>
					<input id="password" name="password" type="password" required><br>
					<h4 id="notConfirmed"></h4>
					
					<input id="submitButton" type="submit" value="Update">
				</form>
			</div>
		</div>
	</div>
	<div class="hide" id="OverlayForAccountSetting">
		<img class="Cross" src="image/cross.png">
		<div class="InfoContainer">	
			<div id="ButtonContainer">
				<div id="HibAcc">Hibenate Account</div>
				<div id="DelAcc">Delete Account</div>
			</div>
			<div id="SettingContainer">
				<div class="hide" id="HibAccSetting">
					<h1>Hibernate Account</h1>
					<h2>What is hibernating?</h2>
					<p>If you need a break for any reason at all, you can always take a break and hibernate.
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
					 ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation 
					 ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
					 irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
					 nulla pariatur. Excepteur sint occaecat cupidatat non proident,
					  sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
					<h2>If you're sure, Enter your passwords below</h2>
					<form action="/JobPortal/HibernateSeeker">
						<label for="password">password</label><br>
			    		<input id="hibPassword" name="password" type="password" required><br>
			    
			    		<label for="conPassword">confirm password</label><br>
			    		<input id="hibConPassword" name="conPassword" type="password" required>
			    
			    		<button id="HibSubmitButton" type="submit" value="employer">hibernate account</button>
					</form>
				</div>
				<div class="hide" id="DelAccSetting">
					<h1>Del Account</h1>
					<h2>What does deleting do exactly?</h2>
					<p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
					 ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation 
					 ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
					 irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
					 nulla pariatur. Excepteur sint occaecat cupidatat non proident,
					  sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
					<h2>If you're sure, Enter your passwords below</h2>
					<form action="/JobPortal/DeleteSeeker">
						<label for="password">password</label><br>
			    		<input id="hibPassword" name="password" type="password" required><br>
			    
			    		<label for="conPassword">confirm password</label><br>
			    		<input id="hibConPassword" name="conPassword" type="password" required>
			    
			    		<button id="HibSubmitButton" type="submit" value="employer">delete account</button>
					</form>
				</div>
			</div>
		</div>
	</div>
    <div id="stickyBar">
    <%if(session !=null && request.getAttribute("id") == session.getAttribute("id")){ %>
        <ul id="navbar">
            <li class="active"><a href="GetSeekerHomePage">Home</a></li>
            <li><a href="SeekerJobList">Apply to a job</a></li>
            <li><a href="SeekerJobAppliedList">Application Status</a></li>
            <li class="RightFloat dropdown">
				<a class="dropbtn"><%=seekerAttr.get("name") %></a>
    			<div class="dropdown-content">
	      			<button id="ChangePassword">Change Password</button>
	      			<button id="AccountSettings">Account Settings</button>
	      			<button onclick="location.href='Logout'">Log out</button>
      			</div>
			</li>
         </ul>
    <%} else if(session !=null && request.getAttribute("id") != session.getAttribute("id")){%>
            <ul id="navbar">
	            <%String hrefString = (session.getAttribute("isSeeker").toString()=="true")?"GetSeekerHomePage":"GetEmployerHomePage"; %>
	            <li><a href=<%=hrefString %>>Home</a></li>
	            <li class="active"><a><%=seekerAttr.get("name")%>'s profile</a></li>
	            <li class="RightFloat"><a  href="Logout">Log Out</a></li>
         	</ul>
    <%}else if(session ==null){ %>
    	<ul id="navbar">
            <li class="RightFloat"><a href="index.jsp">Register now!</a></li>
         </ul>
    <%}
    String helloString = sameId?"Hello ":"Hi, I am ";%>
    </div>
    <%if(session.getAttribute("successNotif")!=null){ %>
	<div id="successfulNotif" class="UpdateNotification">
		<img src="image/tick.png"><p><%=session.getAttribute("successNotif").toString() %></p>
	</div>
	<%session.removeAttribute("successNotif");
	}else if(session.getAttribute("failedNotif")!=null){ %>
	<div id="failedNotif" class="UpdateNotification">
		<img src="image/whitecross.png"><p><%=session.getAttribute("failedNotif").toString() %></p>
	</div>
	<% session.removeAttribute("failedNotif");} %>
    <div id="NotepaperDiv">
    	<% if (session != null && request.getAttribute("id") == session.getAttribute("id")) {%>
		<button id="EditButton">
			<img src="image/pencil.png">
		</button>
		<%}%>
        <h1><% out.print(helloString+seekerAttr.get("name")); %></h1>
       	<p><%=request.getAttribute("seekResult") %></p>
		<p>email: <%=seekerAttr.get("email") %></p>
		<p>phone: <%=seekerAttr.get("phoneNumber") %></p>
		<p>address: <%=seekerAttr.get("address") %></p>
    </div>
</body>
<footer>
<div>
	<h4>Seeker</h4>
		<a href="RegisterAsSeeker.jsp">Seeker Register</a>
		<a href="SeekerLogin.jsp">Seeker Log In</a>
</div>
<div>
	<h4>Employer</h4>
		<a href="RegisterAsEmployer.jsp">Employer Register</a>
		<a href="EmployerLogin.jsp">Employer Log In</a>

</div>
<div>
	<h4>Legal</h4>
	<a>Terms and Condition</a>
</div>
<div>
	<h4>About Us</h4>
	<a>About the developer</a>
</div>
</footer>
</html>