package com.example.restaurantrezervation.Mapper;

import com.example.restaurantrezervation.DTO.ReservationDTO;
import com.example.restaurantrezervation.Entity.Reservation;
import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.Entity.RestaurantTable;
import com.example.restaurantrezervation.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation reservation){
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setRestaurantId(reservation.getRestaurant().getId());
        dto.setUserId(reservation.getUser().getId());
        dto.setTableId(reservation.getTable().getId());
        dto.setNumberOfGuests(dto.getNumberOfGuests());
        dto.setReservationTime(dto.getReservationTime());
        dto.setStatus(dto.getStatus());
        dto.setCreatedAt(dto.getCreatedAt());
        dto.setUpdatedAt(dto.getUpdatedAt());
        return dto;
    }

    public Reservation toEntity(ReservationDTO dto, Restaurant restaurant, User user, RestaurantTable table){
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTable(table);
        reservation.setRestaurant(restaurant);
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setStatus(dto.getStatus());
        reservation.setNumberOfGuests(dto.getNumberOfGuests());
        return reservation;
    }

}
