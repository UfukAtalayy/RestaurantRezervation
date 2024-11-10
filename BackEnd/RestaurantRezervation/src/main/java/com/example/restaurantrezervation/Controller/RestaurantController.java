package com.example.restaurantrezervation.Controller;

import com.example.restaurantrezervation.DTO.RestaurantDTO;
import com.example.restaurantrezervation.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO dto) {
        return restaurantService.createRestaurant(dto);
    }

    @PutMapping("/{id}")
    public RestaurantDTO updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        return restaurantService.updateRestaurant(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
