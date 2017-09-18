package com.privatefly.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.privatefly.model.PrivateFlyModel;
import com.privatefly.service.PrivateflyService;

@RestController
@RequestMapping("/api")
public class PrivateflyApiController {

	private static final Logger logger = Logger.getLogger(PrivateflyApiController.class);

	@Autowired
	private PrivateflyService privateflyService;

	@RequestMapping("/aircrafts")
	public ResponseEntity<Iterable<PrivateFlyModel>> viewHome(ModelMap map) {

		List<PrivateFlyModel> aircrafts = privateflyService.getAllAircrafts();
		logger.info("Listed all aircrafts");
		return new ResponseEntity<Iterable<PrivateFlyModel>>(aircrafts, HttpStatus.OK);
	}

	@RequestMapping(value = "/aircraft", method = RequestMethod.POST)
	public ResponseEntity<String> registerPost(@RequestParam("aircraft_name") String aircraft_name,
			@RequestParam("airfield") String airfield, @RequestParam("ICAO_code") String ICAO_code,
			@RequestParam("openedDate") String openedDate, @RequestParam("runway_length") String runway_length) {
		privateflyService.createNewAircraft(aircraft_name, airfield, ICAO_code, openedDate, runway_length);
		logger.info("Created new aircraft");
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping("/sorted-aircrafts")
	public ResponseEntity<Iterable<PrivateFlyModel>> sortedAircraft(ModelMap map) {
		List<PrivateFlyModel> aircrafts = privateflyService.sortedAircraftList();
		logger.info("Get all aircrafts in ascending order");
		return new ResponseEntity<Iterable<PrivateFlyModel>>(aircrafts, HttpStatus.OK);
	}

	@RequestMapping(value = "/aircraft/{airfield}", method = RequestMethod.GET)
	public ResponseEntity<PrivateFlyModel> searchAircraft(@PathVariable("airfield") String airfield) {
		logger.info("Search by airfield name");
		return new ResponseEntity<PrivateFlyModel>(privateflyService.searchByAirfieldName(airfield), HttpStatus.OK);
	}
}
