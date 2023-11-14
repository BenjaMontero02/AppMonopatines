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
import java.time.LocalDateTime;

@Component
public class CsvReader {
    private ScooterRepository scooterRepository;
    private TripRepository tripRepository;
    private ScooterStopRepository scooterStopRepository;
    private ScooterTripRepository scooterTripRepository;
    private TariffRepository tariffRepository;
    private GeneralPriceRepository pricesRepository;
    private static final String userDir =
            System.getProperty("user.dir") + "/src/main/java/com/appscootercopy/scooterusemicroservice/service/loadData/";

    @Autowired
    public CsvReader(ScooterRepository sr, TripRepository tr, ScooterStopRepository ssr,
                     ScooterTripRepository str, TariffRepository tariffRepository, GeneralPriceRepository pr) throws IOException, SQLException {
        this.scooterRepository = sr;
        this.tripRepository = tr;
        this.scooterStopRepository = ssr;
        this.scooterTripRepository = str;
        this.tariffRepository = tariffRepository;
        this.pricesRepository = pr;
    }

    public void load() throws SQLException, IOException {
        this.loadScooterStop();
        this.loadScooter();
        this.loadPrices();
        this.loadTrip();
        this.loadScooterTrip();
    }

    private void loadScooterStop() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "scooter_stop.csv"));
        for (CSVRecord row : parser) {
            Double x = Double.valueOf(row.get("x"));
            Double y = Double.valueOf(row.get("y"));
            Ubication ubication = new Ubication(x,y);
            ScooterStop scooterStop = new ScooterStop(ubication);
            scooterStopRepository.save(scooterStop);
        }
    }

    private void loadScooter() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "scooter.csv"));
        for (CSVRecord row : parser) {
            String licensePlate = String.valueOf(row.get("license_plate"));
            Boolean available = Boolean.valueOf(row.get("available"));
            Double ubicx = Double.valueOf(row.get("x"));
            Double ubicy = Double.valueOf(row.get("y"));
            Ubication ubication = new Ubication(ubicx,ubicy);
            Scooter scooter = new Scooter(licensePlate, available, ubication);
            scooterRepository.save(scooter);
        }
    }

    private void loadPrices() throws IOException, SQLException {
        GeneralPrice price = new GeneralPrice(198.5, 30.5, true, Timestamp.valueOf(LocalDateTime.now()));
        this.pricesRepository.save(price);
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
            String scooter = String.valueOf(row.get("licenseScooter"));
            Trip trip = new Trip(id, initTime, endTime, kms, ended, 198.5, scooter);
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
