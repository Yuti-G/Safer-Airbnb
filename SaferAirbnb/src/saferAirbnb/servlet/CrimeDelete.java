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

@WebServlet("/crimedelete")
public class CrimeDelete extends HttpServlet {
	protected CrimeDao crimeDao;
	
	@Override
	public void init() throws ServletException {
		crimeDao = CrimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete a Crime");
        req.getRequestDispatcher("/CrimeDelete.jsp").forward(req, resp);
    }
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    // Map for storing messages.
	    Map<String, String> messages = new HashMap<String, String>();
	    req.setAttribute("messages", messages);

	    // Retrieve and validate id.
	    String crimeKeyStr = req.getParameter("crimeId");
	    if (crimeKeyStr == null  || crimeKeyStr.trim().isEmpty()) {
	        messages.put("title", "Invalid Crime ID");
	        messages.put("disableSubmit", "false");
	    } else {
	        // Delete the Host.
	        int crimeKey = Integer.parseInt(crimeKeyStr);
	        Crime crime = new Crime(crimeKey);
	        try {
	            crime = crimeDao.delete(crime);

	            // Update the message.
	            if (crime == null) {
	                messages.put("title", "Successfully deleted " + crimeKey);
	                messages.put("disableSubmit", "true");
	            } else {
	                messages.put("title", "Failed to delete " + crimeKey);
	                messages.put("disableSubmit", "false");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
	    }

	    req.getRequestDispatcher("/CrimeDelete.jsp").forward(req, resp);
	}
}
