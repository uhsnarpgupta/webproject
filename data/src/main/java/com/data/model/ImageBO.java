package com.data.model;

import com.data.constants.Media;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "image")
public class ImageBO extends BaseEntity{
    @Lob
    private byte[] portraitImage;

    @Lob
    private byte[] landscapeImage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductBO product;

    private String description;
    private String name;
    private String alt_text;
    private String link;
    private Media type;
}
