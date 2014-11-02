/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.ws;

import com.whynot.checkOtrade.web.account.Account;
import com.whynot.checkOtrade.web.account.AccountService;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * контроллер авторизации пользователей через RESTful сервисы.
 *
 * @author potapov
 */
@RestController
public class LoginController {

    @Autowired
    AccountService us;

    static Map<String, Account> usermap = new HashMap<>();

    /**
     * Получаем на вход реквизиты пользователя, если они 
     * соответствуют действительности - сообщаем 
     * об успехе операции пользователю и возвращаем ключ-токен,
     * и добавляем в таблицу.
     * @param data
     * @return
     */
    @RequestMapping(value = "rest/login", method = RequestMethod.POST)
    public @ResponseBody String login(LoginData data) {
        String response = "null";
        System.out.println(data.getLogin() + " :: " + data.getPassword());
        Account acc = us.findByEmail(data.getLogin());
        System.out.println("acc :: " + acc);
        if (acc == null) {
            return response;
        }
        System.out.println("acc :: " + acc);
        if (acc.getPassword().equals(data.getPassword())) {
            SecureRandom random = new SecureRandom();
            response = new BigInteger(130, random).toString(32);
        }
        usermap.put(response, acc);
        return response;
    }

}
