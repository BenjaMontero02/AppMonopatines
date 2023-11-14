package com.appscootercopy.scooterusemicroservice.service.loadData;

import com.appscootercopy.scooterusemicroservice.domain.*;
import com.appscootercopy.scooterusemicroservice.repository.*;
import com.appscootercopy.scooterusemicroservice.service.dto.ubication.request.UbicationRequestDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

@Component
public class CsvReader {
    private ScooterRepository scooterRepository;
    private TripRepository tripRepository;
    private UbicationRepository ubicationRepository;
    private ScooterStopRepository scooterStopRepository;
    private ScooterTripRepository scooterTripRepository;
    private static final String userDir =
            System.getProperty("user.dir") + "/src/main/java/com/appscootercopy/scooterusemicroservice/service/loadData/";

    @Autowired
    public CsvReader(ScooterRepository sr, TripRepository tr, UbicationRepository ur,
                     ScooterStopRepository ssr, ScooterTripRepository str) throws IOException, SQLException {
        this.scooterRepository = sr;
        this.tripRepository = tr;
        this.ubicationRepository = ur;
        this.scooterStopRepository = ssr;
        this.scooterTripRepository = str;
    }

    public void load() throws SQLException, IOException {
        this.loadUbication();
        this.loadScooterStop();
        this.loadScooter();
        this.loadTrip();
        this.loadScooterTrip();
    }

    private void loadUbication() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "ubication.csv"));
        for (CSVRecord row : parser) {
            Double x = Double.valueOf(row.get("x"));
            Double y = Double.valueOf(row.get("y"));
            Ubication ubication = new Ubication(x,y);
            ubicationRepository.save(ubication);
        }
    }

    private void loadScooterStop() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "scooter_stop.csv"));
        for (CSVRecord row : parser) {
            Long ubicationId = Long.valueOf(row.get("ubication_id"));
            //isn't correct...
            Ubication ubicationExisting = ubicationRepository.findById(ubicationId).get();
            ScooterStop scooterStop = new ScooterStop(ubicationExisting);
            scooterStopRepository.save(scooterStop);
        }
    }

    private void loadScooter() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "scooter.csv"));
        for (CSVRecord row : parser) {
            Long licensePlate = Long.valueOf(row.get("license_plate"));
            Boolean available = Boolean.valueOf(row.get("available"));
            Long ubicationId = Long.valueOf(row.get("ubication_id"));
            //isn't correct...
            Ubication ubicationExisting = ubicationRepository.findById(ubicationId).get();
            Scooter scooter = new Scooter(licensePlate, available, ubicationExisting);
            scooterRepository.save(scooter);
        }
    }

    private void loadTrip() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "trip.csv"));
        for (CSVRecord row : parser) {
            Long id = Long.valueOf(row.get("id"));
            Timestamp initTime = Timestamp.valueOf(row.get("init_time"));
            Timestamp endTime = Timestamp.valueOf(row.get("end_time"));
            Double kms = Double.valueOf(row.get("kms"));
            Boolean ended = Boolean.valueOf(row.get("ended"));
            Trip trip = new Trip(id, initTime, endTime, kms, ended);
            tripRepository.save(trip);
        }
    }

    private void loadScooterTrip() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "scooter_trip.csv"));
        for (CSVRecord row : parser) {
            Long id_scooter = Long.valueOf(row.get("id_scooter"));
            Long id_trip = Long.valueOf(row.get("id_trip"));

            Scooter scooter = scooterRepository.findById(id_scooter).get();
            Trip trip = tripRepository.findById(id_trip).get();

            ScooterTripId pk = new ScooterTripId(scooter, trip);
            ScooterTrip scooterTrip = new ScooterTrip(pk);
            scooterTripRepository.save(scooterTrip);
        }
    }

}
