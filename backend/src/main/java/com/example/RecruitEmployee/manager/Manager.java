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
    private Integer managerId;
    private String managerName;
    private String managerEmail;
    private String managerContactNumber;
}
