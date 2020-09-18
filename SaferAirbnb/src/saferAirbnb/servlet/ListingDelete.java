package saferAirbnb.servlet;

import saferAirbnb.dal.ListingDao;
import saferAirbnb.dal.PersonDao;
import saferAirbnb.model.Listing;
import saferAirbnb.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/listingdelete")
public class ListingDelete extends HttpServlet {

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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Listing");
        req.getRequestDispatcher("/ListingDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        String listingId = req.getParameter("listingId");
        if (listingId == null  || listingId.trim().isEmpty()) {
            messages.put("title", "Invalid ListingId");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the Listing.
            int listingIdInt = Integer.parseInt(listingId);
            Listing listing = new Listing(listingIdInt);
            try {
                listing = listingDao.delete(listing);

                // Update the message.
                if (listing == null) {
                    messages.put("title", "Successfully deleted " + listingIdInt);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + listingIdInt);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ListingDelete.jsp").forward(req, resp);
    }
}

