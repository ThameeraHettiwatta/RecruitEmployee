package com.example.RecruitEmployee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("SalaryMapper")
public interface SalaryMapper {
}
