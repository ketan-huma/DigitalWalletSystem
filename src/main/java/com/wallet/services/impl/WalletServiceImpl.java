/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.services.impl;

import com.wallet.dao.WalletDao;
import com.wallet.dao.entities.UserDetails;
import com.wallet.entities.CardDetails;
import com.wallet.entities.User;
import com.wallet.services.WalletService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    @Override
    public void register(User userdetails) {
        walletDao.register(userdetails);
    }

    @Override
    public boolean login(User userdetails) {
        UserDetails user = walletDao.getUser(userdetails.getEmail());
        return user != null && user.getPassword().equals(userdetails.getPassword());
    }

    @Override
    public boolean isEmailExists(String emailId) {
        return walletDao.getUser(emailId) != null;
    }

    @Override
    public void addCardDetails(CardDetails cardDetails) {
        walletDao.addCard(cardDetails);
    }

    @Override
    public List<CardDetails> getCardDetails(String emailId) {
        Collection<com.wallet.dao.entities.CardDetails> cards = walletDao.getCards(emailId);

        List<CardDetails> lstCard = new ArrayList<>();
        for (com.wallet.dao.entities.CardDetails card : cards) {
            lstCard.add(new CardDetails(card));
        }
        return lstCard;
    }
}
