package com.greatlearning.empmagement.GreatlearningEmpmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.empmagement.GreatlearningEmpmanagement.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
