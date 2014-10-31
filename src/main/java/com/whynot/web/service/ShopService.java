/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.service;

import com.whynot.web.dao.ShopRepository;
import com.whynot.web.domain.Shop;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author potapov
 */
@Service
public class ShopService {
		@Autowired
	ShopRepository repo;
	
	public Shop create(Shop entity) {
		Shop result = repo.create(entity);	
		return result;
	}

	public boolean delete(Shop entity) {
		return repo.delete(entity);
	}

	public Shop update(Shop entity) {
		return repo.update(entity);
	}
	
	public Shop findById(Long id){
		return repo.findByID(id);
	}
	
	public List<Shop> findAll(){
		return repo.findAll();
	}

	public Shop findByShop(Shop shop) {
		return repo.findByShop(shop);
	}
}
