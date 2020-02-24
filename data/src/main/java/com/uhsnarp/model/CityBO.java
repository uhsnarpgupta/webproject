package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "city")
public class CityBO extends BaseEntity {
    private Integer pinCode;
    private String name;

    @Pattern(regexp = "^[A-Za-z]{3}$")
    private String countryCode;
}