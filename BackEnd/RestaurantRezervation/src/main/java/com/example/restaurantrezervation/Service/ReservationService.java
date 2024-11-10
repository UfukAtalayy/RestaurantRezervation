package com.example.restaurantrezervation.Service;

import com.example.restaurantrezervation.DTO.ReservationDTO;
import com.example.restaurantrezervation.Entity.Reservation;
import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.Entity.RestaurantTable;
import com.example.restaurantrezervation.Entity.User;
import com.example.restaurantrezervation.Mapper.ReservationMapper;
import com.example.restaurantrezervation.Repository.ReservationRepository;
import com.example.restaurantrezervation.Repository.RestaurantRepository;
import com.example.restaurantrezervation.Repository.RestaurantTableRepository;
import com.example.restaurantrezervation.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantTableRepository tableRepository;

    public List<ReservationDTO> getAllReservations(){
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDTO)
                .collect(Collectors.toList());
    }
    public ReservationDTO getReservationById(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Reservation not found"));
        return reservationMapper.toDTO(reservation);
    }

    public ReservationDTO createReservation(ReservationDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        RestaurantTable table = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));
        Reservation reservation = reservationMapper.toEntity(dto, restaurant, user, table);
        if (reservation.getReservationTime() == null) {
            reservation.setReservationTime(LocalDateTime.now());
        }

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }
    public ReservationDTO updateReservation(Long id,ReservationDTO dto){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Reservation not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        RestaurantTable table = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new RuntimeException("Table not found"));
        reservation.setUser(user);
        reservation.setRestaurant(restaurant);
        reservation.setTable(table);
        reservation.setReservationTime(dto.getReservationTime());
        reservation.setStatus(dto.getStatus());
        reservation.setNumberOfGuests(dto.getNumberOfGuests());
        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id){
        reservationRepository.deleteById(id);
    }
}
