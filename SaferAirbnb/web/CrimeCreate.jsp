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
    <title>Create a Crime</title>
</head>
<body>
<div class="container theme-showcase" role="main">
  <h1>Create a Crime</h1>
    <form action="crimecreate" method="post">
        <p>
            <label for="incidentNo">Incident Number</label>
            <input id="incidentNo" name="incidentNo" value="">
        </p>
        <p>
            <label for="date">Date</label>
            <input id="date" name="date" value="format: yyyy-mm-dd">
        </p>
        <p>
            <label for="offenseCode">Offense Code</label>
            <input id="offenseCode" name="offenseCode" value="">
        </p>
        <p>
            <label for="description">Description</label>
            <input id="description" name="description" value="">
        </p>
        <p>
            <label for="address1">Address1</label>
            <input id="address1" name="address1" value="">
        </p>
        <p>
            <label for="address2">Address2</label>
            <input id="address2" name="address2" value="">
        </p>
        <p>
            <label for="latitude">Latitude</label>
            <input id="latitude" name="latitude" value="">
        </p>
        <p>
            <label for="longitude">Longitude</label>
            <input id="longitude" name="longitude" value="">
        </p>
        <p>
            <label for="city">City</label>
            <input id="city" name="city" value="">
        </p>
        <p>
            <label for="state">State</label>
            <input id="state" name="state" value="">
        </p>
        <p>
            <label for="areaNo">Area Number</label>
            <input id="areaNo" name="areaNo" value="">
        </p>
        <p>
            <label for="shooting">Shooting</label>
            <input id="shooting" name="shooting" value="false">
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