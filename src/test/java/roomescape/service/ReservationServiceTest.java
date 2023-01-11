package roomescape.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import roomescape.controller.dto.ReservationRequest;
import roomescape.controller.dto.ReservationResponse;
import roomescape.domain.Reservation;
import roomescape.exception.RoomEscapeException;
import roomescape.repository.ReservationWebRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationWebRepository reservationWebRepository;

    @BeforeEach
    void setUp() {
        reservationWebRepository.deleteAllReservations();
    }

    @DisplayName("ReservationRequest를 통해 새로운 Reservation을 등록할 수 있다.")
    @Test
    void createReservation() {
        // given
        ReservationRequest reservationRequest = new ReservationRequest(
            LocalDate.now(),
            LocalTime.of(11, 30),
            "name"
        );

        // when
        Long reservationId = reservationService.createReservation(reservationRequest);

        // then
        assertThat(reservationId).isNotNull();
    }

    @DisplayName("Reservation 등록시, 동일 날짜 동일 시간대에 예약이 있다면 예약이 불가능하다.")
    @Test
    void createReservationDuplicated() {
        // given
        ReservationRequest reservationRequest1 = new ReservationRequest(
            LocalDate.now(),
            LocalTime.of(11, 30),
            "reservation1"
        );
        reservationService.createReservation(reservationRequest1);

        ReservationRequest reservationRequest2 = new ReservationRequest(
            LocalDate.now(),
            LocalTime.of(11, 30),
            "reservation2"
        );

        // when & then
        assertThatThrownBy(() -> reservationService.createReservation(reservationRequest2))
            .isInstanceOf(RoomEscapeException.class);
    }

    @DisplayName("ReservationId를 통해 예약을 조회할 수 있다.")
    @Test
    void getReservation() {
        // given
        ReservationRequest reservationRequest = new ReservationRequest(
            LocalDate.now(),
            LocalTime.of(11, 30),
            "name"
        );
        Long reservationId = reservationService.createReservation(reservationRequest);

        // when
        ReservationResponse reservationResponse = reservationService.getReservation(reservationId);

        // then
        assertThat(reservationId).isEqualTo(reservationResponse.getId());
        assertThat(reservationRequest.getName()).isEqualTo(reservationResponse.getName());
        assertThat(reservationRequest.getDate().toString()).isEqualTo(reservationResponse.getDate());
        assertThat(reservationRequest.getTime().toString()).isEqualTo(reservationResponse.getTime());
    }

    @DisplayName("Reservation을 Id로 삭제할 수 있다.")
    @Test
    void deleteReservation() {
        // given
        ReservationRequest reservationRequest = new ReservationRequest(
            LocalDate.now(),
            LocalTime.of(11, 30),
            "name"
        );
        Long reservationId = reservationService.createReservation(reservationRequest);

        // when
        reservationService.deleteReservation(reservationId);

        // then
        Optional<Reservation> reservation = reservationWebRepository.getReservation(reservationId);
        assertThat(reservation).isEmpty();
    }
}