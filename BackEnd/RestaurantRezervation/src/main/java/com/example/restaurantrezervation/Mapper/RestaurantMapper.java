package com.example.restaurantrezervation.Mapper;

import com.example.restaurantrezervation.DTO.RestaurantDTO;
import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDTO toDTO(Restaurant restaurant){
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setPhoneNumber(restaurant.getPhoneNumber());
        dto.setOwnerId(restaurant.getOwner()!= null ? restaurant.getOwner().getId():null);
        dto.setCreatedAt(restaurant.getCreatedAt());
        dto.setUpdatedAt(restaurant.getUpdatedAt());
        return dto;
    }

    public Restaurant toEntity(RestaurantDTO restaurantDTO, User owner){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setOwner(owner);
        return restaurant;
    }
}
