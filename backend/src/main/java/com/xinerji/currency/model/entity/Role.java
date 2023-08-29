package com.xinerji.currency.model.entity;

import com.xinerji.currency.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private String name;
}
