package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class IntineraryService {

    private TimetableService timetableService;

    public IntineraryService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime) {
        List<LocalTime> arrivalTimes = new ArrayList<>();
        List<Line> lines = timetableService.findLinesThrough(departure, destination);
        for (Line line : lines) {
            List<LocalTime> foundArrivalTimes = timetableService.findArrivalTimes(line, departure);
            for (LocalTime foundArrivalTime : foundArrivalTimes) {
                if (foundArrivalTime.isAfter(startTime) && foundArrivalTime.isBefore(startTime.plusMinutes(15))) {
                    arrivalTimes.add(foundArrivalTime);
                }
            }
        }
        return arrivalTimes;
    }
}