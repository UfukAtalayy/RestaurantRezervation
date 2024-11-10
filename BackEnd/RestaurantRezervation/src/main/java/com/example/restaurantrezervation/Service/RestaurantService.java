package com.example.restaurantrezervation.Service;

import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.DTO.RestaurantDTO;
import com.example.restaurantrezervation.Entity.User;
import com.example.restaurantrezervation.Mapper.RestaurantMapper;
import com.example.restaurantrezervation.Repository.RestaurantRepository;
import com.example.restaurantrezervation.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private UserRepository userRepository;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurantMapper.toDTO(restaurant);
    }

    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Restaurant restaurant = restaurantMapper.toEntity(dto, owner);
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhoneNumber(dto.getPhoneNumber());

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        restaurant.setOwner(owner);

        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}

