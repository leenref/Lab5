package com.example.lab5eventsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
    private String id;
    private String description;
    private int capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
