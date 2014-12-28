<%@ include file="/WEB-INF/templates/taglib_includes.jsp" %>
<div>
	<form name="login" action="login" method="post">
		<label>Name:</label><input type="text" name="name"  placeholder="Username"/>
		<label>Password:</label><input type="password" name="password" placeholder="Password"/>
		<input type="submit" value="Submit" >
	</form>
</div>
