package com.example.entity;

import com.example.entity.base.IdentityBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends IdentityBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "product_type_id", nullable = false)
    private Integer productTypeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", insertable = false, updatable = false)
    private ProductTypeEntity productType;
}
