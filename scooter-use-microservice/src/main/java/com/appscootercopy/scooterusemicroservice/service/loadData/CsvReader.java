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
    private ScooterStopRepository scooterStopRepository;
    private static final String userDir =
            System.getProperty("user.dir") + "/src/main/java/com/appscootercopy/scooterusemicroservice/service/loadData/";

    @Autowired
    public CsvReader(ScooterRepository sr, ScooterStopRepository ssr) throws IOException, SQLException {
        this.scooterRepository = sr;
        this.scooterStopRepository = ssr;
    }

    public void load() throws SQLException, IOException {
        this.loadScooterStop();
        this.loadScooter();
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

}
