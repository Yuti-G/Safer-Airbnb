package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/crimecreate")
public class CrimeCreate extends HttpServlet {
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
        //Just render the JSP.
        req.getRequestDispatcher("/CrimeCreate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String incidentNo = req.getParameter("incidentNo");
        
        String dateStr = req.getParameter("date");
        String offenseCodeStr = req.getParameter("offenseCode");
        String description = req.getParameter("description");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String latitudeStr = req.getParameter("latitude");
        String longitudeStr = req.getParameter("longitude");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String areaNoStr = req.getParameter("areaNo");
        String shootingStr = req.getParameter("shooting");
        
        if (incidentNo == null || incidentNo.trim().isEmpty()) {
            messages.put("success", "Invalid Incident Number.");
        } else if (areaNoStr == null || areaNoStr.trim().isEmpty()) {
        	messages.put("success", "Invalid Area Number.");
        } else {
        	try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(dateStr);
                double latitude = Double.parseDouble(latitudeStr);
                double longitude = Double.parseDouble(longitudeStr);
                int areaNo = Integer.parseInt(areaNoStr);
                
                int offenseCodeId = Integer.parseInt(offenseCodeStr);
                OffenseCode offenseCode = OffenseCodeDao.getInstance().getOffenseCodeById(offenseCodeId);
                
                Area area = AreaDao.getInstance().getAreaByAreaNo(areaNo);
                Location location = new Location(address1, address2, latitude, longitude, city, state, area);
                location = LocationDao.getInstance().create(location);
                
                boolean shooting = Boolean.getBoolean(shootingStr);
                
                Crime crime = new Crime(incidentNo, new java.sql.Date(date.getTime()), description, offenseCode, location, shooting);
                crime = crimeDao.create(crime);
                messages.put("success", "Successfully created crime, ID = " + crime.getCrimeKey());
        	} catch (NumberFormatException e) {
        		messages.put("success", "Invalid number input, " + e);
        	} catch (ParseException e) {
        		messages.put("success", "Date format error. " + e);
            } catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }

        req.getRequestDispatcher("/CrimeCreate.jsp").forward(req, resp);
    }
}
