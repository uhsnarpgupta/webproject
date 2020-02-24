package com.uhsnarp.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class UserBO extends BaseEntity {
    private String name;

    private Long mobile;
    private Date dob;
    private String gender;

    @OneToOne
    private BusinessBO business;

    @Email
    private String email;

    @OneToOne(cascade= CascadeType.ALL)
    private AddressBO address;

    @Builder
    public UserBO(Integer id, String name, Long mobile, Date dob, String gender, String email,
                  BusinessBO business, AddressBO address) {
        super(id, new Timestamp(System.currentTimeMillis()), null);
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.dob=dob;
        this.gender=gender;
        this.email=email;

        if (business != null) {
            this.business = business;
        }
        if(address !=null){
            this.address=address;
        }
    }
}
