<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<html>
<head>
    <title>Find a Host</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="findhost" method="post">
            <h1>Search for a Host by Name</h1>
            <p>
                <label for="hostName">Host Name</label>
                <input id="hostName" name="hostName" value="${fn:escapeXml(param.hostName)}">
            </p>

            <h1>Search for a Host by Host ID</h1>
            <p>
                <label for="hostId">Host ID</label>
                <input id="hostId" name="hostId" value="${fn:escapeXml(param.hostId)}">
            </p>
            <p>
                <input type="submit">
                <br/><br/><br/>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </form>
        <br/>
        <div id="hostCreate"><a class="btn btn-primary" href="hostcreate" role="button">Create Host</a></div>
        <br/>
        <h1>Matching Host</h1>
        <table class = "table">
            <thead class="thead-light">
                <tr>
                    <th style="text-align:center; min-width: 50px;">HostId</th>
                    <th style="text-align:center; min-width: 50px;">HostSince</th>
                    <th style="text-align:center; min-width: 50px;">HostLocation</th>
                    <th style="text-align:center; min-width: 50px;">HostAbout</th>
                    <th style="text-align:center; min-width: 50px;">HostResponseTime</th>
                    <th style="text-align:center; min-width: 50px;">HostResponseRate</th>
                    <th style="text-align:center; min-width: 50px;">HostAcceptanceRate</th>
                    <th style="text-align:center; min-width: 50px;">HostURL</th>
                    <th style="text-align:center; min-width: 50px;">Delete Host</th>
                    <th style="text-align:center; min-width: 50px;">Update Host</th>
                </tr>
            <thead>
            <tbody>
            <c:forEach items="${hosts}" var="host" >
                <tr>
                    <td><c:out value="${host.getHostId()}" /></td>
                    <td><c:out value="${host.getHostSince()}" /></td>
                    <td><c:out value="${host.getHostLocation()}" /></td>
                    <td><c:out value="${host.getHostAbout()}" /></td>
                    <td><c:out value="${host.getHostResponseTime()}" /></td>
                    <td><c:out value="${host.getHostResponseRate()}" /></td>
                    <td><c:out value="${host.getHostAcceptanceRate()}" /></td>
                    <td><c:out value="${host.getHostUrl()}" /></td>
                    <td><a href="hostdelete?hostId=<c:out value="${host.getHostId()}"/>">Delete</a></td>
                    <td><a href="hostupdate?hostId=<c:out value="${host.getHostId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
</body>
<style>
    tr:nth-child(even) {background-color: #f2f2f2;}
    th {
        background-color: #f2f2f2;
    }
</style>
</html>
