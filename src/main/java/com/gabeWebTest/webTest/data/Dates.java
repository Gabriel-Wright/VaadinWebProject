package com.gabeWebTest.webTest.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Embeddable
public class Dates extends WebPage {

    @NotBlank
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime timeCreated;

    @NotBlank
    @UpdateTimestamp
    private LocalDateTime timeLastUpdated;

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeLastUpdated() {
        return timeLastUpdated;
    }
}
