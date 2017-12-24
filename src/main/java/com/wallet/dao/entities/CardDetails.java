/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallet.dao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ketan.jariwala
 */
@Entity
@Table(name = "CARD_DETAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CardDetails.findAll", query = "SELECT c FROM CardDetails c")
    , @NamedQuery(name = "CardDetails.findById", query = "SELECT c FROM CardDetails c WHERE c.id = :id")
    , @NamedQuery(name = "CardDetails.findByCardNo", query = "SELECT c FROM CardDetails c WHERE c.cardNo = :cardNo")
    , @NamedQuery(name = "CardDetails.findByNameOnCard", query = "SELECT c FROM CardDetails c WHERE c.nameOnCard = :nameOnCard")
    , @NamedQuery(name = "CardDetails.findByValidaty", query = "SELECT c FROM CardDetails c WHERE c.validaty = :validaty")
    , @NamedQuery(name = "CardDetails.findByCvv", query = "SELECT c FROM CardDetails c WHERE c.cvv = :cvv")})
public class CardDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CARD_NO")
    private String cardNo;
    @Basic(optional = false)
    @Column(name = "NAME_ON_CARD")
    private String nameOnCard;
    @Basic(optional = false)
    @Column(name = "VALIDATY")
    private String validaty;
    @Basic(optional = false)
    @Column(name = "CVV")
    private int cvv;
    @JoinColumn(name = "EMAIL_ID", referencedColumnName = "EMAIL_ID")
    @ManyToOne(optional = false)
    private UserDetails emailId;

    public CardDetails() {
    }

    public CardDetails(Integer id) {
        this.id = id;
    }

    public CardDetails(Integer id, String cardNo, String nameOnCard, String validaty, int cvv) {
        this.id = id;
        this.cardNo = cardNo;
        this.nameOnCard = nameOnCard;
        this.validaty = validaty;
        this.cvv = cvv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getValidaty() {
        return validaty;
    }

    public void setValidaty(String validaty) {
        this.validaty = validaty;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public UserDetails getEmailId() {
        return emailId;
    }

    public void setEmailId(UserDetails emailId) {
        this.emailId = emailId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CardDetails)) {
            return false;
        }
        CardDetails other = (CardDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wallet.dao.entities.CardDetails[ id=" + id + " ]";
    }

}
