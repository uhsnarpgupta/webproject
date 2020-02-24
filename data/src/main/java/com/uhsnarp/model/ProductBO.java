package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class ProductBO extends BaseEntity{
    private String name;
    private String description;

    @OneToOne
    private CategoryBO category;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private BusinessBO business;

    @OneToMany(mappedBy = "product")
    private Set<ImageBO> images = new HashSet<>();

    public ProductBO() {
    }
}
