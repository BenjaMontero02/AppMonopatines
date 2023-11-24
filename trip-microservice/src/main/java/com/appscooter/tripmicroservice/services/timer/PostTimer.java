package com.appscooter.tripmicroservice.services.timer;

import com.appscooter.tripmicroservice.domain.GeneralPrice;
import com.appscooter.tripmicroservice.domain.PauseTrip;
import com.appscooter.tripmicroservice.domain.Tariff;
import com.appscooter.tripmicroservice.domain.Trip;
import com.appscooter.tripmicroservice.repositories.GeneralPriceRepository;
import com.appscooter.tripmicroservice.repositories.TariffRepository;
import com.appscooter.tripmicroservice.repositories.TripRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;
import java.util.TimerTask;

public class PostTimer extends TimerTask {
    private Long idTrip;
    private TripRepository tripRepository;
    private TariffRepository tariffRepository;
    private GeneralPriceRepository priceRepository;

    public PostTimer(Long idTrip, TripRepository tr, TariffRepository tariffRepository,
                     GeneralPriceRepository pr) {
        this.idTrip=idTrip;
        this.tripRepository=tr;
        this.tariffRepository=tariffRepository;
        this.priceRepository=pr;
    }

    @Override
    public void run() {
        Optional<Trip> trip = this.tripRepository.findById(idTrip);
        if(!trip.isEmpty()) {
            PauseTrip pauseTrip = trip.get().getPause();
            if(pauseTrip.getEndPause() == null) {
                Time endPause = Time.valueOf(LocalTime.now());
                Time initPause = pauseTrip.getInitPause();
                Long diff = endPause.getTime() - initPause.getTime();
                pauseTrip.setTimePause(diff/1000);
                pauseTrip.setEndPause(endPause);
                GeneralPrice generalPrice = this.priceRepository.findByCurrent(true);
                Tariff tariffExtra = new Tariff(generalPrice.getPriceInterest(), 2L);
                trip.get().setTariffExtra(tariffExtra);
                this.tripRepository.save(trip.get());
            }
        }
    }
}

