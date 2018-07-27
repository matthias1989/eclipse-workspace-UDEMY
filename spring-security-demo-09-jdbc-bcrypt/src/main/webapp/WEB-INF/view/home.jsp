<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html>
	<head>
		<title>luv2code Company Home Page</title>
	</head>
	
	<body>
		<h2>luv2code Company Home Page - Yoohoo!!!</h2>
		<hr>
		<p> Welcome to luv2code company home page! </p>
		<hr>
		<!-- Display user name and role -->
		User: <security:authentication property="principal.username"/> <br> <br>
		Role (s): <security:authentication property="principal.authorities"/> <br> <br>
		
		<security:authorize access="hasRole('MANAGER')">
	
			<!-- Add a link to point to /leaders => only for managers -->
			<hr>
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
				(Only for Manager people)
			</p>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<hr>
			<!-- Add a link to point to /systems => only for admins -->
			<p>
				<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
				(Only for Admin people)
			</p>
		</security:authorize>
		
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
	</body>

</html>