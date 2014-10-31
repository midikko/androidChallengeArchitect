/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.service;

import com.whynot.web.dao.PaymentRepository;
import com.whynot.web.domain.Payment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author potapov
 */
@Service
public class PaymentService {
		@Autowired
	PaymentRepository repo;
	
	public Payment create(Payment entity) {
		Payment result = repo.create(entity);	
		return result;
	}

	public boolean delete(Payment entity) {
		return repo.delete(entity);
	}

	public Payment update(Payment entity) {
		return repo.update(entity);
	}
	
	public Payment findById(Long id){
		return repo.findByID(id);
	}
	
	public List<Payment> findAll(){
		return repo.findAll();
	}
}
