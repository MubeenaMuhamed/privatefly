package com.privatefly;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.privatefly.model.PrivateFlyDAO;
import com.privatefly.model.PrivateFlyModel;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrivateFlyApplication.class)
//@ContextConfiguration(locations = {"/PrivateFlyAppicationTests-Context.xml"})
public class PrivateFlyApplicationTests {

	@Autowired
    PrivateFlyDAO privateflyDAO;
	
	@Before
    public void setUp() throws Exception {
		privateflyDAO.deleteAll();
		Date openedTime =new Date();
        PrivateFlyModel aircraft_one = new PrivateFlyModel("Aircraft1", "Airfield1","ICAO1", openedTime,"RunwayLength1");
        PrivateFlyModel aircraft_two = new PrivateFlyModel("Aircraft2", "Airfield2","ICAO2", openedTime,"RunwayLength2");
        PrivateFlyModel aircraft_three = new PrivateFlyModel("Aircraft3", "Airfield3","ICAO3", openedTime,"RunwayLength3");
        privateflyDAO.save(aircraft_one);
        privateflyDAO.save(aircraft_two);
        privateflyDAO.save(aircraft_three);
    }

	@Test
    public void testLoadAllAirecrafts() {
		int count =0;
		List<PrivateFlyModel> aircrafts = (List<PrivateFlyModel>) privateflyDAO.findAll();
		count = aircrafts.size();
		assertEquals("Did not get all Aircrafts", 3, count);
    }
    @Test
    public void testFindAircraft() throws Exception {
    	PrivateFlyModel aircraft = privateflyDAO.findByAircraftname("Aircraft3");
        assertEquals("Found wrong name", "Aircraft3", aircraft.getAircraftname());
    }
    @Test
    public void testCreateAircraft() throws Exception {
	
        // Create a new aircraft
    	Date openedTime = new Date();
    	PrivateFlyModel aircraft4 = new PrivateFlyModel("Aircraft4", "Airfield4","ICAO4", openedTime,"RunwayLength4");
    	privateflyDAO.save(aircraft4);

        PrivateFlyModel aircraft = privateflyDAO.findByAircraftname("Aircraft4");
        assertEquals("Found wrong name", "Aircraft4", aircraft.getAircraftname());

    }
    
    @Test
    public void testSortedAirecraftsByAirefieldname() {
    	privateflyDAO.deleteAll();
		Date openedTime =new Date();
        PrivateFlyModel aircraft_one = new PrivateFlyModel("Aircraft1", "C_Airfield1","ICAO1", openedTime,"RunwayLength1");
        PrivateFlyModel aircraft_two = new PrivateFlyModel("Aircraft2", "A_Airfield2","ICAO2", openedTime,"RunwayLength2");
        PrivateFlyModel aircraft_three = new PrivateFlyModel("Aircraft3", "B_Airfield3","ICAO3", openedTime,"RunwayLength3");
        privateflyDAO.save(aircraft_one);
        privateflyDAO.save(aircraft_two);
        privateflyDAO.save(aircraft_three);
		int count =0;
		List<PrivateFlyModel> aircrafts = (List<PrivateFlyModel>) privateflyDAO.findAllByOrderByAirfieldAsc();
		count = aircrafts.size();
		assertEquals("Did not get all Aircrafts", 3, count);
		assertEquals("Not in sorted list", "A_Airfield2", aircrafts.get(0).getAirfield());
		assertEquals("Not in sorted list", "B_Airfield2", aircrafts.get(1).getAirfield());
		assertEquals("Not in sorted list", "C_Airfield2", aircrafts.get(3).getAirfield());
    }


}
