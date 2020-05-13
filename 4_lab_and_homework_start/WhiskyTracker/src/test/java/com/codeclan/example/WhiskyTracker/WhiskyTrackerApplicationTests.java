package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskiesForAYear() {
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(1991);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesByRegion() {
		List<Distillery> foundDistilleries = distilleryRepository.findByRegion("Speyside");
		assertEquals(3, foundDistilleries.size());
	}

	@Test
	public void canGetWhiskeyFromDistilleryByAge() {
		Distillery distillery1 = new Distillery("Some", "Thing");
		distilleryRepository.save(distillery1);

		Whisky whisky1 = new Whisky("Boi", 15, 2018, distillery1);
		whiskyRepository.save(whisky1);

		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryAndAge(distillery1, 15);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canGetWhiskeyByRegion() {
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion("Speyside");
		assertEquals(3, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesWith12YearOldWhiskies() {
		List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesAge(12);
		assertEquals(6, foundDistilleries.size());
	}

}
