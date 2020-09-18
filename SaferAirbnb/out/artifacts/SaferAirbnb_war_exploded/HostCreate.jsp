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
    <title>Create a Host</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <h1>Create Host</h1>
    <form action="hostcreate" method="post">
        <p>
            <label for="hostId">HostId</label>
            <input id="hostId" name="hostId" value="">
        </p>
        <p>Limit your preferred ID number to 7 digits e.g., 1234567</p>
        <p>
            <label for="hostname">HostName</label>
            <input id="hostname" name="hostname" value="">
        </p>
        <p>
            <label for="hostLocation">HostLocation</label>
            <input id="hostLocation" name="hostLocation" value="">
        </p>
        <p>
            <label for="hostAbout">HostAbout</label>
            <input id="hostAbout" name="hostAbout" value="">
        </p>
        <p>
            <label for="hostResponseTime">HostResponseTime</label>
            <input id="hostResponseTime" name="hostResponseTime" value="">
        </p>
        <p>
            <label for="hostResponseRate">HostResponseRate</label>
            <input id="hostResponseRate" name="hostResponseRate" value="">
        </p>
        <p>
            <label for="hostAcceptanceRate">HostAcceptanceRate</label>
            <input id="hostAcceptanceRate" name="hostAcceptanceRate" value="">
        </p>

        <p>
            <label for="hostUrl">HostUrl</label>
            <input id="hostUrl" name="hostUrl" value="">
        </p>
        <p>
            <label for="hostSince">HostSince (yyyy-mm-dd)</label>
            <input id="hostSince" name="hostSince" value="">
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
