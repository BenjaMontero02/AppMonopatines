package com.appscootercopy.scooterusemicroservice;

import com.appscootercopy.scooterusemicroservice.controller.ScooterController;
import com.appscootercopy.scooterusemicroservice.domain.Ubication;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.EnableScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.request.ScooterRequestDTO;
import com.appscootercopy.scooterusemicroservice.service.dto.scooter.response.ScooterResponseDTO;
import com.appscootercopy.scooterusemicroservice.service.exception.BadRequestException;
import com.appscootercopy.scooterusemicroservice.service.exception.ConflictExistException;
import com.appscootercopy.scooterusemicroservice.service.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

@SpringBootTest
class ScooterUseMicroserviceApplicationTests {

	private ScooterController controller;

	@Autowired
	public ScooterUseMicroserviceApplicationTests(ScooterController s) {
		controller=s;
	}

	@Test
	void testGetScooterByLicensePlate() {
		String license = "BRA18";
		try {
			ScooterResponseDTO scooter = this.controller.getScooterByLicensePlate(license);
			if(scooter != null) {
				System.out.println("Scooter i looking for: "+scooter);
			}
		}
		catch (NotFoundException e) {
			System.out.println("Scooter not found with: "+license);
		}
	}

	@Test
	void testChangeAvailability() {
		Long id = 17L; //id scooter existing
		String license = "1AAA93"; //license scooter existing
		EnableScooterRequestDTO request = new EnableScooterRequestDTO();
		request.setAvailable(false);
		//i change the availability of the scooter (false)
		this.controller.changeAvailabilityScooter(request, id);
		ScooterResponseDTO scooter = this.controller.getScooterByLicensePlate(license);
		//i check the change of availability
		Assert.assertTrue(scooter.getAvailable() == false, "the scooter should not be available");

	}

	@Test
	void testSaveScooter() {
        ScooterRequestDTO request = new ScooterRequestDTO();
		String license = "BLB23";
        request.setLicensePlate("license");
        request.setAvailable(true);
        Ubication ubication = new Ubication(50.4, 120.7);
        request.setUbication(ubication);
        try {
            this.controller.saveScooter(request);
        } catch (ConflictExistException e) {
            System.out.println("There is already a Scooter entity with licensePlate: "+license);
        } catch (BadRequestException e) {
            System.out.println("Complete all parameters");
        }

    }

}
