/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.ws;

import com.whynot.checkOtrade.web.domain.Item;
import com.whynot.checkOtrade.web.domain.Shop;
import java.io.Serializable;

/**
 *  <p> Сущность для обмена данными между нами и мобильным устройством</p>
 * @author potapov
 */
public class DataPart implements Serializable{
	private String token;
	private Item item;
	private Shop shop;
        private int price;

	public DataPart() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
	
	
}
