package roomescape.controller.dto;

import roomescape.domain.Reservation;

public class ReservationResponse {
    private final Long id;
    private final String date;
    private final String time;
    private final String themeName;
    private final String name;
    private final String themeDesc;
    private final Integer themePrice;

    public ReservationResponse(Long id, String date, String time, String name, String themeName, String themeDesc, Integer themePrice) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.themeName = themeName;
        this.themeDesc = themeDesc;
        this.themePrice = themePrice;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getThemeDesc() {
        return themeDesc;
    }

    public Integer getThemePrice() {
        return themePrice;
    }

    public static ReservationResponse fromEntity(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getDate().toString(),
                reservation.getTime().toString(),
                reservation.getTheme().getName(),
                reservation.getName(),
                reservation.getTheme().getDesc(),
                reservation.getTheme().getPrice()
        );
    }
}