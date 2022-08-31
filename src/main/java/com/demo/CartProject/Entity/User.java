package com.demo.CartProject.Entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="user")
public class User extends ModelBase {

@Column(name = "name")
private String name;

@Column(name = "email")
private String email;

@Column(name = "password")
private String password;

}
