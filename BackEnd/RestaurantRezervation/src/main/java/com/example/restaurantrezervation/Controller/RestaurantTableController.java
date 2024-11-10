package com.example.restaurantrezervation.Controller;

import com.example.restaurantrezervation.DTO.RestaurantTableDTO;
import com.example.restaurantrezervation.Service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @GetMapping
    public List<RestaurantTableDTO> getAllTables(){
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/{id}")
    public RestaurantTableDTO getTableById(@PathVariable Long id){
        return restaurantTableService.getTableById(id);
    }
    @PostMapping
    public RestaurantTableDTO createTable(@RequestBody RestaurantTableDTO dto){
        return restaurantTableService.createTable(dto);
    }

    @PutMapping("/{id}")
    public RestaurantTableDTO updateTable(@PathVariable Long id,@RequestBody RestaurantTableDTO dto){
        return restaurantTableService.updateTable(id,dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id){
        restaurantTableService.deleteTable(id);
    }
}
