package com.example.demo.health;

import org.springframework.boot.actuate.availability.LivenessStateHealthIndicator;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityState;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.stereotype.Component;

@Component
public class MyLivenessIndicator extends LivenessStateHealthIndicator {
    public MyLivenessIndicator(ApplicationAvailability availability) {
        super(availability);
    }

    @Override
    protected AvailabilityState getState(ApplicationAvailability applicationAvailability) {
        return LivenessState.BROKEN;
    }


}
