package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.repository.Repository;

@Service
@Transactional
public class ServiceImpl implements SampleService {
	
	@Autowired
	Repository repo;
	
	@Override
	public Iterable<Item> SelectAll() {
		System.out.println("商品表示");
		return repo.findAll();
	}
	
	@Override
	public Optional<Item> SelectOneById(Integer id){
		System.out.println("商品表示：" + id);
		return repo.findById(id);
	}
	
	@Override
	public void InsertData(Item entity) {
//		repo.InsertData(entity.getId(),
//						entity.getName(),
//						entity.getPrice());
		repo.save(entity);
		System.out.println("Insert実行");
	}

	@Override
	public void UpdateData(Item entity) {
		repo.save(entity);
		System.out.println("Update実行");
	}

	@Override
	public void DeleteDataById(Integer id) {
		repo.deleteById(id);
	}	
	

}
