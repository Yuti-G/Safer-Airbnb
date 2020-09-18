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
    <title>Find Review</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="findreview" method="post">
            <h1>Search for Review</h1>
            <p>Fill in one of the following field to apply:</p>
            <p>
                <label for="listingId">Listing ID</label>
                <input id="listingId" name="listingId" value="${fn:escapeXml(param.listingId)}">
            </p>
            <p>
                <label for="guestId">Guest ID</label>
                <input id="guestId" name="guestId" value="${fn:escapeXml(param.guestId)}">
                
            </p>


            <p>
                <input type="submit">
                <br/><br/><br/>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>


        </form>
        <br/>
        <div id="reviewcreate"><a class="btn btn-primary" href="reviewcreate" role="button">Create A Review</a></div>
        <br/>
        <h1>Matching Reviews</h1>
        <table class = "table">
            <thead class="thead-light">
                <tr>
                    <th style="text-align:center; min-width: 50px;">ReviewId</th>
                    <th style="text-align:center; min-width: 50px;">Comments</th>
                    <th style="text-align:center; min-width: 50px;">Date</th>
                    <th style="text-align:center; min-width: 50px;">Listing ID</th>
                    <th style="text-align:center; min-width: 50px;">Guest ID</th>
                    <th style="text-align:center; min-width: 50px;">Delete Review</th>
                    <th style="text-align:center; min-width: 50px;">Update Review</th>
                </tr>
            <thead>
            <tbody>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getReviewKey()}" /></td>
                    <td><c:out value="${review.getComments()}" /></td>
                    <td><c:out value="${review.getDate()}" /></td>
                    <td><c:out value="${review.getListing().getListingId()}" /></td>
                    <td><c:out value="${review.getPerson().getName()}" /></td>
                    <!-- TODO: change to Servlets of Review -->
                    <td><a href="reviewdelete?reviewId=<c:out value="${review.getReviewKey()}"/>">Delete</a></td>
                    <td><a href="reviewupdate?reviewId=<c:out value="${review.getReviewKey()}"/>">Update</a></td>
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