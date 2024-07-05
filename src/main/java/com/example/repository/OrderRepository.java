package com.example.repository;

import com.example.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrdersEntity, Integer> {

    Optional<OrdersEntity> findByProfileId(Integer id);

    @Query(value = "select o.id, o.address, o.number_of_products, o.price, o.status, o.created_date from orders as o " +
            " where o.profile_id =:profileId and o.visible = true ", nativeQuery = true)
    List<OrdersEntity> getOrderList(@Param("profileId") Integer profileId);

}
