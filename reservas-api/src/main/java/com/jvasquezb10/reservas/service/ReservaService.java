package com.jvasquezb10.reservas.service;

import com.jvasquezb10.reservas.dto.ReservaRequest;
import com.jvasquezb10.reservas.model.Reserva;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final List<Reserva> reservas = new ArrayList<>();
    private Long siguienteId = 1L;

    public Reserva crearReserva(ReservaRequest request) {
        Reserva reserva = new Reserva();

        reserva.setId(siguienteId++);
        reserva.setNombreCliente(request.getNombreCliente());
        reserva.setHabitacion(request.getHabitacion());
        reserva.setFechaEntrada(request.getFechaEntrada());
        reserva.setFechaSalida(request.getFechaSalida());
        reserva.setEstado(request.getEstado());

        reservas.add(reserva);

        return reserva;
    }

    public List<Reserva> obtenerReservas() {
        return reservas;
    }

    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst();
    }

    public Optional<Reserva> actualizarReserva(Long id, ReservaRequest request) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                reserva.setNombreCliente(request.getNombreCliente());
                reserva.setHabitacion(request.getHabitacion());
                reserva.setFechaEntrada(request.getFechaEntrada());
                reserva.setFechaSalida(request.getFechaSalida());
                reserva.setEstado(request.getEstado());

                return Optional.of(reserva);
            }
        }

        return Optional.empty();
    }

    public Optional<Reserva> cancelarReserva(Long id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                reserva.setEstado("CANCELADA");
                return Optional.of(reserva);
            }
        }

        return Optional.empty();
    }

    public boolean datosValidos(ReservaRequest request) {
        return request != null
                && request.getNombreCliente() != null && !request.getNombreCliente().isBlank()
                && request.getHabitacion() != null && !request.getHabitacion().isBlank()
                && request.getFechaEntrada() != null
                && request.getFechaSalida() != null
                && request.getEstado() != null && !request.getEstado().isBlank();
    }
}