package com.voidpulse.pulseevents.events;

public interface PulseEvent {

    String getName();

    void start();

    void stop();

    int getDuration();
}