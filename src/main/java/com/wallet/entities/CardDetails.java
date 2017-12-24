/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ketan.jariwala
 */
public class CardDetails {

    @NotEmpty
    @Email
    private String emailId;
    @NotEmpty
    @Length(min = 16, max = 16)
    private String cardNo;
    @NotEmpty
    private String nameOnCard;
    @NotEmpty
    private String cardValidity;
    @NotEmpty
    @Length(min = 3, max = 3)
    private int cardCvv;

    public CardDetails() {
    }

    public CardDetails(com.wallet.dao.entities.CardDetails cardDetails) {
        this.nameOnCard = cardDetails.getNameOnCard();
        this.cardNo = cardDetails.getCardNo();
        this.cardValidity = cardDetails.getValidaty();
        this.cardCvv = cardDetails.getCvv();
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardValidity() {
        return cardValidity;
    }

    public void setCardValidity(String cardValidity) {
        this.cardValidity = cardValidity;
    }

    public int getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(int cardCvv) {
        this.cardCvv = cardCvv;
    }

    @Override
    public String toString() {
        return "CardDetails{" + "emailId=" + emailId + ", cardNo=" + cardNo + ", nameOnCard=" + nameOnCard + ", cardValidity=" + cardValidity + ", cardCvv=" + cardCvv + '}';
    }
}
