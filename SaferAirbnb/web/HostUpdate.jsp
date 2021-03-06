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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update a Host</title>
</head>
<body>
<div class="container theme-showcase" role="main">
<h1>Update Host</h1>
<form action="hostupdate" method="post">
    <p>
        <label for="hostId">HostId</label>
        <input id="hostId" name="hostId" value="${fn:escapeXml(param.hostId)}">
    </p>
    <p>
        <label for="hostname">New HostName</label>
        <input id="hostname" name="hostname" value="">
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