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

@WebServlet("/findcrime")
public class FindCrime extends HttpServlet {
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

        List<Crime> crimes = new ArrayList<>();
        
        String incidentNo = req.getParameter("incidentNo");
        String districtCode = req.getParameter("district");
        String offenseCodeStr = req.getParameter("offenseCode");
        
        if (incidentNo != null && !incidentNo.trim().isEmpty()) {
        	try {
        		incidentNo = incidentNo.trim();
        		crimes = crimeDao.getCrimesByIncidentNo(incidentNo);
        		messages.put("success", "Displaying results for Incident No = " + incidentNo);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	} catch (NumberFormatException e) {
        		messages.put("success", "Please enter a valid Incident No.");
        	}
        } else if (districtCode != null && !districtCode.trim().isEmpty()) {
        	try {
        		DistrictDao districtDao = DistrictDao.getInstance();
        		District district = districtDao.getDistrictByDistrictCode(districtCode.trim());
        		crimes = crimeDao.getCrimesByDistrictName(district.getDistrictName());
        		messages.put("success", "Displaying results for District " + district.getDistrictName());
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        } else if (offenseCodeStr != null && !offenseCodeStr.trim().isEmpty()) {
        	try {
        		int offenseCode = Integer.parseInt(offenseCodeStr);
        		crimes = crimeDao.getCrimesByOffenseCode(offenseCode);
        		messages.put("success", "Displaying results for Offense Code " + offenseCode);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	} catch (NumberFormatException e) {
        		messages.put("success", "Please enter a valid Offense Code.");
        	}
        } else {
        	messages.put("success", "Please fill in any field.");
        }
        
        req.setAttribute("crimes", crimes);

        req.getRequestDispatcher("/FindCrime.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Crime> crimes = new ArrayList<>();
        
        String incidentNo = req.getParameter("incidentNo");
        String districtCode = req.getParameter("district");
        String offenseCodeStr = req.getParameter("offenseCode");
        
        if (incidentNo != null && !incidentNo.trim().isEmpty()) {
        	try {
        		incidentNo = incidentNo.trim();
        		crimes = crimeDao.getCrimesByIncidentNo(incidentNo);
        		messages.put("success", "Displaying results for Incident No = " + incidentNo);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	} catch (NumberFormatException e) {
        		messages.put("success", "Please enter a valid Incident No.");
        	}
        } else if (districtCode != null && !districtCode.trim().isEmpty()) {
        	try {
        		DistrictDao districtDao = DistrictDao.getInstance();
        		District district = districtDao.getDistrictByDistrictCode(districtCode.trim());
        		crimes = crimeDao.getCrimesByDistrictName(district.getDistrictName());
        		messages.put("success", "Displaying results for District " + district.getDistrictName());
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        } else if (offenseCodeStr != null && !offenseCodeStr.trim().isEmpty()) {
        	// TODO: What is Offense Code???
        	try {
        		int offenseCode = Integer.parseInt(offenseCodeStr);
        		crimes = crimeDao.getCrimesByOffenseCode(offenseCode);
        		messages.put("success", "Displaying results for Offense Code " + offenseCode);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	} catch (NumberFormatException e) {
        		messages.put("success", "Please enter a valid Offense Code.");
        	}
        } else {
        	messages.put("success", "Please fill in any field.");
        }
        
        req.setAttribute("crimes", crimes);

        req.getRequestDispatcher("/FindCrime.jsp").forward(req, resp);
    }
}
