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
    <title>Find a person</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="findperson" method="post">
            <h1>Search for People by Name</h1>
            <p>
                <label for="name">Name</label>
                <input id="name" name="name" value="${fn:escapeXml(param.name)}">
            </p>
            <p>
                <input type="submit">
                <br/><br/><br/>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </form>
        <br/>
        <div id="personCreate"><a class="btn btn-primary" href="personcreate" role="button">Create A Person</a></div>
        <br/>
        <h1>Matching People</h1>
        <table class = "table">
            <thead class="thead-light">
                <tr>
                    <th style="text-align:center; min-width: 50px;">PersonId</th>
                    <th style="text-align:center; min-width: 50px;">PersonName</th>
                    <th style="text-align:center; min-width: 50px;">Delete Person</th>
                    <th style="text-align:center; min-width: 50px;">Update Person</th>
                </tr>
            <thead>
            <tbody>

            <c:forEach items="${people}" var="person" >
                <tr>
                    <td><c:out value="${person.getPersonId()}" /></td>
                    <td><c:out value="${person.getName()}" /></td>
                    <td><a href="persondelete?personId=<c:out value="${person.getPersonId()}"/>">Delete</a></td>
                    <td><a href="personupdate?personId=<c:out value="${person.getPersonId()}"/>">Update</a></td>
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