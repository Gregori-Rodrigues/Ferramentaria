package com.mademil.ferramentaria.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeParser {
    public static int parseCycleTimeToTotalSeconds(String cycleTime) throws IllegalArgumentException {
        cycleTime = cycleTime.trim();

        String regexHHMMSS = "^([0-9]{2}):([0-9]{2}):([0-9]{2})$";  
        String regexMMSS = "^([0-9]{1,2}):([0-9]{2})$";  

        if (cycleTime.matches(regexHHMMSS)) {
            String[] parts = cycleTime.split(":", 3);
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            return (hours * 3600) + (minutes * 60) + seconds;
        }
        else if (cycleTime.matches(regexMMSS)) {
            String[] parts = cycleTime.split(":", 2);
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return (minutes * 60) + seconds;
        }
        else {
            throw new IllegalArgumentException("Invalid cycle time format. Expected formats are hh:mm:ss or mm:ss.");
        }
    }

    public static String formatCycleTime(Integer totalSeconds) {
        if (totalSeconds < 3600) {
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d", minutes, seconds); 
        } else {
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, seconds); 
        }
    }

    public static String parseTimesTampIntoCustomFormat(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm:ss");

        return dateTime.format(formatter);
    }
}