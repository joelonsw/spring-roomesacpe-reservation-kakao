package roomescape.controller.dto;

import roomescape.domain.Reservation;
import roomescape.domain.Theme;
import roomescape.exception.ErrorCode;
import roomescape.exception.RoomEscapeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ReservationRequest {

    private final LocalDate date;
    private final LocalTime time;
    private final String name;

    public ReservationRequest(String date, String time, String name) {
        this.date = validateDate(date);
        this.time = validateTime(time);
        this.name = name;
    }

    private LocalDate validateDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new RoomEscapeException(ErrorCode.VALID_INPUT_REQUIRED);
        }
    }

    private LocalTime validateTime(String time) {
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new RoomEscapeException(ErrorCode.VALID_INPUT_REQUIRED);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Reservation toEntity(Theme theme) {
        return new Reservation(
                date,
                time,
                name,
                theme
        );
    }
}