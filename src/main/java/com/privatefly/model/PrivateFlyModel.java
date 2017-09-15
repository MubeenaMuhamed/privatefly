package com.privatefly.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aircraft")
public class PrivateFlyModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	private String aircraftname;
	@NotNull
	private String airfield;
	@NotNull
	private Date openedDate;
	@NotNull
	private String runway_length;
	@NotNull
	private String ICAO_code;

	public PrivateFlyModel() {
		super();
	}

	public PrivateFlyModel(String aircraftname, String airfield, String ICAO_code, Date openedDate,
			String runway_length) {
		super();
		this.aircraftname = aircraftname;
		this.airfield = airfield;
		this.ICAO_code = ICAO_code;
		this.openedDate = openedDate;
		this.runway_length = runway_length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAircraftname() {
		return aircraftname;
	}

	public void setAircraftname(String aircraft_name) {
		this.aircraftname = aircraft_name;
	}

	public String getAirfield() {
		return airfield;
	}

	public void setAirfield(String airfield) {
		this.airfield = airfield;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public String getRunway_length() {
		return runway_length;
	}

	public void setRunway_length(String runway_length) {
		this.runway_length = runway_length;
	}

	public String getICAO_code() {
		return ICAO_code;
	}

	public void setICAO_code(String iCAO_code) {
		ICAO_code = iCAO_code;
	}

}