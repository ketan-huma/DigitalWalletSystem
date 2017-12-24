/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.controllers;

import com.google.gson.Gson;
import com.wallet.entities.User;
import com.wallet.services.WalletService;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/wallet")
public class AuthenticationController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public @ResponseBody
    String register(@RequestBody String userData, HttpSession session) throws Exception {
        System.out.println(userData);
        User userEntity = new Gson().fromJson(userData, User.class);

        System.out.println(userEntity.toString());
        walletService.register(userEntity);
        session.setAttribute("userId", userEntity.getEmail());
        return String.valueOf(true);
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody String user, HttpSession session) {
        User userEntity = new Gson().fromJson(user, User.class);
        session.setAttribute("userId", userEntity.getEmail());
        return String.valueOf(walletService.login(userEntity));
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public @ResponseBody
    String logout(HttpSession session) {
        session.invalidate();
        return String.valueOf(true);
    }

    @RequestMapping(value = {"/checkEmailExists"}, method = RequestMethod.POST)
    public @ResponseBody
    String checkEmailExists(@RequestBody String emailId, HttpServletRequest request) {
        System.out.println("Email " + emailId);
        return String.valueOf(walletService.isEmailExists(emailId));
    }
}
