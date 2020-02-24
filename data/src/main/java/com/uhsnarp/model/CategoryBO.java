package com.uhsnarp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "category")
public class CategoryBO extends BaseEntity{
    @NotNull
    @Size(min = 3, max = 25)
    private String name;

    @NotNull
    @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String colorCode;

    private String description;
}
