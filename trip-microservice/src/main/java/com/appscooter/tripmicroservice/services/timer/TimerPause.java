package com.appscooter.tripmicroservice.services.timer;


import com.appscooter.tripmicroservice.domain.PauseTrip;
import com.appscooter.tripmicroservice.domain.Trip;
import com.appscooter.tripmicroservice.repositories.GeneralPriceRepository;
import com.appscooter.tripmicroservice.repositories.TariffRepository;
import com.appscooter.tripmicroservice.repositories.TripRepository;

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
        timer.schedule(new PostTimer(idTrip, repository, tariffRep, priceRepository), 2 * 60 * 1000);
        trip.get().setPause(new PauseTrip(Time.valueOf(LocalTime.now())));
        this.repository.save(trip.get());
    }


}
