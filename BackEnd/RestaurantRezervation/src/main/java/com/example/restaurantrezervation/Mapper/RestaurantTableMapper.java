package com.example.restaurantrezervation.Mapper;

import com.example.restaurantrezervation.DTO.RestaurantTableDTO;
import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.Entity.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableMapper {

    public RestaurantTableDTO toDTO(RestaurantTable restaurantTable){
        RestaurantTableDTO dto = new RestaurantTableDTO();
        dto.setId(restaurantTable.getId());
        dto.setAvailable(restaurantTable.isAvailable());
        dto.setRestaurantId(restaurantTable.getRestaurant().getId());
        dto.setTableNumber(restaurantTable.getTableNumber());
        dto.setSeatingCapacity(restaurantTable.getSeatingCapacity());
        dto.setCreatedAt(restaurantTable.getCreatedAt());
        dto.setUpdatedAt(restaurantTable.getUpdatedAt());
        return dto;
    }

    public RestaurantTable toEntity(RestaurantTableDTO dto, Restaurant restaurant){
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setRestaurant(restaurant);
        restaurantTable.setAvailable(dto.isAvailable());
        restaurantTable.setTableNumber(dto.getTableNumber());
        restaurantTable.setSeatingCapacity(dto.getSeatingCapacity());
        return restaurantTable;
    }
}
