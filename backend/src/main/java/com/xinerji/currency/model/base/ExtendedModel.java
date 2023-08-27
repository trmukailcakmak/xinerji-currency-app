package com.xinerji.currency.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xinerji.currency.model.constant.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class ExtendedModel {

    public abstract Long getId();

    public abstract void setId(Long id);

    private Integer status = Status.ACTIVE; //1 aktif, 0 pasif

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public void setInactive() {
        this.status = Status.PASSIVE;
    }

    public void setActive() {
        this.status = Status.ACTIVE;
    }

    @JsonIgnore
    @Version
    private int version;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createDate;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @JsonIgnore
    @CreatedBy
    private Long insertBy;

    @JsonIgnore
    @LastModifiedBy
    private Long lastModifiedBy;

}

