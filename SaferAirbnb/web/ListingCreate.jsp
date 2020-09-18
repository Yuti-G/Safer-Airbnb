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
    <title>Create a Listing</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <h1>Create Listing</h1>
    <form action="listingcreate" method="post">
        <p>
            <label for="listingId">Listing Id</label>
            <input id="listingId" name="listingId" value="">
        </p>
        <p>
            <label for="listingname">Listing Name</label>
            <input id="listingname" name="listingname" value="">
        </p>
        <p>
            <label for="listingSummary">Listing Summary</label>
            <input id="listingSummary" name="listingSummary" value="">
        </p>
        <p>
            <label for="listingUrl">Listing Url</label>
            <input id="listingUrl" name="listingUrl" value="">
        </p>
        <p>
            <label for="listingHostId">HostId</label>
            <input id="listingHostId" name="listingHostId" value="">
        </p>
        <p>
            <label for="availability">Availability</label>
<%--            <input id="availability" name="availability" value="">--%>
            <select name="availability" id="availability">
                <option value=""></option>
                <option value="True">Yes</option>
                <option value="False">No</option>
            </select>
        </p>
        <p>
            <label for="propertyType">Property Type</label>
<%--            <input id="propertyType" name="propertyType" value="">--%>
            <select name="propertyType" id="propertyType">
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

        <p>
            <label for="roomType">Room Type</label>
<%--            <input id="roomType" name="roomType" value="">--%>
            <select name="roomType" id="roomType">
                <option value="EntireHome/Apt">EntireHome/Apt</option>
                <option value="PrivateRoom">PrivateRoom</option>
                <option value="SharedRoom">SharedRoom</option>
            </select>
        </p>


        <p>
            <label for="accommodates">Accommodates</label>
            <input id="accommodates" name="accommodates" value="">
        </p>
        <p>
            <label for="bathrooms">Bathrooms</label>
            <input id="bathrooms" name="bathrooms" value="">
        </p>
        <p>
            <label for="bedrooms">Bedrooms</label>
            <input id="bedrooms" name="bedrooms" value="">
        </p>
        <p>
            <label for="beds">Beds</label>
            <input id="beds" name="beds" value="">
        </p>
        <p>
            <label for="price">Price</label>
            <input id="price" name="price" value="">
        </p>
        <p>
            <label for="deposit">Deposit</label>
            <input id="deposit" name="deposit" value="">
        </p>
        <p>
            <label for="minimumNights">Minimum Nights</label>
            <input id="minimumNights" name="minimumNights" value="">
        </p>

        <p>
            <label for="maximumNights">Maximum Nights</label>
            <input id="maximumNights" name="maximumNights" value="">
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
            <label for="neighborhood">Neighborhood</label>
<%--            <input id="neighborhood" name="neighborhood" value="">--%>
            <select name="neighborhood" id="neighborhood">
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
