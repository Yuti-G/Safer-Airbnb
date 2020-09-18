package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findreview")
public class FindReview extends HttpServlet {
    protected ReviewDao reviewDao;
    
    @Override
    public void init() throws ServletException {
        reviewDao = ReviewDao.getInstance();
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> reviews = new ArrayList<>();
        
        String listingIdStr = req.getParameter("listingId");
        String guestIdStr = req.getParameter("guestId");
        
        if (listingIdStr != null && !listingIdStr.trim().isEmpty()) {
            try {
                int listingId = Integer.parseInt(listingIdStr);
                reviews = reviewDao.getReviewsByListingId(listingId);
                messages.put("success", "Displaying results for Listing ID = " + listingId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            } catch (NumberFormatException e) {
                messages.put("success", "Please enter a valid Listing ID.");
            }
        } else if (guestIdStr != null && !guestIdStr.trim().isEmpty()) {
            try {
                int guestId = Integer.parseInt(guestIdStr);
                reviews = reviewDao.getReviewsByGuestId(guestId);
                messages.put("success", "Displaying results for Guest ID = " + guestId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            } catch (NumberFormatException e) {
                messages.put("success", "Please enter a valid Guest ID.");
            }
        } else {
            messages.put("success", "Please fill in any field.");
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/FindReview.jsp").forward(req, resp);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> reviews = new ArrayList<>();
        
        String listingIdStr = req.getParameter("listingId");
        String guestIdStr = req.getParameter("guestId");
        
        if (listingIdStr != null && !listingIdStr.trim().isEmpty()) {
            try {
                int listingId = Integer.parseInt(listingIdStr);
                reviews = reviewDao.getReviewsByListingId(listingId);
                messages.put("success", "Displaying results for Listing ID = " + listingId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            } catch (NumberFormatException e) {
                messages.put("success", "Please enter a valid Listing ID.");
            }
        } else if (guestIdStr != null && !guestIdStr.trim().isEmpty()) {
            try {
                int guestId = Integer.parseInt(guestIdStr);
                reviews = reviewDao.getReviewsByGuestId(guestId);
                messages.put("success", "Displaying results for Guest ID = " + guestId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            } catch (NumberFormatException e) {
                messages.put("success", "Please enter a valid Guest ID.");
            }
        } else {
            messages.put("success", "Please fill in any field.");
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/FindReview.jsp").forward(req, resp);
    }
}
