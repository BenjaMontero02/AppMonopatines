package com.appscootercopy.scooterusemicroservice.service.timer;

import com.appscootercopy.scooterusemicroservice.domain.PauseTrip;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import com.appscootercopy.scooterusemicroservice.repository.GeneralPriceRepository;
import com.appscootercopy.scooterusemicroservice.repository.TariffRepository;
import com.appscootercopy.scooterusemicroservice.repository.TripRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Timer;

public class TimerPause {
    private Timer timer;
    private long tiempoInicio;
    private Long idTrip;
    private TripRepository repository;
    private TariffRepository tariffRep;
    private GeneralPriceRepository priceRepository;

    public TimerPause(Long idTrip, TripRepository tr, TariffRepository tfr, GeneralPriceRepository pr) {
        this.idTrip=idTrip;
        this.repository=tr;
        this.tariffRep=tfr;
        this.priceRepository=pr;
    }

    public void initPause() {
        timer = new Timer();
        tiempoInicio = System.currentTimeMillis();
        Optional<Trip> trip = this.repository.findById(idTrip);
        timer.schedule(new PostTimer(idTrip, repository, tariffRep, priceRepository), 1 * 60 * 1000);
        trip.get().setPause(new PauseTrip(Time.valueOf(LocalTime.now())));
        this.repository.save(trip.get());
        System.out.println("pausa iniciada: "+Time.valueOf(LocalTime.now()));
    }


}
