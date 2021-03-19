package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {
    private  Integer projectId;
    private String projectName;
    private String projectLocation;
    private Integer managerId;
}
