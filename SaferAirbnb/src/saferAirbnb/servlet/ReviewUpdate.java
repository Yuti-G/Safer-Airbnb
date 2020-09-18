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


@WebServlet("/reviewupdate")
public class ReviewUpdate extends HttpServlet {
	
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

        // Retrieve host and validate.
        String reviewIdStr = req.getParameter("reviewId");
        if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Review ID.");
        } else {
            try {
                int reviewId = Integer.parseInt(reviewIdStr);
                Review review = reviewDao.getReviewById(reviewId);
                if(review == null) {
                    messages.put("success", "Review does not exist.");
                }
                req.setAttribute("review", review);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve review and validate.
        String reviewIdStr = req.getParameter("reviewId");
        if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Review ID.");
        } else {
            try {
                int reviewId = Integer.parseInt(reviewIdStr);
                Review review = reviewDao.getReviewById(reviewId);
                if(review == null) {
                    messages.put("success", "Review does not exist. No update to perform.");
                } else {
                    String comments = req.getParameter("comments");
                    if (comments == null || comments.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid comment.");
                    } else {
                    	review = reviewDao.updateContent(review, comments);
                        messages.put("success", "Successfully updated content for review, ID:" + reviewId);
                    }
                }
                req.setAttribute("review", review);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }
}
