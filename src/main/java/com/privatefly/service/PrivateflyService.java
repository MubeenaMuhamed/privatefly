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

import com.privatefly.controller.PrivateflyApiController;
import com.privatefly.model.PrivateFlyDAO;
import com.privatefly.model.PrivateFlyModel;

@Service
public class PrivateflyService {

	private static final Logger logger = Logger.getLogger(PrivateflyApiController.class);
	
	@Autowired
	private PrivateFlyDAO privateFlyDAO;
	
	public List<PrivateFlyModel> getAllAircrafts(){
		return privateFlyDAO.findAll();
	}

	public void createNewAircraft(PrivateFlyModel aircraft){
		privateFlyDAO.save(aircraft);
	}
	public List<PrivateFlyModel> sortedAircraftList(){
		return privateFlyDAO.findAllByOrderByAirfieldAsc();
	}
	public PrivateFlyModel searchByAircraftName(String aircraftname){
		return privateFlyDAO.findByAircraftname(aircraftname);
		
	}
	public boolean exists(PrivateFlyModel aircraft) {
	        return searchByAircraftName(aircraft.getAircraftname()) != null;
	}
	
}