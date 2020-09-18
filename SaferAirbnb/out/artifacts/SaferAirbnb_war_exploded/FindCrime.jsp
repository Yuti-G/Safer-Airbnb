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
    <title>Find Crime</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="findcrime" method="post">
            <h1>Search for Crime(s)</h1>
            <p>Fill in one of the following field to apply:</p>
            <p>
                <label for="incidentNo">Incident Number</label>
                <input id="incidentNo" name="incidentNo" value="${fn:escapeXml(param.incidentNo)}">
            </p>
            <p>
                <label for="district">District Code</label>
                <input id="district" name="district" value="${fn:escapeXml(param.district)}">
            </p>
            <p>
                <label for="offenseCode">OffenseCode</label>
                <input id="offenseCode" name="offenseCode" value="${fn:escapeXml(param.offenseCode)}">
            </p>


            <p>
                <input type="submit">
                <br/><br/><br/>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </form>
        <br/>
        <div id="crimecreate"><a class="btn btn-primary" href="crimecreate" role="button">Create A Crime</a></div>
        <br/>
        <h1>Matching Crime(s)</h1>
        <table class = "table">
            <thead class="thead-light">
                <tr>
                    <th style="text-align:center; min-width: 50px;">Crime ID</th>
                    <th style="text-align:center; min-width: 50px;">Incident Number</th>
                    <th style="text-align:center; min-width: 50px;">Date</th>
                    <th style="text-align:center; min-width: 50px;">Description</th>
                    <th style="text-align:center; min-width: 50px;">Offense Code</th>
                    <th style="text-align:center; min-width: 50px;">Area Number</th>
                    <th style="text-align:center; min-width: 50px;">District Code</th>
                    <th style="text-align:center; min-width: 50px;">Shooting</th>
                    <th style="text-align:center; min-width: 50px;">Update Crime</th>
                    <th style="text-align:center; min-width: 50px;">Delete Crime</th>
                </tr>
            <thead>
            <tbody>
            <c:forEach items="${crimes}" var="crime" >
                <tr>
                    <td><c:out value="${crime.getCrimeKey()}" /></td>
                    <td><c:out value="${crime.getIncidentNumber()}" /></td>
                    <td><c:out value="${crime.getDateTime()}" /></td>
                    <td><c:out value="${crime.getDescription()}" /></td>
                    <td><c:out value="${crime.getOffenseCode().getName()}" /></td>
                    <td><c:out value="${crime.getLocation().getArea().getAreaNo()}" /></td>
                    <td><c:out value="${crime.getLocation().getArea().getDistrict().getDistrictCode()}" /></td>
                    <td><c:out value="${crime.isShooting()}" /></td>
                    <td><a href="crimeupdate?crimeId=<c:out value="${crime.getCrimeKey()}"/>">Update</a></td>
                    <td><a href="crimedelete?crimeId=<c:out value="${crime.getCrimeKey()}"/>">Delete</a></td>
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