package com.imse.shopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imse.shopin.model.Item;

public interface IItemRepository extends JpaRepository<Item, String> {

}
