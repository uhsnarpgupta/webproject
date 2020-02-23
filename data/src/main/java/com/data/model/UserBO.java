package com.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Table(name = "user")
public class UserBO extends BaseEntity {
    //@Column(name = "full_name")
    private String fullname;

    private Long mobile;
    private Date dob;
    private String gender;

    @OneToOne
    private BusinessBO business;

    @Email
    private String email;

    @OneToOne(cascade= CascadeType.ALL)
    private AddressBO address;
}
