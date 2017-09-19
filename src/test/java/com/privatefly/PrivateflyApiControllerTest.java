package com.privatefly;


import com.privatefly.PrivateFlyApplication;
import com.privatefly.controller.PrivateflyApiController;
import com.privatefly.model.PrivateFlyModel;
import com.privatefly.service.PrivateflyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PrivateFlyApplication.class})
public class PrivateflyApiControllerTest {


    private MockMvc mockMvc;

    @Mock
    private PrivateflyService privateflyService;

    @InjectMocks
    private PrivateflyApiController apiController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(apiController)
                .build();
    }

    // =========================================== Get All Aircrafts ==========================================

    @Test
    public void test_getAllAircrafts_success() throws Exception {
    	Date openedDate = new Date();
        List<PrivateFlyModel> aircrafts = Arrays.asList(
                new PrivateFlyModel("AircraftNameOne","AirfieldOne", "ICAO_Code_1", openedDate, "12KM"),
                new PrivateFlyModel("AircraftNameTWO","AirfieldTwo", "ICAO_Code_2", openedDate, "6KM"));

        when(privateflyService.getAllAircrafts()).thenReturn(aircrafts);
        mockMvc.perform(get("/api/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].aircraftname", is("AircraftNameOne")))
                .andExpect(jsonPath("$[0].airfield", is("AirfieldOne")))
                .andExpect(jsonPath("$[0].icao_code", is("ICAO_Code_1")))
                .andExpect(jsonPath("$[0].openedDate", is(openedDate.getTime())))
                .andExpect(jsonPath("$[0].runway_length", is("12KM")))
                .andExpect(jsonPath("$[1].aircraftname", is("AircraftNameTWO")))
                .andExpect(jsonPath("$[1].airfield", is("AirfieldTwo")))
                .andExpect(jsonPath("$[1].icao_code", is("ICAO_Code_2")))
                .andExpect(jsonPath("$[1].openedDate", is(openedDate.getTime())))
                .andExpect(jsonPath("$[1].runway_length", is("6KM")));

        verify(privateflyService, times(1)).getAllAircrafts();
        verifyNoMoreInteractions(privateflyService);
    }

	// =========================================== Get Aircraft By Aircraft Name =========================================

    @Test
    public void test_getAircraft_success() throws Exception {
    	Date openedDate = new Date();
    	PrivateFlyModel aircraft = new PrivateFlyModel("AircraftNameOne","AirfieldOne", "ICAO_Code_1", openedDate, "12KM");

        when(privateflyService.searchByAircraftName("AircraftNameOne")).thenReturn(aircraft);

        mockMvc.perform(get("/api/aircraft/{aircraftname}", "AircraftNameOne"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.aircraftname", is("AircraftNameOne")))
                .andExpect(jsonPath("$.airfield", is("AirfieldOne")))
                .andExpect(jsonPath("$.icao_code", is("ICAO_Code_1")))
                .andExpect(jsonPath("$.openedDate", is(openedDate.getTime())))
                .andExpect(jsonPath("$.runway_length", is("12KM")));

        verify(privateflyService, times(1)).searchByAircraftName("AircraftNameOne");
        verifyNoMoreInteractions(privateflyService);
    }

    // =========================================== Create New Aircraft ========================================

    @Test
    public void test_createNewAircraft_success() throws Exception {
    	Date openedDate = new Date();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	PrivateFlyModel aircraft = new PrivateFlyModel("AircraftNameOne","AirfieldOne", "ICAO_Code_1", openedDate, "12KM");

        when(privateflyService.exists(aircraft)).thenReturn(false);
        doNothing().when(privateflyService).createNewAircraft(aircraft);

        mockMvc.perform(
                post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("aircraft_name","AircraftNameOne")
                        .param("airfield","AirfieldOne")
                        .param("ICAO_code","ICAO_Code_1")
                        .param("openedDate",df.format(openedDate))
                        .param("runway_length","12KM"))
                .andExpect(status().isCreated());
    }

}




