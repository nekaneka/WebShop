package com.imse.shopin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.imse.shopin.model.User;

public interface IUserRepository extends JpaRepository<User, String> {
	
	
//	@Query("update User u set u.is_logedin = ?2 where userId = ?1")
//	@Modifying
//	@Transactional
//	Integer updateUser(String uuid, boolean bool);

	User findByisLogedin(boolean bool);

}
