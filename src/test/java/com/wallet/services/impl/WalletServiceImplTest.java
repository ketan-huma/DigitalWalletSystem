/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.services.impl;

import com.wallet.dao.WalletDao;
import com.wallet.entities.CardDetails;
import com.wallet.services.WalletService;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author ketan.jariwala
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dispatcher-servlet-test.xml"})
public class WalletServiceImplTest {

    @Mock
    private WalletDao walletDao;

    @InjectMocks
    @Autowired
    private WalletService walletService;

    public WalletServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCardDetails method, of class WalletServiceImpl.
     */
    @Test
    public void testAddCardDetails() {
        System.out.println("addCardDetails");
        CardDetails cardDetails = new CardDetails();
        System.out.println("WalletService " + walletService);
        walletService.addCardDetails(cardDetails);
        Mockito.verify(walletDao).addCard(cardDetails);
    }

    /**
     * Test of getCardDetails method, of class WalletServiceImpl.
     */
    @Test
    public void testGetCardDetails() {
        System.out.println("getCardDetails");
        String emailId = "ketan@gmail.com";

        List<com.wallet.dao.entities.CardDetails> expResult = new ArrayList<>();
        expResult.add(new com.wallet.dao.entities.CardDetails(1, "1111111111111111", "Ketn Jariwala", emailId, 123));
        expResult.add(new com.wallet.dao.entities.CardDetails(2, "2222222222222222", "Ketn Jariwala", emailId, 321));

        Mockito.when(walletDao.getCards(emailId)).thenReturn(expResult);

        List<CardDetails> cardDetails = walletService.getCardDetails(emailId);
        assertThat(cardDetails, hasSize(2));

        assertThat(cardDetails.get(0).getCardNo(), is(expResult.get(0).getCardNo()));
        assertThat(cardDetails.get(1).getCardNo(), is(expResult.get(1).getCardNo()));
    }

}
