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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {
	protected ReviewDao reviewDao;
	protected ListingDao listingDao;
	protected PersonDao personDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
		listingDao = ListingDao.getInstance();
		personDao = PersonDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String dateStr = req.getParameter("date");
        String comments = req.getParameter("comments");
        String listingIdStr = req.getParameter("listingId");
        String guestIdStr = req.getParameter("guestId");
        
        if (dateStr == null || dateStr.trim().isEmpty()) {
        	messages.put("success", "Invalid date.");
        } else if (comments == null || comments.trim().isEmpty()) {
        	messages.put("success", "Invalid comments.");
        } else if (listingIdStr == null || listingIdStr.trim().isEmpty()) {
        	messages.put("success", "Invalid Listing ID.");
        } else if (guestIdStr == null || guestIdStr.trim().isEmpty()) {
        	messages.put("success", "Invalid Guest ID.");
        } else {
        	

            try {
            	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                date = dateFormat.parse(dateStr);
                int listingId = Integer.parseInt(listingIdStr);
                int guestId = Integer.parseInt(guestIdStr);
                
                Listing listing = listingDao.getListingById(listingId);
                Person guest = personDao.getPersonById(guestId);
                
                Review review = new Review(new java.sql.Date(date.getTime()), comments, listing, guest);
                review = reviewDao.create(review);
                messages.put("success", "Successfully created review, ID = " + review.getReviewKey());
            } catch (ParseException e) {
            	messages.put("success", "Invalid date format.");
                throw new IOException(e);
            } catch (NumberFormatException e) {
            	messages.put("success", e.toString());
                throw new IOException(e);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
    }
}
