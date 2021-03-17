package com.example.RecruitEmployee.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class Manager {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
}
