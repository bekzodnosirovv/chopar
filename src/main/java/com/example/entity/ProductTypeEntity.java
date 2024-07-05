package com.example.entity;

import com.example.entity.base.SequencesBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_type")
public class ProductTypeEntity extends SequencesBaseEntity {

    @Column(name = "name")
    private String name;
}
