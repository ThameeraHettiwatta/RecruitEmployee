package com.example.RecruitEmployee.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class Manager {
    private String name;
    private Integer id;
    private String contactNumber;
    private String email;
}
