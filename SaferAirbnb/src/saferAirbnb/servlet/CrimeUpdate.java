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

@WebServlet("/crimeupdate")
public class CrimeUpdate extends HttpServlet {
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

        // Retrieve host and validate.
        String reviewIdStr = req.getParameter("crimeId");
        if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Crime ID.");
        } else {
            try {
                int crimeId = Integer.parseInt(reviewIdStr);
                Crime crime = crimeDao.getCrimeById(crimeId);
                if(crime == null) {
                    messages.put("success", "Crime does not exist.");
                }
                req.setAttribute("crime", crime);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/CrimeUpdate.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve host and validate.
        String reviewIdStr = req.getParameter("crimeId");
        if (reviewIdStr == null || reviewIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Crime ID.");
        } else {
            try {
                int crimeId = Integer.parseInt(reviewIdStr);
                Crime crime = crimeDao.getCrimeById(crimeId);
                if(crime == null) {
                    messages.put("success", "Crime does not exist.");
                }
                String shootingStr = req.getParameter("shooting");
                boolean shooting = Boolean.valueOf(shootingStr);
                crime.setShooting(shooting);
                crime = crimeDao.updateShooting(crime, shooting);
                req.setAttribute("crime", crime);
                messages.put("success", "Crime " + crimeId + " is set " + shooting);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/CrimeUpdate.jsp").forward(req, resp);
    }

}
