/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.controllers;

import com.google.gson.Gson;
import com.wallet.entities.CardDetails;
import com.wallet.services.WalletService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ketan.jariwala
 */
@Controller
@RequestMapping("/wallet/card")
public class CardController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = {"/addCardDetails"}, method = RequestMethod.POST)
    public @ResponseBody
    void addCardDetails(@RequestBody String cardData, HttpSession session) throws Exception {
        System.out.println(cardData);
        CardDetails cardDetails = new Gson().fromJson(cardData, CardDetails.class);

        cardDetails.setEmailId((String) session.getAttribute("userId"));
        System.out.println(cardDetails.toString());
        walletService.addCardDetails(cardDetails);
    }

    @RequestMapping(value = {"/getCardDetails"}, method = RequestMethod.GET)
    public @ResponseBody
    String getCardDetails(HttpSession session) throws Exception {
        String emailId = (String) session.getAttribute("userId");
        return new Gson().toJson(walletService.getCardDetails(emailId), List.class);
    }
}
