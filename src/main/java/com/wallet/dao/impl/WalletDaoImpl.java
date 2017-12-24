/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.dao.impl;

import com.wallet.dao.WalletDao;
import com.wallet.dao.entities.UserDetails;
import com.wallet.entities.CardDetails;
import com.wallet.entities.User;
import com.wallet.util.HibernateUtil;
import java.util.Collection;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WalletDaoImpl implements WalletDao {

    @Override
    public void register(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserDetails userDetails = new UserDetails();
        userDetails.setEmailId(user.getEmail());
        userDetails.setName(user.getFullName());
        userDetails.setPassword(user.getPassword());

        session.persist(userDetails);
        session.getTransaction().commit();
    }

    @Override
    public UserDetails getUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (UserDetails) session.get(UserDetails.class, email);
    }

    @Override
    public void addCard(CardDetails cardDetails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserDetails userDetails = (UserDetails) session.load(UserDetails.class, cardDetails.getEmailId());

        com.wallet.dao.entities.CardDetails cardDaoEnt = new com.wallet.dao.entities.CardDetails();
        cardDaoEnt.setNameOnCard(cardDetails.getNameOnCard());
        cardDaoEnt.setCardNo(cardDetails.getCardNo());
        cardDaoEnt.setCvv(cardDetails.getCardCvv());
        cardDaoEnt.setEmailId(userDetails);
        cardDaoEnt.setValidaty(cardDetails.getCardValidity());

        session.persist(cardDaoEnt);
        session.getTransaction().commit();
    }

    @Override
    public Collection<com.wallet.dao.entities.CardDetails> getCards(String emailId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserDetails userDetails = (UserDetails) session.get(UserDetails.class, emailId);
        return userDetails.getCardDetailsCollection();
    }
}
