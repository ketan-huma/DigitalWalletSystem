/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.dao;

import com.wallet.dao.entities.UserDetails;
import com.wallet.entities.CardDetails;
import com.wallet.entities.User;
import java.util.Collection;

/**
 *
 * @author ketan.jariwala
 */
public interface WalletDao {

    public void register(User userDetails);

    public UserDetails getUser(String email);

    public void addCard(CardDetails cardDetails);

    public Collection<com.wallet.dao.entities.CardDetails> getCards(String emailId);
}
