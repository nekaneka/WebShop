package com.imse.shopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
