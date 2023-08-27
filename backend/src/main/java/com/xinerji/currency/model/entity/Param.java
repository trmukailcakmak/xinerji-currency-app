package com.xinerji.currency.model.entity;

import com.xinerji.currency.model.base.ExtendedModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "param")
@Data
@NoArgsConstructor
public class Param extends ExtendedModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String key;

    private String value;
}
