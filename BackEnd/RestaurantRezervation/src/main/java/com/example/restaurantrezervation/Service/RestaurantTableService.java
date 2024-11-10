package com.example.restaurantrezervation.Service;

import com.example.restaurantrezervation.DTO.RestaurantTableDTO;
import com.example.restaurantrezervation.Entity.Restaurant;
import com.example.restaurantrezervation.Entity.RestaurantTable;
import com.example.restaurantrezervation.Mapper.RestaurantTableMapper;
import com.example.restaurantrezervation.Repository.RestaurantRepository;
import com.example.restaurantrezervation.Repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantTableService {
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private RestaurantTableMapper restaurantTableMapper;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantTableDTO> getAllTables() {
        return restaurantTableRepository.findAll()
                .stream()
                .map(restaurantTableMapper::toDTO)
                .collect(Collectors.toList());
    }


    public RestaurantTableDTO getTableById(Long id){
        RestaurantTable restaurantTable =restaurantTableRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Table not found"));
        return restaurantTableMapper.toDTO(restaurantTable);
    }

    public RestaurantTableDTO createTable(RestaurantTableDTO dto){
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        RestaurantTable table = restaurantTableMapper.toEntity(dto,restaurant);
        return restaurantTableMapper.toDTO(restaurantTableRepository.save(table));
    }

    public RestaurantTableDTO updateTable(Long id,RestaurantTableDTO dto){
        RestaurantTable table = restaurantTableRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Table not found"));
        table.setTableNumber(dto.getTableNumber());
        table.setSeatingCapacity(dto.getSeatingCapacity());
        // Eğer isAvailable değişirse, güncellenme zamanını da değiştir
        if (table.isAvailable() != dto.isAvailable()) {
            table.setAvailable(dto.isAvailable());
            table.setUpdatedAt(LocalDateTime.now()); // Güncellenme zamanını yenile
        }


        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Restaurant not found"));
        table.setRestaurant(restaurant);
        return restaurantTableMapper.toDTO(restaurantTableRepository.save(table));
    }

    public void deleteTable(Long id){
        restaurantTableRepository.deleteById(id);
    }
}
