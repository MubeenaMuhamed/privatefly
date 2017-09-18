package com.privatefly.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privatefly.controller.HomeController;
import com.privatefly.model.PrivateFlyDAO;
import com.privatefly.model.PrivateFlyModel;

@Service
public class PrivateflyService {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private PrivateFlyDAO privateFlyDAO;
	
	public List<PrivateFlyModel> getAllAircrafts(){
		return privateFlyDAO.findAll();
	}

	public void createNewAircraft(String aircraftname, String airfield, String ICAO_code, String openedDate,
		String runwayLength){
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date startDate = df.parse(openedDate);;
			PrivateFlyModel aircraft = new PrivateFlyModel(aircraftname, airfield, ICAO_code, startDate,
					runwayLength);
			privateFlyDAO.save(aircraft);
		} catch (ParseException e) {
			logger.info(e);
		}
	}
	public List<PrivateFlyModel> sortedAircraftList(){
		return privateFlyDAO.findAllByOrderByAirfieldAsc();
	}
	public PrivateFlyModel searchByAirfieldName(String airfield){
		return privateFlyDAO.findByAircraftname(airfield);
		
	}
	
}