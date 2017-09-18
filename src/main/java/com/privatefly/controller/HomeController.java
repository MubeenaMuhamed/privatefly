package com.privatefly.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.privatefly.model.PrivateFlyModel;
import com.privatefly.service.PrivateflyService;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private PrivateflyService privateflyService;

	@RequestMapping("/")
	public String viewHome(ModelMap map) {

		List<PrivateFlyModel> aircrafts = privateflyService.getAllAircrafts();
		logger.info("Listed all aircrafts");
		map.addAttribute("aircrafts", aircrafts);
		return "index";
	}

	@RequestMapping(value = "/add-aircrafts", method = RequestMethod.POST)
	public String registerPost(@RequestParam("aircraft_name") String aircraft_name,
			@RequestParam("airfield") String airfield, @RequestParam("ICAO_code") String ICAO_code,
			@RequestParam("openedDate") String openedDate, @RequestParam("runway_length") String runway_length) {
		privateflyService.createNewAircraft(aircraft_name, airfield, ICAO_code, openedDate, runway_length);
		logger.info("Created new aircraft");
		return "redirect:/";
	}

	@RequestMapping("/sorted-list")
	public String sortedAircraft(ModelMap map) {
		List<PrivateFlyModel> aircrafts = privateflyService.sortedAircraftList();
		map.addAttribute("aircrafts", aircrafts);
		logger.info("Get all aircrafts in ascending order");
		return "index";
	}

	@RequestMapping(value = "/search-by-aircraft", method = RequestMethod.GET)
	public String searchAircraft(ModelMap map, @RequestParam("airfield") String airfield) {
		map.addAttribute("aircrafts", privateflyService.searchByAirfieldName(airfield));
		logger.info("Search by airfield name");
		return "index";
	}
}
