package com.urooms.management.managementMicroservice.domain.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalCreateEvent {

    private int id;

    private double price;

    public RentalCreateEvent() {}

    public RentalCreateEvent(int id, double price) {
        this.id = id;
        this.price = price;
    }

}
