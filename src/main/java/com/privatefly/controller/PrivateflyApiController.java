package com.privatefly.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Iterable<PrivateFlyModel>> viewHome() {

		List<PrivateFlyModel> aircrafts = privateflyService.getAllAircrafts();
		logger.info("Listed all aircrafts");
		return new ResponseEntity<Iterable<PrivateFlyModel>>(aircrafts, HttpStatus.OK);
	}

	@RequestMapping(value = "/aircraft", method = RequestMethod.POST)
	public ResponseEntity<String> registerPost(@RequestParam("aircraft_name") String aircraft_name,
			@RequestParam("airfield") String airfield, @RequestParam("ICAO_code") String ICAO_code,
			@RequestParam("openedDate") String openedDate, @RequestParam("runway_length") String runway_length) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = df.parse(openedDate);;
			PrivateFlyModel aircraft = new PrivateFlyModel(aircraft_name, airfield, ICAO_code, startDate,
					runway_length);
			privateflyService.createNewAircraft(aircraft);
		} catch (ParseException e) {
			logger.info(e);
		}
		logger.info("Created new aircraft");
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping("/sorted-aircrafts")
	public ResponseEntity<Iterable<PrivateFlyModel>> sortedAircraft() {
		List<PrivateFlyModel> aircrafts = privateflyService.sortedAircraftList();
		logger.info("Get all aircrafts in ascending order");
		return new ResponseEntity<Iterable<PrivateFlyModel>>(aircrafts, HttpStatus.OK);
	}

	@RequestMapping(value = "/aircraft/{aircraftname}", method = RequestMethod.GET)
	public ResponseEntity<PrivateFlyModel> searchAircraft(@PathVariable("aircraftname") String aircraftname) {
		logger.info("Search by aircraft name");
		return new ResponseEntity<PrivateFlyModel>(privateflyService.searchByAircraftName(aircraftname), HttpStatus.OK);
	}
}
