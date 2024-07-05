package com.example.entity;

import com.example.entity.base.IdentityBaseEntity;
import com.example.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrdersEntity extends IdentityBaseEntity {

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "address")
    private String address;

    @Column(name = "number_of_products")
    private Integer numberOfProducts;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
}
