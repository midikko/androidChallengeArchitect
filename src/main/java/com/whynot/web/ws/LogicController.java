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
import com.whynot.web.domain.Payment;
import com.whynot.web.domain.PriceList;
import com.whynot.web.domain.Shop;
import com.whynot.web.service.ItemService;
import com.whynot.web.service.PaymentService;
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

    @Autowired
    PaymentService paymentService;

    int point;

    @RequestMapping(value = "rest/add_item", method = RequestMethod.POST)
    public @ResponseBody
    String login(DataPart data) {
        if (!LoginController.usermap.containsKey(data.getToken())) {
            return "Access denied";
        }

        Shop shop = shopService.findByShop(data.getShop());

        if (shop == null) {
            shop = shopService.create(data.getShop());
            point += 2;
        }

        Item item = itemService.findByName(data.getItem().getName());
        if (item == null) {
            item = itemService.create(data.getItem());
            point += 2;
        }

        List<PriceList> items = priceRepo.findPricesByItem(item, shop);
        PriceList itemPrice = null;
        if (items == null) {
            PriceList priceitem = new PriceList(data.getPrice(), item, shop);
            point++;
            priceRepo.create(priceitem);
            Account acc = LoginController.usermap.get(data.getToken());
            Payment pay = new Payment(Payment.Type.DEBIT, point, acc);
            paymentService.create(pay);
            return "Success added! " + point + " points gained";
        } else {
            itemPrice = priceRepo.findItemPrice(data.getItem(), data.getShop(), data.getPrice());
            if (itemPrice == null) {
                point += 2;
                PriceList priceitem = new PriceList(data.getPrice(), item, shop);
                priceRepo.create(priceitem);
                Account acc = LoginController.usermap.get(data.getToken());
                Payment pay = new Payment(Payment.Type.DEBIT, point, acc);
                paymentService.create(pay);
                return "Success added! " + point + " points gained";
            } else {
                return "Already in base!";
            }
        }
    }
}
