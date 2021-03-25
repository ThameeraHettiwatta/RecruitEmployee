package com.example.RecruitEmployee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private  Integer projectId;
    private String projectName;
    private String projectLocation;
    private Integer managerId;
}
