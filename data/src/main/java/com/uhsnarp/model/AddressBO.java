package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class AddressBO extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_pinCode")
    private CityBO city;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
}
