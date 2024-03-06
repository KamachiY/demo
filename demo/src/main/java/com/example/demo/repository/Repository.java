package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Item;

public interface Repository extends CrudRepository<Item, Integer>  {
//	@Modifying
//	@Query(value = "INSERT INTO item VALUES(:id, :name, :price)")
//	void InsertData(@Param("id") Integer id,
//					@Param("name") String name,
//					@Param("price") Integer price);
	
}
