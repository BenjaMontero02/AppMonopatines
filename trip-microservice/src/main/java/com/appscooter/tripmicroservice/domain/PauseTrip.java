package com.appscooter.tripmicroservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long timePause;
    @Column
    private Time initPause;
    @Column
    private Time endPause;

    public PauseTrip(Time initPause) {
        this.initPause = initPause;
    }

    public PauseTrip(Long time, Time init, Time end) {
        this.timePause=time;
        this.initPause=init;
        this.endPause=end;
    }
}
