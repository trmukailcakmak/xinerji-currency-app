package com.xinerji.currency.model.entity;

import com.xinerji.currency.model.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "param")
@Data
@NoArgsConstructor
public class Param extends BaseEntity {

    @Column(unique = true)
    private String key;

    private String value;
}
