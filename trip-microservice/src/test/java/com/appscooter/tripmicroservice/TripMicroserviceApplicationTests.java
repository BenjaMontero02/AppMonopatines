package com.appscooter.tripmicroservice;

import com.appscooter.tripmicroservice.controllers.TripController;
import com.appscooter.tripmicroservice.services.dtos.tariff.request.TotalProfitsRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.tariff.response.ReportProfitsDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.requests.TripRequestDTO;
import com.appscooter.tripmicroservice.services.dtos.trip.responses.TripResponseDTO;
import com.appscooter.tripmicroservice.services.exception.ConflictExistException;
import com.appscooter.tripmicroservice.services.exception.NotFoundException;
import com.appscooter.tripmicroservice.services.exception.WebClientNotFound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class TripMicroserviceApplicationTests {

	private TripController controller;

	@Autowired
	public TripMicroserviceApplicationTests(TripController tp) {
		controller=tp;
	}

	@Test
	void testGetTripById() {
		try {
			//obtengo trip por id
			TripResponseDTO t = controller.findTripById(24L);
		}
		catch (NotFoundException e) {
			System.out.println(e);
		}
	}

	@Test
	void testSaveTrip() {
		TotalProfitsRequestDTO request = new TotalProfitsRequestDTO();
		request.setFirstMonth(1L); //mes enero
		request.setLastMonth(6L); //mes junio
		request.setYear(2023L);  //anio
		List<ReportProfitsDTO> report = controller.findProfitsByMonthsInYear(request);
		for (ReportProfitsDTO result : report) {
			System.out.println(result);
		}
	}

	@Test
	void testDeleteTripById() {
		Long id = 12L;
		try {
			controller.deleteTrip(12L);
			controller.deleteTrip(20L);
		}
		catch (NotFoundException e) {
			System.out.println(e);
		}
	}

}
