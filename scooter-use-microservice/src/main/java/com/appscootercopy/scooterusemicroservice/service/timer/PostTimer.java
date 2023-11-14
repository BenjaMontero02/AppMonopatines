package com.appscootercopy.scooterusemicroservice.service.timer;

import com.appscootercopy.scooterusemicroservice.domain.GeneralPrice;
import com.appscootercopy.scooterusemicroservice.domain.PauseTrip;
import com.appscootercopy.scooterusemicroservice.domain.Tariff;
import com.appscootercopy.scooterusemicroservice.domain.Trip;
import com.appscootercopy.scooterusemicroservice.repository.GeneralPriceRepository;
import com.appscootercopy.scooterusemicroservice.repository.TariffRepository;
import com.appscootercopy.scooterusemicroservice.repository.TripRepository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
            else {
                System.out.println("La pausa ya ha sido cancelada antes");
            }
        }
        else {
            System.out.println("no trajo el trip");
        }
    }
}
