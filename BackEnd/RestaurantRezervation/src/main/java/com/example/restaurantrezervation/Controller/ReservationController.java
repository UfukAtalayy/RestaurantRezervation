package com.example.restaurantrezervation.Controller;

import com.example.restaurantrezervation.DTO.ReservationDTO;
import com.example.restaurantrezervation.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO dto){
        return reservationService.createReservation(dto);
    }

    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO dto){
        return reservationService.updateReservation(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}
