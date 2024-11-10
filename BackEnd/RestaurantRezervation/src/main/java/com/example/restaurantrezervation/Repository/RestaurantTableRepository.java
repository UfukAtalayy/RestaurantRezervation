package com.example.restaurantrezervation.Repository;
import com.example.restaurantrezervation.Entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {
}
