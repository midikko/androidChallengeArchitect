/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.ws;

import com.whynot.web.account.Account;
import com.whynot.web.account.UserService;
import com.whynot.web.dao.PriceRepository;
import com.whynot.web.domain.Item;
import com.whynot.web.domain.PriceList;
import com.whynot.web.domain.Shop;
import com.whynot.web.service.ItemService;
import com.whynot.web.service.ShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author potapov
 */
public class LogicController {
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PriceRepository priceRepo;
	
	@RequestMapping(value = "rest/add_item", method = RequestMethod.POST)
	public @ResponseBody String login(DataPart data){
		if (!LoginController.usermap.containsKey(data.getToken())){
			return "Access denied";
		}
		
		Shop shop = shopService.findByShop(data.getShop());
		
		if (shop == null){
			shop = shopService.create(data.getShop());
		}
		
		List<PriceList> item = priceRepo.findPricesByItem(data.getItem(),data.getShop());
		
		if (item == null){
			itemService.create(data.getItem());
		}
		
		return response;
	}
}
