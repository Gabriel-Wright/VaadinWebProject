package com.gabeWebTest.webTest.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Embeddable
public class Dates  {

    public Dates() {
    }

    public Dates(LocalDateTime timeCreated, LocalDateTime timeLastUpdated) {
        this.timeCreated = timeCreated;
        this.timeLastUpdated = timeLastUpdated;
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timeCreated;

    @UpdateTimestamp
    private LocalDateTime timeLastUpdated;

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeLastUpdated() {
        return timeLastUpdated;
    }
}
