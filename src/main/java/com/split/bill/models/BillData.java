package com.split.bill.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class EventData {

    private final String eventTitle;

    private final String eventDate;

    @Override
    public String toString() {
        return String.format("Event title - '%s', event name - '%s'", eventTitle, eventDate);
    }
}
