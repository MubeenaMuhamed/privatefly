package com.privatefly.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.privatefly.model.PrivateFlyDAO;
import com.privatefly.model.PrivateFlyModel;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private PrivateFlyDAO privateFlyDAO;

	@RequestMapping("/")
	public String viewHome(ModelMap map) {
		if (logger.isDebugEnabled()) {
			logger.debug("viewHome is executed!");
		}
		Iterable<PrivateFlyModel> aircrafts = privateFlyDAO.findAll();
		map.addAttribute("aircrafts", aircrafts);
		return "index";
	}

	@RequestMapping(value = "/add-aircrafts", method = RequestMethod.POST)
	public String registerPost(@RequestParam("aircraft_name") String aircraft_name,
			@RequestParam("airfield") String airfield, @RequestParam("ICAO_code") String ICAO_code,
			@RequestParam("openedDate") String openedDate, @RequestParam("runway_length") String runway_length) {
		System.out.println(openedDate);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date startDate = df.parse(openedDate);
			System.out.println(startDate);

			PrivateFlyModel aircraft = new PrivateFlyModel(aircraft_name, airfield, ICAO_code, startDate,
					runway_length);
			privateFlyDAO.save(aircraft);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@RequestMapping("/sorted-list")
	public String sortedAircraft(ModelMap map) {
		Iterable<PrivateFlyModel> aircrafts = privateFlyDAO.findAllByOrderByAirfieldAsc();
		map.addAttribute("aircrafts", aircrafts);
		return "index";
	}

	@RequestMapping(value = "/search-by-aircraft", method = RequestMethod.GET)
	public String searchAircraft(ModelMap map, @RequestParam("airfield") String airfield) {
		map.addAttribute("aircrafts", privateFlyDAO.findByAircraftname(airfield));
		return "index";
	}
}
