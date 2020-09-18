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
<title>Update a Review</title>
</head>
<body>
<div class="container theme-showcase" role="main">
  <h1>Update a Review</h1>
  <form action="reviewupdate" method="post">
    <p>
        <label for="reviewId">Review ID</label>
        <input id="reviewId" name="reviewId" value="${fn:escapeXml(param.reviewId)}">
    </p>
    <p>
        <label for="comments">New Comment</label>
        <input id="comments" name="comments" value="">
    </p>
    <p>
        <input type="submit">
    </p>
  </form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</div>
</body>
</html>