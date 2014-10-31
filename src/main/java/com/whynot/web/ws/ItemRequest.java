/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.ws;

import com.whynot.web.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author potapov
 */
@RestController
//@Controller
public class ItemRequest {
	
	@RequestMapping("/greeting")
	public Item greeting(){
		return new Item();
	}
	
}
