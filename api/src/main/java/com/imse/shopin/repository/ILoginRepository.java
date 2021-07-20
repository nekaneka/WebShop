package com.imse.shopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Employee;
import com.imse.shopin.model.Login;
import com.imse.shopin.model.User;

public interface ILoginRepository extends JpaRepository<Login, String>{

	Login findByUser(User user);

	Login findByemployee(Employee emp);

}
