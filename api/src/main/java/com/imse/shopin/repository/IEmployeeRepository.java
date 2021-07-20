package com.imse.shopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, String> {

}
