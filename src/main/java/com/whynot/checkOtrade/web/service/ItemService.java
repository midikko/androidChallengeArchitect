/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.service;

import com.whynot.checkOtrade.web.dao.ItemRepository;
import com.whynot.checkOtrade.web.domain.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service-level сущность для товаров
 * @author potapov
 */
@Service
public class ItemService {
	
	@Autowired
	ItemRepository repo;
	
	public Item create(Item entity) {
		Item result = repo.create(entity);	
		return result;
	}

	public boolean delete(Item entity) {
		return repo.delete(entity);
	}

	public Item update(Item entity) {
		return repo.update(entity);
	}
	
	public Item findById(Long id){
		return repo.findByID(id);
	}
	
	public List<Item> findAll(){
		return repo.findAll();
	}
        
        public Item findByName(String name){
		return repo.findByName(name);
	}

}
