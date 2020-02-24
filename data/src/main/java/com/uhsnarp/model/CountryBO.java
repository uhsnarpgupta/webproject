package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "country")
public class CountryBO extends BaseEntity {
    private String name;
    private String countryCode;
    private String continent_name;
}
