package com.dsmpear.main.domain.user.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter
public class User {
    private String password;
}
