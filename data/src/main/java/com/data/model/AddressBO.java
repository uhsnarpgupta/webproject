package com.data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "address")
public class AddressBO extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "city_pinCode")
    private CityBO city;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
}
