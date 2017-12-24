/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.services;

import com.wallet.entities.CardDetails;
import com.wallet.entities.User;
import java.util.List;

/**
 *
 * @author ketan.jariwala
 */
public interface WalletService {

    public void register(User userdetails);

    public boolean login(User userdetails);

    public boolean isEmailExists(String emailId);

    public void addCardDetails(CardDetails cardDetails);

    public List<CardDetails> getCardDetails(String emailId);
}
