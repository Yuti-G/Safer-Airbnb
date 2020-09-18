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
    <title>Find a Listing</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
        <form action="findlisting" method="post">
            <h1>Search for a Listing by Neighborhood Name</h1>
            <p>
                <label for="neighborhoodName">Neighborhood</label>
<%--                <input id="neighborhoodName" name="neighborhoodName" value="${fn:escapeXml(param.neighborhoodName)}">--%>
                <select name="neighborhoodName" id="neighborhoodName" class="selectpicker">
                    <option value=""></option>
                    <option value="Allston">Allston</option>
                    <option value="Brighton">Brighton</option>
                    <option value="Allston-Brighton">Allston-Brighton</option>
                    <option value="Back Bay">Back Bay</option>
                    <option value="Beacon Hill">Beacon Hill</option>
                    <option value="Charlestown">Charlestown</option>
                    <option value="Chestnut Hill">Chestnut Hill</option>
                    <option value="Chinatown">Chinatown</option>
                    <option value="Downtown">Downtown</option>
                    <option value="Downtown Crossing">Downtown Crossing</option>
                    <option value="East Boston">East Boston</option>
                    <option value="Fenway/Kenmore">Fenway/Kenmore</option>
                    <option value="Financial District">Financial District</option>
                    <option value="Government Center">Government Center</option>
                    <option value="Hyde Park">Hyde Park</option>
                    <option value="Jamaica Plain">Jamaica Plain</option>
                    <option value="Leather District">Leather District</option>
                    <option value="Mattapan">Mattapan</option>
                    <option value="Mission Hill">Mission Hill</option>
                    <option value="North End">North End</option>
                    <option value="Roslindale">Roslindale</option>
                    <option value="Roxbury">Roxbury</option>
                    <option value="South Boston">South Boston</option>
                    <option value="South End">South End</option>
                    <option value="Theater District">Theater District</option>
                    <option value="West End">West End</option>
                    <option value="West Roxbury">West Roxbury</option>
                </select>
            </p>

            <h1>Search for a Listing by Property Type</h1>
            <p>
                <label for="propertyType">Property Type</label>
<%--                <input id="propertyType" name="propertyType" value="${fn:escapeXml(param.propertyType)}">--%>
                <select name="propertyType" id="propertyType" class="selectpicker">
                    <option value=""></option>
                    <option value="Villa">Villa</option>
                    <option value="House">House</option>
                    <option value="Apartment">Apartment</option>
                    <option value="Condominium">Condominium</option>
                    <option value="Townhouse">Townhouse</option>
                    <option value="Bed&Breakfast">Bed&Breakfast</option>
                    <option value="Loft">Loft</option>
                    <option value="Boat">Boat</option>
                    <option value="Guesthouse">Guesthouse</option>
                    <option value="Dorm">Dorm</option>
                    <option value="Camper/RV">Camper/RV</option>
                    <option value="EntireFloor">EntireFloor</option>
                    <option value="Other">Other</option>
                </select>
            </p>

            <h1>Search for a Listing by Room Type</h1>
            <p>
                <label for="roomType">Room Type</label>
<%--                <input id="roomType" name="roomType" value="${fn:escapeXml(param.roomType)}">--%>
                <select name="roomType" id="roomType" class="selectpicker">
                    <option value=""></option>
                    <option value="EntireHome/Apt">EntireHome/Apt</option>
                    <option value="PrivateRoom">PrivateRoom</option>
                    <option value="SharedRoom">SharedRoom</option>
                </select>
            </p>

            <h1>Search for a Listing by Listing ID</h1>
            <p>
                <label for="listingId">Listing ID</label>
                <input id="listingId" name="listingId" value="${fn:escapeXml(param.listingId)}">

            </p>

            <p>
                <input type="submit">
                <br/><br/><br/>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </form>
        <br/>
        <div id="listingCreate"><a class="btn btn-primary" href="listingcreate" role="button">Create Listing</a></div>
        <br/>
        <h1>Matching Listing</h1>
        <table class="table">
            <thead class="thead-light">
                <tr>
                    <th style="text-align:center; min-width: 50px;">ListingId</th>
                    <th style="text-align:center; min-width: 50px;">Listing Name</th>
                    <th style="text-align:center; min-width: 50px;">Summary</th>
                    <th style="text-align:center; min-width: 50px;">Url</th>
                    <th style="text-align:center; min-width: 50px;">Host</th>
                    <th style="text-align:center; min-width: 50px;">Availability</th>
                    <th style="text-align:center; min-width: 50px;">Property Type</th>
                    <th style="text-align:center; min-width: 50px;">Room Type</th>
                    <th style="text-align:center; min-width: 50px;">Accommodates</th>
                    <th style="text-align:center; min-width: 50px;">Bathrooms</th>
                    <th style="text-align:center; min-width: 50px;">Bedrooms</th>
                    <th style="text-align:center; min-width: 50px;">Beds</th>
                    <th style="text-align:center; min-width: 50px;">Price</th>
                    <th style="text-align:center; min-width: 50px;">Deposit</th>
                    <th style="text-align:center; min-width: 50px;">Minimum Night</th>
                    <th style="text-align:center; min-width: 50px;">Maximum Night</th>
                    <th style="text-align:center; min-width: 50px;">Location</th>
                    <th style="text-align:center; min-width: 50px;">Neighborhood</th>
                    <th style="text-align:center; min-width: 50px;">Delete</th>
                    <th style="text-align:center; min-width: 50px;">Update</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listings}" var="listing" >
                <tr>
                    <td><c:out value="${listing.getListingId()}" /></td>
                    <td><c:out value="${listing.getName()}" /></td>
                    <td><c:out value="${listing.getSummary()}" /></td>
                    <td><c:out value="${listing.getUrl()}" /></td>
                    <td><c:out value="${listing.getHost().displayInfo()}" /></td>
                    <td><c:out value="${listing.availabilityStr()}" /></td>
                    <td><c:out value="${listing.getPropertyType()}" /></td>
                    <td><c:out value="${listing.getRoomType()}" /></td>
                    <td><c:out value="${listing.getAccommodates()}" /></td>
                    <td><c:out value="${listing.getBathrooms()}" /></td>
                    <td><c:out value="${listing.getBedrooms()}" /></td>
                    <td><c:out value="${listing.getBeds()}" /></td>
                    <td>$<c:out value="${listing.getPrices()}" /></td>
                    <td>$<c:out value="${listing.getSecurityDeposit()}" /></td>
                    <td><c:out value="${listing.getMinimumNights()}" /></td>
                    <td><c:out value="${listing.getMaximumNights()}" /></td>
                    <td><c:out value="${listing.getLocation().displayInfo()}" /></td>
                    <td><c:out value="${listing.getNeighborhood().displayInfo()}" /></td>

                    <td><a href="listingdelete?listingId=<c:out value="${listing.getListingId()}"/>">Delete</a></td>
                    <td><a href="listingupdate?listingId=<c:out value="${listing.getListingId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
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
