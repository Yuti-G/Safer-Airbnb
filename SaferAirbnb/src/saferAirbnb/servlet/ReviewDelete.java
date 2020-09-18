package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet {
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete a Review");
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
    }
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    // Retrieve and validate id.
	    String reviewIdStr = req.getParameter("reviewId");
	    if (reviewIdStr == null  || reviewIdStr.trim().isEmpty()) {
	        messages.put("title", "Invalid Review ID");
	        messages.put("disableSubmit", "false");
	    } else {
	        // Delete the Host.
	        int reviewId = Integer.parseInt(reviewIdStr);
	        Review review = new Review(reviewId);
	        try {
	            review = reviewDao.delete(review);

	            // Update the message.
	            if (review == null) {
	                messages.put("title", "Successfully deleted " + reviewId);
	                messages.put("disableSubmit", "true");
	            } else {
	                messages.put("title", "Failed to delete " + reviewId);
	                messages.put("disableSubmit", "false");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
	    }

	    req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
	}
}
