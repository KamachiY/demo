package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Item;

public interface SampleService {

	/*+
	 * 全件取得
	 */
	Iterable<Item> SelectAll();
	
	/**
	 * idをキーにして１件取得
	 */
	Optional<Item> SelectOneById(Integer id);
	
	/**
	 * 取得したデータをDBにINSERT
	 */
	void InsertData(Item entity);
	
	/**
	 * データを更新する
	 */
	void UpdateData(Item entity);
	
	/**
	 * データを削除する
	 */
	void DeleteDataById(Integer id);
	
}
