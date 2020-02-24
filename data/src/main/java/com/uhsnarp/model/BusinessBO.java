package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "business")
public class BusinessBO extends BaseEntity {
    private String name;
    private String tagLine;
    private Long mobileNumber;
    private Integer category_code;
    private Long gst_number;
    private Long pan_number;
    private String facebookLink;

    @Pattern(regexp = "^[A-Za-z]{4}[a-zA-Z0-9]{7}$")
    private String ifsc_code;

    @OneToOne
    private CategoryBO category;

    @OneToMany(mappedBy = "business")
    private Set<ProductBO> products = new HashSet<>();

}
