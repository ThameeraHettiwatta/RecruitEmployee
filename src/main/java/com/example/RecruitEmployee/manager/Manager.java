package com.example.RecruitEmployee.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
@Setter
@Entity
public class Manager {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
}
