package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/listingcreate")
public class ListingCreate extends HttpServlet {

    protected ListingDao listingDao;
    protected HostDao hostDao;
    protected NeighborhoodDao neighborhoodDao;
    protected AreaDao areaDao;
    protected LocationDao locationDao;
    protected DistrictDao districtDao;

    @Override
    public void init() throws ServletException {
        listingDao = ListingDao.getInstance();
        hostDao = HostDao.getInstance();
        neighborhoodDao = NeighborhoodDao.getInstance();
        areaDao = AreaDao.getInstance();
        locationDao = LocationDao.getInstance();
        districtDao = DistrictDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/ListingCreate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String listingName = req.getParameter("listingname");
        if (listingName == null || listingName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the Listing.
            int listingId = Integer.parseInt(req.getParameter("listingId"));
            String summary = req.getParameter("listingSummary");
            String url = req.getParameter("listingUrl");
            int hostId = Integer.parseInt(req.getParameter("listingHostId"));
            Boolean availability = Boolean.valueOf(req.getParameter("availability"));
            Listing.PropertyType propertyType = Listing.PropertyType.getEnum(req.getParameter("propertyType"));
            Listing.RoomType roomType = Listing.RoomType.valueOf(req.getParameter("roomType"));
            int accommodates = Integer.parseInt(req.getParameter("accommodates"));
            float bathrooms = Float.parseFloat(req.getParameter("bathrooms"));
            float bedrooms = Float.parseFloat(req.getParameter("bedrooms"));
            int beds = Integer.parseInt(req.getParameter("beds"));
            float price = Float.parseFloat(req.getParameter("price"));
            float deposit = Float.parseFloat(req.getParameter("deposit"));
            int minimumNights = Integer.parseInt(req.getParameter("minimumNights"));
            int maximumNights = Integer.parseInt(req.getParameter("maximumNights"));
            String address1 = req.getParameter("address1");
            String address2 = req.getParameter("address2");
            float latitude = Float.parseFloat(req.getParameter("latitude"));
            float longitude = Float.parseFloat(req.getParameter("longitude"));
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            int areaNo = Integer.parseInt(req.getParameter("areaNo"));
            String neighborhood = req.getParameter("neighborhood");



            try {
                Host host = hostDao.getHostById(hostId);
                Neighborhood neighbor = neighborhoodDao.getNeighborhoodByName(neighborhood);
                Area area = areaDao.getAreaByAreaNo(areaNo);
                Location newLocation = new Location(address1, address2, latitude, longitude, city, state, area);
                Location location = locationDao.create(newLocation);
                // Exercise: parse the input for StatusLevel.
                Listing listing = new Listing(listingId, listingName, summary, url, host, availability, propertyType,
                        roomType, accommodates, bathrooms, bedrooms, beds, price, deposit, minimumNights, maximumNights,
                        location, neighbor);
                listing = listingDao.create(listing);
                messages.put("success", "Successfully created " + listingName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ListingCreate.jsp").forward(req, resp);
    }
}
