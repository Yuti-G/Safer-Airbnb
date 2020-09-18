package saferAirbnb.servlet;

import saferAirbnb.dal.ListingDao;
import saferAirbnb.model.Listing;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * FindUsers is the primary entry point into the application.
 *
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 *
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at locallisting.
 * 4. Point your browser to http://locallisting:8080/SaferAirbnb/findlisting.
 */
@WebServlet("/findlisting")
public class FindListing extends HttpServlet {

    protected ListingDao listingDao;

    @Override
    public void init() throws ServletException {
        listingDao = ListingDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Listing> listings = new ArrayList<Listing>();
        int i = 0;
        String[] params = new String[] {req.getParameter("neighborhoodName"), req.getParameter("propertyType"), req.getParameter("roomType"), req.getParameter("listingId")};
        while (i < 4 && (params[i] == null || params[i].trim().isEmpty())) {
            i++;
        }
        // Retrieve and validate name.
        // name is retrieved from the URL query string.
        try {
            if (i == 0) {
                listings = listingDao.getListingsByNeighborhood(params[i]);
            } else if (i == 1) {
                listings = listingDao.getListingsByPropertyType(Listing.PropertyType.valueOf(params[i]));
            } else if (i == 2) {
                listings = listingDao.getListingsByRoomType(Listing.RoomType.valueOf(params[i]));
            } else if (i == 3) {
                listings = listingDao.getListingsById(Integer.parseInt(params[i]));
            } else {
                messages.put("success", "Please enter a valid search term.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        } catch (IllegalArgumentException e) {
            messages.put("success", "Please enter a valid search term.");
        }

        if (!messages.containsKey("success")) {
            messages.put("success", "Displaying results for " + params[i]);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previous", params[i]);
        }
        req.setAttribute("listing", listings);

        req.getRequestDispatcher("/FindListing.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Listing> listings = new ArrayList<Listing>();
        int i = 0;
        String[] params = new String[] {req.getParameter("neighborhoodName"), req.getParameter("propertyType"), req.getParameter("roomType"), req.getParameter("listingId")};
        while (i < 4 && (params[i] == null || params[i].trim().isEmpty())) {
            i++;
        }
        // Retrieve and validate name.
        // name is retrieved from the URL query string.
        try {
            if (i == 0) {
                listings = listingDao.getListingsByNeighborhood(params[i]);
            } else if (i == 1) {
                listings = listingDao.getListingsByPropertyType(Listing.PropertyType.valueOf(params[i]));
            } else if (i == 2) {
                listings = listingDao.getListingsByRoomType(Listing.RoomType.valueOf(params[i]));
            } else if (i == 3) {
                listings = listingDao.getListingsById(Integer.parseInt(params[i]));
            } else {
                messages.put("success", "Please enter a valid search term.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        } catch (IllegalArgumentException e) {
            messages.put("success", "Please enter a valid search term.");
        }

        if (!messages.containsKey("success")) {
            messages.put("success", "Displaying results for " + params[i]);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previous", params[i]);
        }
        req.setAttribute("listings", listings);

        req.getRequestDispatcher("/FindListing.jsp").forward(req, resp);
    }
}
