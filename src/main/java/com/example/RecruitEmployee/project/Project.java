package com.example.RecruitEmployee.project;

import com.example.RecruitEmployee.manager.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    private  Integer projectId;
    private String projectName;
    private String projectLocation;
    private Manager manager;
}
