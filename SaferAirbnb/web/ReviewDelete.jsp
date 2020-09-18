<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete a Review</title>
</head>
<body>
<div class="container theme-showcase" role="main">
  <h1>${messages.title}</h1>
  <form action="reviewdelete" method="post">
    <p>
    <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
        <label for="reviewId">Review ID</label>
        <input id="reviewId" name="reviewId" value="${fn:escapeXml(param.reviewId)}">
    </div>
    </p>
    <p>
	    <span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
		<input type="submit">
		</span>
    </p>
</form>
<br/><br/>
</div>
</body>
</html>