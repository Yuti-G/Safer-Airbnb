package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listingupdate")
public class ListingUpdate extends HttpServlet {

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

        // Retrieve listing and validate.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ListingId.");
        } else {
            try {
                int listingIdInt = Integer.parseInt(listingId);
                Listing listing = listingDao.getListingById(listingIdInt);
                if(listing == null) {
                    messages.put("success", "Listing does not exist.");
                }
                req.setAttribute("listing", listing);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/ListingUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve listing and validate.

        // Retrieve listing and validate.
        String listingId = req.getParameter("listingId");
        if (listingId == null || listingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ListingId.");
        } else {
            try {
                int listingIdInt = Integer.parseInt(listingId);
                Listing listing = listingDao.getListingById(listingIdInt);
                if(listing == null) {
                    messages.put("success", "Listing does not exist. No update to perform.");
                } else {
                    String newAvailabilityStr = req.getParameter("newAvailability");
                    if (newAvailabilityStr == null || newAvailabilityStr.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid name.");
                    } else {
                        Boolean newAvailability = Boolean.valueOf(newAvailabilityStr);
                        listing = listingDao.updateAvailability(listing, newAvailability);
                        messages.put("success", "Successfully updated availability for listing, ID:" + listingIdInt);
                    }
                }
                req.setAttribute("listing", listing);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ListingUpdate.jsp").forward(req, resp);
    }
    }
